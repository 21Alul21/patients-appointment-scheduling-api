
@RestController
@RequestMapping("/appointment")
public class AppointmentController{

  private final AppointmentService appointmentService;
  private final NotificationService notificationService;
  private final DoctorRepository doctorRepository;
  public AppointmentController(AppointmentService appointmentService, NotificationService notificationService, DoctorRepository doctorRepository){
  this.appointmentService = appointmentService;
  this.notificationService = notificationService;
  this.doctorRepository = doctorRepository;
  }

  // crete appointment and notification
  @PostMapping("/create-appointment")
  public ResponseEntity<AppointmentEntity> createAppointment(@RequestBody AppointmentEntity appointmentEntity){
    DoctorEntity doctor = doctorRepository.findDoctorById(appointmentEntity.getDoctorId());
    appointmentEntity.setStatus("PENDING");
    appointmentEntity.setDoctor(doctor);
    AppointmentEntity createdAppointment = appointmentService.createAppointment(appointmentEntity);
   
   
   NotificationEntity notificationEntity = new NotificationEntity();
   notificationEntity.setMessage("new appointment created");
   notificationEntity.setSentAt(LocalDateTime.now());
   notificationEntity.setIsRead(false);
   notificationEntity.setStatus("PENDING");
   notificationEntity.setDoctor(doctor);
   notificationService.createNotification(notificationEntity);
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(createdAppointment);
  }

  // get all appointments
  @GetMapping("/all-appointments")
  public ResponseEntity<List<AppointmentEntity>> getAppointments(){
    return ResponseEntity.ok(appointmentService.getAppointments());
  }

  // get a particular appointment
  @GetMapping("/get-appointment/{id}")
  public ResponseEntity<AppointmentEntity> getAppointment(@PathVariable UUID id){
    return ResponseEntity.ok(appointmentService.findById(id));
  }

  // update an appointment 
  @PatchMapping("/update-appointment/{id}")
  public ResponseEntity<AppointmentEntity> updateAppointment(@RequestBody AppointmentEntity appointmentEntity, @PathVariable UUID id){
   AppointmentEntity update = appointmentService.updateAppointment(appointmentEntity, id);
    return ResponseEntity
     .status(HttpStatus.OK)
     .body(update);
  }

}

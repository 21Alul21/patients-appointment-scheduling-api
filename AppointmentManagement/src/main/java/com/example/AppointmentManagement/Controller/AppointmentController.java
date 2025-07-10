
@RestController
@RequiredAllArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController{

  private final AppointmentService appointmentService;
  private final NotificationService notificationService;
  private final DoctorRepository doctorRepository;
  

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

  // get all appointments related to an authenticated user
  @GetMapping("/get-user-appointments")
  public List<AppointmentEntity> getUserAppointments(){
    UserEntity currentUser = authenticateUser();
    UUID orgId = currentUser.getOrganization().getOrganizationId();
    return appointmentRepository.findAllByOrganization_organizationIdAndUser_UserId(orgId, currentUser.getUserId());
  }

  // update an appointment 
  @PatchMapping("/update-appointment/{id}")
  public ResponseEntity<AppointmentEntity> updateAppointment(@RequestBody AppointmentEntity appointmentEntity, @PathVariable UUID id){
   AppointmentEntity update = appointmentService.updateAppointment(appointmentEntity, id);
    return ResponseEntity
     .status(HttpStatus.OK)
     .body(update);
  }

  // delete an appointment
  @DeleteMapping("/delete-appointment/{id}")
  public ResponseEntity<void> deleteAppointment(@PathaVariable UUID id){
    appointmentService.deleteAppointment(id);
    return ResponseEntity
      .noContent()
      .build();
  }

}

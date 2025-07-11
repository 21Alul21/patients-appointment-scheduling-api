
@RestController
@RequiredAllArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController{

  private final AppointmentService appointmentService;
  private final NotificationService notificationService;
  private final DoctorRepository doctorRepository;
  private final AuthUtils authUtils;
  private final UserRepository userRepository;
  

  // crete appointment and notification by a patient in an organization 
  // to be viewed by a doctor in the same organization only
  @PostMapping("/create-appointment")
  public ResponseEntity<AppointmentEntity> createAppointment(@RequestBody AppointmentEntity appointmentEntity){
   UserEntity currentUser = jwtUtils.getCurrentUser();
   UUID currentUserOrgId = currentUser.getOrganization().organizationId();
   DoctorEntity doctor = userRepository.findByOrganization_OrganizationIdAndDoctor_DoctorId(currentUserOrgId, UUID appointmentEntity.getDoctorId());
    OrganizationEntity currentUserOrg = currentUser.getOrganization()
    appointmentEntity.setStatus("PENDING");
    appointmentEntity.setDoctor(doctor);
    appointmentEntity.setOrganization(currentUserOrg);
    AppointmentEntity createdAppointment = appointmentService.createAppointment(appointmentEntity);
   
   
   NotificationEntity notificationEntity = new NotificationEntity();
   notificationEntity.setMessage("new appointment created");
   notificationEntity.setSentAt(LocalDateTime.now());
   notificationEntity.setIsRead(false);
   notificationEntity.setStatus("PENDING");
   notificationEntity.setOrganization(currentUserOrg);
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
  public ResponseEntity<List<AppointmentEntity>> getUserAppointments(){
    UserEntity currentUser = authUtils.getCurrentUser();
    UUID orgId = currentUser.getOrganization().getOrganizationId();
    return ResponseEntity
      .ok(appointmentRepository.findAllByOrganization_organizationIdAndUser_UserId(orgId, currentUser.getUserId()));
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

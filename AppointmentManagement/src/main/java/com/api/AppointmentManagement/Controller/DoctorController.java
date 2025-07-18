package com.api.AppointmentManagement.Controller;

@RestController
@RequiredAllArgsConstructor
@RequestMapping("/doctor")
public class DoctorController{
  private final DoctorService doctorService;
  private final AuthUtils authUtils;

  
  // SUPERADMIN can get a doctor
  @GetMapping("/{doctorId}")
  public ResponseEntity<DoctorDTO> getDoctor(@PathVariable UUID doctorId){
    return ResponseEntity
      .ok(doctorService.getDoctor(doctorId));
  }

// Doctor in an organization getting list of all their appointments
@GetMapping("/get-all-appointments-with-patients")
public ResponseEntity<List<AppointmentEntity>> getAppointmentsWithPatients() {
    UserEntity currentUser = authUtils.authenticateUser();
    UUID orgId = currentUser.getOrganization().getOrganizationId();

    // Get doctorId from currentUser
    DoctorEntity doctor = currentUser.getDoctor();
    if (doctor == null) {
        throw new RuntimeException("Current user is not a doctor");
    }

    UUID doctorId = doctor.getDoctorId();

    List<AppointmentEntity> appointments =
        appointmentRepository.findAllByDoctor_DoctorIdAndOrganization_OrganizationId(doctorId, orgId);

    return ResponseEntity.ok(appointments);
}

  
  // SUPERADMIN can get doctors across all orgs
  @GetMapping("/all")
  public ResponseEntity<List<DoctorDTO>> getDoctors(){
    return ResponseEntity
      .ok(doctorService.getDoctors());
  }

  // ORGADMINS can get list of doctors in their org
    @GetMapping("/org-patients-list")
    public ResponseEntity<List<DoctorDTO>> getOrgPatients(){
      return ResponseEntity
        .ok(doctorService.getOrgDoctors());  
    }

  @PatchMapping("/change-appointment-status/{appointmentId}")
public ResponseEntity<?> changeAppointmentStatus(
        @PathVariable UUID appointmentId,
        @RequestParam String status) {

    UserEntity currentUser = authUtils.authenticateUser();
    UUID orgId = currentUser.getOrganization().getOrganizationId();

    // Check if the current user is a doctor
    DoctorEntity doctor = currentUser.getDoctor();
    if (doctor == null) {
        throw new RuntimeException("Current user is not a doctor");
    }

    // Find the appointment
    AppointmentEntity appointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new AppointmentNotFoundException("Couldn't find the appointment"));

    // Ensure the appointment belongs to the same doctor and organization
    if (!appointment.getDoctor().getDoctorId().equals(doctor.getDoctorId())) {
        throw new RuntimeException("This appointment does not belong to the current doctor");
    }

    if (!doctor.getOrganization().getOrganizationId().equals(appointment.getOrganization().getOrganizationId())) {
        throw new RuntimeException("Doctor and appointment do not belong to the same organization");
    }

    // Update appointment status
    AppointmentStatusEnum newStatus;
    try {
        newStatus = AppointmentStatusEnum.valueOf(status.toUpperCase());
    } catch (IllegalArgumentException e) {
        throw new RuntimeException("Invalid appointment status value");
    }

    appointment.setAppointmentStatus(newStatus);
    appointmentRepository.save(appointment);

    //crete Notification for the patient 
    NotificationEntity notification = new NotificationEntity();
    notification.setMessage("Your appointment was " + newStatus.toString().toLowerCase());
    notification.setSentAt(LocalDateTime.now());
    notification.setIsRead(false);
    notification.setStatus(newStatus.toString());
    notification.setRecipient(appointment.getUser()); // patient
    notification.setAppointment(appointment);
    notification.setOrganization(appointment.getOrganization());

    notificationService.createNotification(notification);

   // Notify patient via websocket
    NotificationMessage message = new NotificationMessage(
        "Your appointment was " + status.toUpperCase(),
        doctorUser.getEmail(),
        appointment.getAppointmentId().toString(),
        LocalDateTime.now().toString()
    );
    notificationService.sendToPatient(appointment.getUser().getEmail(), message);

    return ResponseEntity.ok("Appointment status updated and patient notified.");
}

  // SUPERADMIN can update a doctor record
  @PatchMapping("/update-profile/{doctorId}")
  public ResponseEntity<DoctorEntity> updateDoctor(@RequestBody DoctorEntity doctorEntity, @PathVariable UUID doctorId){
   return ResponseEntity.ok(doctorService.updateDoctor(doctorEntity, doctorId));
  }

  // SUPERADMIN can delete any doctor record across orgs
  @DeleteMapping("/delete-account/{doctorId}")
  public ResponseEntity<Void> deleteDoctor(@PathVariable UUID doctorId){
    doctorService.deleteDoctor(doctorId);
   return ResponseEntity
     .noContent()
     .build();
  }

}

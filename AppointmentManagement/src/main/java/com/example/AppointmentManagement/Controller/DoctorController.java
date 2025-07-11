
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

  // doctor can update the status of his appointment with a patient
  @PatchMapping("/change-appointment-status/{appointmentId}")
  public ResponseEntity<?> changeAppointmentStatus(@PathVariable UUID appointmentId, @RequestParam String status){
    UserEntity currentUser = authUtils.authenticateUser();
    UUID orgId = currentUser.getOrganization().getOrganizationId();

    // Get doctorId from currentUser
    DoctorEntity doctor = currentUser.getDoctor();
    if (doctor == null) {
        throw new RuntimeException("Current user is not a doctor");
    }
    AppointmentEntity appointment = appointmentRepository
      .findById(appointmentId)
      .orElseThrow(() -> new AppointmentNotFoundException("could’nt find the appointment"));

    if (!doctor.getOrganization().getOrganizationId().equals(appointment.getOrganization().getOrganizationId()){
       throw new RuntimeException("the doctor and appointment are not in the same organization");
    }
    return appointment.setStatus(status.toUppercase());
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

package com.api.AppointmentManagement.Service;

@Service
public class DoctorService{

  private final DoctorRepository doctorRepository;
  private final DoctorMapper doctorMapper;
  
  public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper){
  this.doctorService = doctorService;
  this.doctorMapper = doctorMapper;
  }

  // GET a doctor
  public DoctorDTO getDoctor(UUID doctorId){
   DoctorEntity doctor = doctorRepository
     .findById(doctorId)
     .orElseThrow(() -> new DoctorNotFoundException("no doctor was found at the given Id"));
    return doctorMapper.toDTO(doctor);
  }

  // GET ALL doctors across organizations
  public List<DoctorDTO> getDoctors(){
    List<DoctorEntity> doctors = doctorService.findAll();
    if (doctors.isEmpty()){
      throw new DoctorNotFoundException("doctors not found");
    }
      return
      doctorRepository
      .stream()
      .findAll()
      .map(doctorMapper::toDTO)
      .collect(Collectors.toList())
  }

  // GET all doctors within the same organization as the current user
public List<UserEntity> getOrgPatients() {
    UserEntity currentUser = authUtils.getCurrentUser(); // already authenticated
    UUID orgId = currentUser.getOrganization().getOrganizationId();

    return userRepository.findAllByOrganization_OrganizationIdAndRole(orgId, RoleEnum.DOCTOR);
}

  // CREATE doctor record
  public DoctorEntity createDoctor(DoctorEntity doctorEntity){
   if (doctorEntity == null){
     throw new IllegalArgumentException("doctor fields has to contain valid credentials");
   }
    String rawPassword = doctorEntity.getPassword();
    String hashedPassword = passwordEncoder.encode(rawPassword);
    doctorEntity.setPassword(hashedPassword);
    
    return doctorRepository.save(doctorEntity);
  }

  // UPDATE doctor record
  public DoctorEntity updateDoctor(DoctorEntity doctorEntity, UUID doctorId){
   if (doctorEntity == null){
     throw new IllegalArgumentException("doctor fields has to contain valid credentials to update record");
   }
    DoctorEntity doctor = doctorRepository
      .findById(doctorId)
      .orElseThrow(() -> new DoctorNotFoundException("cannot update doctor entity with id: " + doctorId)); 
      doctor.setFirstName(doctorEntity.getFirstName());
      doctor.setLastName(doctorEntity.getLastName());
      doctor.setAge(doctorEntity.getAge());
      doctor.setEmail(doctorEntity.getEmail());
     doctorRepository.save(public DoctorEntity updateDoctor(DoctorEntity doctorEntity, UUID doctorId){
    return doctorRepository.save(doctor);   
  }

   // DELETE doctor record
    public void deleteDoctor(UUID doctorId){
    DoctorEntity doctor = doctorRepository.findById(doctorId)
      .orElseThrow(() -> new DoctorNotFoundException("cannot delete doctor record at Id: " + doctorId));
 
  doctorRepository.deleteById(doctorId);
  }

}
  

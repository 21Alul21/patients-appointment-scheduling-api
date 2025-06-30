
@Service
public class DoctorService{

  private final DoctorRepository doctorRepository;
  private final DoctorMapper doctorMapper;
  
  public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper){
  this.doctorService = doctorService;
  this.doctorMapper = doctorMapper;
  }

  public DoctorDTO getDoctor(UUID doctorId){
   DoctorEntity doctor = doctorRepository
     .findById(doctorId)
     .orElseThrow(() -> new DoctorNotFoundException("no doctor was found at the given Id"));
    return doctorMapper.toDTO(doctor);
  }

  public List<DoctorDTO> getDoctors(){
    List<DoctorEntity> doctors = doctorService.findAll();
    if (doctors.isEmpty()){
      throw new DoctorsNotFoundException("doctors not found");
    }
      return
      .map(doctorMapper::toDTO)
      .collect(Collectors.toList())
  }

  public DoctorEntity createDoctor(DoctorEntity doctorEntity){
   if (doctorEntity == null){
     throw new IllegalArgumentException("doctor fields has to contain valid credentials");
   }
    return doctorRepository.save(doctorEntity);
  }

  public DoctorEntity updateDoctor(DoctorEntity doctorEntity, UUID doctorId){
   if (doctorEntity == null){
     throw new IllegalArgumentException("doctor fields has to contain valid credentials to update record");
   }
    DoctorEntity doctor = doctorRepository
      .findById(doctorId)
      .orElseThrow(() -> new DoctorNotFoundException("cannot update doctor entity with id: " + doctorId); 
      doctor.setFirstName(doctorEntity.getFirstName());
      doctor.setLastName(doctorEntity.getLastName());
      doctor.setAge(doctorEntity.getAge());
      doctor.setEmail(doctorEntity.getEmail());
   return doctor;
  }

  public void deleteDoctor(UUID doctorId){
    doctorRepository.deleteById(doctorId)
      .orElseThrow(() -> new DoctorNotFoundException("cannot delete doct record at Id: " + doctorId));
  }

}
  


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

  // GET doctors
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

  // CREATE doctor record
  public DoctorEntity createDoctor(DoctorEntity doctorEntity){
   if (doctorEntity == null){
     throw new IllegalArgumentException("doctor fields has to contain valid credentials");
   }
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
  

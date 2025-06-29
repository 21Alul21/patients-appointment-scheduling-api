
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
    if (doctors.isEmpty){
      throw new DoctorsNotFoundException("doctors not found");
    }
      return
      .map(doctorMapper::toDTO)
      .collect(Collectors.toList())
  }
}

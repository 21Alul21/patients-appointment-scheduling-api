
@Service
public class DoctorService{

  private final DoctorRepository doctorRepository;
  public DoctorService(DoctorRepository doctorRepository){
  this.doctorService = doctorService;
  }

  public DoctorDTO getDoctor(UUID doctorId){
   DoctorEntity doctor = doctorRepository
     .findById(doctorId)
     .orElseThrow(() -> new DoctorNotFoundException("no doctor was found at the given Id"));
    return doctorDTO.toDTO(doctor);
  }

  public List<DoctorDTO> getDoctors(){
  }
}

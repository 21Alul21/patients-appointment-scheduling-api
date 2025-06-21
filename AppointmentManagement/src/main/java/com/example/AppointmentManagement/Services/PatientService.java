
@Service
public class PatientService{
  private final PatientRepository patientRepository;
  private final PateintMapper PatientMapper;
  
  public PatientService(PatienRepository patientRepository, PatientDTO patientDTO, DTOMapper dtoMapper){
    this.patientRepository = patientRepository;
  }

  public List<PatientDTO> getPatients(){

  List<PatientDTO> patientDTO = patientRepository.findAll()
 .stream().map(patientEntity->patientMapper.toDTO(patientEntity)).collect(Collectors.toList());
    return patientDTO;
  }
}

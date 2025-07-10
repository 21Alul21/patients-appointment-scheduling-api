@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtils authUtils;


    // GET all patients accross organizations by SUPERADMIN
    public List<PatientDTO> getPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDTO)
                .collect(Collectors.toList());
        
    }

    // GET all patients in a particular organization where the orgAdmin belong
    public List<UserEntity> getOrgUsers(UUID orgId){
      if (orgId == null){
          throw new IllegalArgumentException("organizationId not found");
      }
        UserEntiry currentUser = authUtils.getCurrentUser();
        if (currentUser.getOrganization().getOrganizationId() != orgId){
           throw new AccessDeniedException("you do not have access to that organization data");
        }
       return userRepository.findAllByOrganizationId(orgId);
    }

    // GET one patient
    public PatientDTO getPatient(UUID patientId) {
    return patientRepository.findById(patientId)
            .stream()
            .map(patientMapper::toDTO)
            .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + patientId));
}
/*
// CREATE patient
public PatientEntity createPatient(PatientEntity patientEntity){
if (patientEntity == null){
throw new IllegalArgumentException("empty patient credentials");
}
String rawPassword = patientEntity.getPassword();
    String hashedPassword = passwordEncoder.encode(rawPassword);
    patientEntity.setPassword(hashedPassword);
    
return patientRepository.save(patientEntity);
}
*/

// DELETE patient
public PatientDTO deletePatient(UUID patientId) {
    PatientEntity patient = patientRepository.findById(patientId)
        .orElseThrow(() -> new RuntimeException("No patient found with ID: " + patientId));
    
    patientRepository.deleteById(patientId);

    return patientMapper.toDTO(patient); 
}

// UPDATE patient
public PatientEntity updatePatient(PatientEntity updatedData, UUID patientId) {
    PatientEntity existingPatient = patientRepository.findById(patientId)
            .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + patientId));

    // Update fields
    existingPatient.setFirstName(updatedData.getFirstName());
    existingPatient.setLastName(updatedData.getLastName());
    existingPatient.setAge(updatedData.getAge());
    existingPatient.setEmail(updatedData.getEmail());
    

    return patientRepository.save(existingPatient);
}


} 



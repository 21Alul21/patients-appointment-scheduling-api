package com.api.AppointmentManagement.Service;

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

    // GET all patients within the same organization as the current user
public List<UserEntity> getOrgPatients() {
    UserEntity currentUser = authUtils.getCurrentUser(); // already authenticated
    UUID orgId = currentUser.getOrganization().getOrganizationId();

    return userRepository
        .findAllByOrganization_OrganizationIdAndRole(orgId, RoleEnum.PATIENT)
        .stream()
        .map(patientMapper::toDTO)
        .collect(Collectors.toList());
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



@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public List<PatientDTO> getPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO getPatient(UUID patientId) {
    return patientRepository.findById(patientId)
            .map(patientMapper::toDTO)
            .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patientId));
}


public PatientEntity createPatient(PatientEntity patientEntity){
if (patientEntity == null){
throw new IllegalArgumentException("empty patient credentials");
}
String rawPassword = PatientEntity.get(password);
    String hashedPassword = passwordEncoder.encode(rawPassword);
    patientEntity.setPassword(hashedPassword);
    
return patientRepository.save(patientEntity);
}


public PatientDTO deletePatient(UUID patientId) {
    PatientEntity patient = patientRepository.findById(patientId)
        .orElseThrow(() -> new RuntimeException("No patient found with ID: " + patientId));
    
    patientRepository.deleteById(patientId);

    return patientMapper.toDTO(patient); 
}


public PatientEntity updatePatient(PatientEntity updatedData, UUID patientId) {
    PatientEntity existingPatient = patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patientId));

    // Update fields
    existingPatient.setFirstName(updatedData.getFirstName());
    existingPatient.setLastName(updatedData.getLastName());
    existingPatient.setAge(updatedData.getAge());
    existingPatient.setEmail(updatedData.getEmail());
    

    return patientRepository.save(existingPatient);
}


} 

}

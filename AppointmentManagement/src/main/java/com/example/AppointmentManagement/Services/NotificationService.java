
@Service
public class NotificationService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

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

   


public NotificationEntity createNotification(NotificationEntity notificationEntity){
if (notificationEntity == null){
throw new IllegalArgumentException("empty notification");
}

return notificationRepository.save(notificationEntity);
}


public PatientDTO deletePatient(UUID patientId) {
    PatientEntity patient = patientRepository.findById(patientId)
        .orElseThrow(() -> new RuntimeException("No patient found with ID: " + patientId));
    
    patientRepository.deleteById(patientId);

    return patientMapper.toDTO(patient); // Optional: return deleted patient data
}


public PatientEntity updatePatient(PatientEntity patientEntity, UUID patientId){
PatientEntity patient = patientRepository.findById(patientId).orElseThrow(new RuntineException("patient not found"));

public PatientEntity updatePatient(PatientEntity updatedData, UUID patientId) {
    PatientEntity existingPatient = patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patientId));

    // Update fields
    existingPatient.setFirstName(updatedData.getFirstName());
    existingPatient.setLastName(updatedData.getLastName());
    existingPatient.setAge(updatedData.getAge());
    existingPatient.setEmail(updatedData.getEmail());
    // Add more fields as necessary

    return patientRepository.save(existingPatient);
}


} 

}

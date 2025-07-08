public class PatientMapper {

    public PatientDTO toDTO(PatientEntity patientEntity) {
        if (patientEntity == null) {
            return null;
        }

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPatientId(patientEntity.getId());
        patientDTO.setFirstName(patientEntity.getFirstName());
        patientDTO.setLastName(patientEntity.getLastName());
        patientDTO.setEmail(patientEntity.getEmail());
        patientDTO.setPhoneNumber(patientEntity.getPhoneNumber());
        patientDTO.setGender(patientEntity.getGender());
        patientDTO.setRegisteredAt(patientEntity.getRegisteredAt());

        return patientDTO;
    }
}

public class DoctorMapper {

    public DoctorDTO toDTO(DoctorEntity doctorEntity) {
        if (doctorEntity == null) {
            return null;
        }

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setDoctorId(doctorEntity.getId());
        doctorDTO.setFirstName(doctorEntity.getFirstName());
        doctorDTO.setLastName(doctorEntity.getLastName());
        doctorDTO.setEmail(doctorEntity.getEmail());
        doctorDTO.setPhoneNumber(doctorEntity.getPhoneNumber());
        doctorDTO.setSpecialization(doctorEntity.getSpecialization());
        doctorDTO.setRegisteredAt(doctorEntity.getRegisteredAt());

        return doctorDTO;
    }
}

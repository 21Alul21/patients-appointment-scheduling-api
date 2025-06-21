public class DoctorMapper{
 public DoctorDTO toDTO(DoctorEntity doctorEntity){

      if (doctorEntity == null){
         return;
      }

   DoctorDTO doctorDTO = new DoctorDTO();
   doctorDTO.setId(DoctorEntity.getId());
   doctorDTO.setFirstName(DoctorEntity.getFirstName());
   doctorDTO.setLastName(DoctorEntity.getLastName());
   doctorDTO.setEmail(DoctorEntity.getEmail());
   return doctorDTO;
 }
  

}

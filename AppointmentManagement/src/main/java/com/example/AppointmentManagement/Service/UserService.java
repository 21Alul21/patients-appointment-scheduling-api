@Service
public class UserService{
  public UserEntity registerUser(RegisterDTO registerDTO){
     if (registerDTO == null){
       throw new IllegalArgumentException("user registration fields cannot be empty");
     }
    
    OrganizationEntity organization = organizationRepository.findById(registerDTO.getOrganizationId)
      .orElseThrow(new OrganizationNotFoundException("the organization id provided during regustration is invalid");
  }

  UserEntity user = new UserEntity();
  user.setEmail(registerDTO.getEmail());
  user.setPassword(registerDTO.getPassward());
  user.setRole(registerDTO.getRole().toUppercase());


 // bi-directional relationship assignment 
 if (user.getRole() == RoleEnum.PATIENT) {
        PatientEntity patient = new PatientEntity();
        patient.setUser(userEntity);       
        user.setPatient(patient);    
    }

    if (user.getRole() == RoleEnum.DOCTOR) {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setUser(user);
        user.setDoctor(doctor);
    }

    organization.setUser(user);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}

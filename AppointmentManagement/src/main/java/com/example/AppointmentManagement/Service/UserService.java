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
  user.setRole(registerDTO.getRole());

 // bi-directional relationship assignment 
}

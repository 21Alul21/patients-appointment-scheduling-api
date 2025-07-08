@RestController
@RequestMapping("api/v1/auth")
public class AuthController{

  @PostMapping("/register")
  public ResponseEntity<RegisterDTO> registerUser(@RequestMapping RegisterDTO registerDTO){
    if (registerDTO == null){
       throw new IllegalArgumentException("Registration fields cannot be empty")
    }

    UserEntity userEntity = new UserEntity();

    userEntity.setId(registerDTO.getId);
    userEntity.setEmail(registerDTO.getEmail);
    userEntity.setPassword(passwordEncoder.encode(registerDTO.getId));
    userEntity.setRole(registerDTO.getRole);

    return ResponseEntity
      .status(HttpStatus.CREATED);
      .body(userEntity);
}

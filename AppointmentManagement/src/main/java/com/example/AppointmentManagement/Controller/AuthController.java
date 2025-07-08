@RestController
@RequestMapping("api/v1/auth")
public class AuthController{

  @PostMapping("/register")
  public ResponseEntity<UserEntity> registerUser(@RequestBody RegisterDTO registerDTO){
    if (registerDTO == null){
       throw new IllegalArgumentException("Registration fields cannot be empty");
    }
    if ((registerDTO.getRole).toUppercase == "ADMIN"){
       throw new IllegalArgumentException("you cannot register as an admin");
    }

    UserEntity userEntity = new UserEntity();

    userEntity.setId(registerDTO.getId);
    userEntity.setEmail(registerDTO.getEmail);
    userEntity.setPassword(passwordEncoder.encode(registerDTO.getId));
    userEntity.setRole(RoleEntity.valueOf(registerDTO.getRole.toUppercase));

    return ResponseEntity
      .status(HttpStatus.CREATED);
      .body(userEntity);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
       
    }
}

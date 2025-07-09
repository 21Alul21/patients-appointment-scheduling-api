@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody RegisterDTO registerDTO) {
        if (registerDTO == null) {
            throw new IllegalArgumentException("Registration fields cannot be empty");
        }

        if (registerDTO.getRole().equalsIgnoreCase("ADMIN")) {
            throw new IllegalArgumentException("You cannot register as an admin");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(registerDTO.getId());
        userEntity.setEmail(registerDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        userEntity.setRole(RoleEnum.valueOf(registerDTO.getRole().toUpperCase()));

        if (userEntity.getRole().equalsIgnoreCase("PATIENT")){
            userEntity.getPatient().setUserEntity(userEntity);
        }

        if (userEntity.getRole().equalsIgnoreCase("DOCTOR")){
            userEntity.getPatient().setUserEntity(userEntity);
        }
      
        userEntity.get
        userRepository.save(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDTO.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(Map.of("token", token));
    }
}

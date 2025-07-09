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
            user = registerUser(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/registerOrgAdmin")
    public ResponseEntity<> registerOrgAdmin(@RequestBody RegisterDTO registerDTO, @RequestParameter String orgName){
        orgAdmin = registerOrgAdmin(registerDTO, orgName);
        return ResponseEntity.status(HttpStatus.CREATED)
             .body(orgAdmin);                      
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

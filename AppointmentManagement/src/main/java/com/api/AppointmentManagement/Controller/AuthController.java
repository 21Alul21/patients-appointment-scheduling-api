package com.api.AppointmentManagement.Controller;

@RestController
@RequiredAllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<UserEntity> registerUser(@RequestBody RegisterDTO registerDTO) {

        UserEntity user = userService.registerUser(registerDTO);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(user);
    }

    @PostMapping("/register-Org-Admin")
    public ResponseEntity<UUID> registerOrgAdmin(
            @RequestBody RegAdminOrgDTO registerDTO) {

        UUID orgRegNumber = userService.registerOrgAdmin(registerDTO);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(orgRegNumber);
    }
}


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        authenticationManager
            .authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDTO.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(Map.of("token", token));
    }
}

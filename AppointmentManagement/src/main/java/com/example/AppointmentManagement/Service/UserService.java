@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity registerUser(RegisterDTO registerDTO) {

        if (registerDTO == null) {
            throw new IllegalArgumentException("User registration fields cannot be empty");
        }

        OrganizationEntity organization = organizationRepository.findById(registerDTO.getOrganizationId())
            .orElseThrow(() -> new OrganizationNotFoundException("The organization ID provided is invalid"));

        UserEntity user = new UserEntity();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));  // Secure password
        user.setRole(RoleEnum.valueOf(registerDTO.getRole().toUpperCase()));
        user.setOrganization(organization);  

        // Bi-directional relationship assignment
        if (user.getRole() == RoleEnum.PATIENT) {
            PatientEntity patient = new PatientEntity();
            patient.setUser(user);       
            user.setPatient(patient);    
        }

        if (user.getRole() == RoleEnum.DOCTOR) {
            DoctorEntity doctor = new DoctorEntity();
            doctor.setUser(user);
            user.setDoctor(doctor);
        }

        return userRepository.save(user); 
    }
}

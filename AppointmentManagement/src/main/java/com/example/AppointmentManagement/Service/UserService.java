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

    public UserEntity registerAdmin(RegisterDTO registerDTO, String organizationName) {

    if (registerDTO == null) {
        throw new IllegalArgumentException("Fields for registering admin cannot be empty");
    }

    // Step 1: Create new organization and generate a unique org ID
    OrganizationEntity organization = new OrganizationEntity();
    String orgId = UUID.randomUUID().toString();  // Or use a better custom generator
    String uniqueOrgId = organizationName + "/" + orgId;

    organization.setName(organizationName);
    organization.setOrganizationId(uniqueOrgId);  // Set custom org ID

    // Step 2: Create admin user
    UserEntity user = new UserEntity();
    user.setEmail(registerDTO.getEmail());
    user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
    user.setRole(RoleEnum.ORGADMIN);
    user.setOrganization(organization);

    // Step 3: Set bi-directional relationship
    OrgAdminEntity orgAdmin = new OrgAdminEntity();
    orgAdmin.setUser(user);
    user.setOrgAdmin(orgAdmin);

    // Step 4: Save organization and user
    organizationRepository.save(organization);
    userRepository.save(user);

    return user;
  }

}

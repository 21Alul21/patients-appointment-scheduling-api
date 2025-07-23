package com.api.AppointmentManagement.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserEntity registerUser(RegisterDTO registerDTO) {

    if (registerDTO == null) {
        throw new IllegalArgumentException("User registration fields cannot be empty");
    }

    // Get current authenticated user
    Optional<UserEntity> currentUserOpt = jwtUtil.getCurrentUser();

    UserEntity authUser;

    if (currentUserOpt.isPresent()) {
        authUser = currentUserOpt.get();

        if (!RoleEnum.ADMIN.name().equals(authUser.getRole().name())) {
            throw new RuntimeException("Logged-in user must be an ADMIN to register a new user.");
        }

    } else {
        // Not logged in (public registration), must provide org info
        if (registerDTO.getOrgRegNumber() == null) {
            throw new RuntimeException("Non-admins must register with organization registration number.");
        }

        // Find organization by ID
        OrganizationEntity organization = organizationRepository.findById(registerDTO.getOrganizationId())
                .orElseThrow(() -> new OrganizationNotFoundException("The organization ID provided is invalid"));

        authUser = new UserEntity();  // dummy holder to set organization below
        authUser.setOrganization(organization);
    }

    // Create and prepare the new user
    UserEntity newUser = new UserEntity();
    newUser.setEmail(registerDTO.getEmail());
    newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
    newUser.setRole(RoleEnum.valueOf(registerDTO.getRole().toUpperCase()));
    newUser.setOrganization(authUser.getOrganization()); // Inherit org from authUser

    // Bi-directional relationship setup
    if (newUser.getRole() == RoleEnum.PATIENT) {
        PatientEntity patient = new PatientEntity();
        patient.setUser(newUser);
        newUser.setPatient(patient);
    }

    if (newUser.getRole() == RoleEnum.DOCTOR) {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setUser(newUser);
        newUser.setDoctor(doctor);
    }

    return userRepository.save(newUser);
}


    
    public UserEntity registerOrgAdmin(RegAdminOrgDTO dto) {
    if (dto == null) {
        throw new IllegalArgumentException("Missing registration info");
    }

    // Create new organization with unique ID
    OrganizationEntity org = new OrganizationEntity();
    org.setName(dto.getOrgName);
    String generatedOrgId = orgName.toLowerCase().replace(" ", "-") + "/" + UUID.randomUUID();
    org.setOrgRegNumber(generatedOrgId);

    // Create org admin user
    UserEntity user = new UserEntity();
    user.setEmail(dto.getEmail());
    user.setPassword(passwordEncoder.encode(dto.getPassword()));
    user.setRole(RoleEnum.ORGADMIN);
    user.setOrganization(org);

    organizationRepository.save(org);
    userRepository.save(user);
    return generatedOrgId;
    }

  }

}

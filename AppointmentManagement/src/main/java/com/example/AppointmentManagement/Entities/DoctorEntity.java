

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID DoctorId;
    @NotBlank(message = "first name cannot be blank")
    private String firstName;
    @NotBlank(message = "last name cannot be blank")
    private String lastNmame;
    @Size(min = 5, message = "length of password should be gretater than {min}")
    private String Password;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String meansOfIdentification;
    private String idNumber;
    @Email(message = "please enter a valid email address")
    private String email;
    @Enumerated(EnumType.STRING)
    private SpecializationEnum specialization;
    private LocalDateTime registeredAt;
   
    @OneToMany(mappedBy = "doctorEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatientEntity> patientEntity = new ArrayList<>();

    @OneToMany(mappedBy = "doctorEntity", cascade = Cascade.Type.All, orphanRemoval = true)
    private List<DoctorAvailability> doctorAvailability = new ArrayList<>();

    @OneToMany(mappedBy = "doctorEntitye", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotificationEntity> notificationEntity = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "organization_id");
    OrganizationEntity organization;  

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    
 
}

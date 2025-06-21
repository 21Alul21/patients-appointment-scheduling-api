

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID patientId;
    @NotBlank(message = "first name cannot be blank")
    private String firstName;
    @NotBlank(message = "last name cannot be blank")
    private String lastNmame;
    @Size(min = 5, message = "length of password should be gretater than {min}")
    private String Password;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String nextOfKinPhone;
    private String meansOfIdentification;
    private String idNumber;
    private String insuranceProvider;
    private String insuranceNumber;
    @Email(message = "Please Enter a valid email address")
    private String email;
    private LocalDateTime registeredAt;

    @OneToMany(mappedBy = "personEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotificationEntity> notificationEntity = new ArralList<>();  
    
    @OneToMany(mappedBy = "patientEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentEntity> appointmentEntity = new ArrayList<>();

}

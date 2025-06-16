

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID patientID;
    @NotBlank(message = "first name cannot be blank")
    private String firstName;
    @NotBlank(message = "last name cannot be blank")
    private String lastNmame;
    @Size(min = 5, message = "length of password should be gretater than {min}")
    private String Password;
    private Date dateOfBirth;
    private String phoneNumber;
    private int notification;
    private String nextOfKinPhone;
    private String meansOfIdentification;
    private String idNumber;
    private String insuranceProvider;
    private String insuranceNumber;
    @Email(message = "Please Enter a valid email address")
    private String email;
    private DateTime registeredAt;

    @OneToMany(mappedBy = "patientEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentEntity> appointmentEntity = new ArrayList<>();

}

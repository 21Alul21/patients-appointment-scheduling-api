


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID patientID;
    private String firstName;
    private String lastNmame;
    private String Password;
    private Date dateOfBirth;
    private String phoneNumber;
    private int notification;
    private String nextOfKinPhone;
    private String meansOfIdentification;
    private String idNumber;
    private String insuranceProvider;
    private String insuranceNumber;
    private String email;
    private DateTime registeredAt;

    @OneToMany(mappedBy = "patientEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentEntity> appointmentEntity = new ArrayList<>();


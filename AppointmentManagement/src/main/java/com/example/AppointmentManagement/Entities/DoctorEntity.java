


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID DoctorID;
    private String firstName;
    private String lastNmame;
    private String Password;
    private Date dateOfBirth;
    private String phoneNumber;
    private int notification;
    private String meansOfIdentification;
    private String idNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private SpecializationEnum specialization;
    private DateTime registeredAt;
    

    public Enum SpecializationEnum{
    GYNACOLOGIST,
    DENTIST,
    OPTICIAN,
    GENERAL_MEDICAL_SERVICES
}


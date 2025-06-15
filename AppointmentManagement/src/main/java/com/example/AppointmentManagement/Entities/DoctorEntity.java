


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID DoctorID;
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
    private String specialization;
    private DateTime registeredAt;

}
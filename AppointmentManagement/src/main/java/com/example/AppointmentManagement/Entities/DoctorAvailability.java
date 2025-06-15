


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
    private DateTime availableTime;
    private String email;
    @Enumerated(EnumType.STRING)
    private SpecializationEnum specialization;

    public Enum SpecializationEnum{
    GYNACOLOGIST,
    DENTIST,
    OPTICIAN,
    GENERAL_MEDICAL_SERVICES
}
}


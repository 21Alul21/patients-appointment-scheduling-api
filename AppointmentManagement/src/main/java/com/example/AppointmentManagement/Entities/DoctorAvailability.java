


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAvailability{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID doctorAvailabilityID;
    private DateTime availableTime;
    @Enumerated(EnumType.STRING)
    private SpecializationEnum specialization;

    @ManyToOne(fetch = fetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctorEntity;

    public Enum SpecializationEnum{
    GYNACOLOGIST,
    DENTIST,
    OPTICIAN,
    GENERAL_MEDICAL_SERVICES
}
}





@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAvailability{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID doctorAvailabilityID;
    @NotBlank("please enter a convenint available time")
    private DateTime availableTime;
    @Enumerated(EnumType.STRING)
    @NotBlank("please fill in your specialization")
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


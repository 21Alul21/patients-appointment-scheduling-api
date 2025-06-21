


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAvailability{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID doctorAvailabilityId;
    @NotNull(message = "please enter a convenint available time")
    private LocalDateTime availableTime;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "please fill in your specialization")
    private SpecializationEnum specialization;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctorEntity;

    public enum SpecializationEnum{
    GYNACOLOGIST,
    DENTIST,
    OPTICIAN,
    GENERAL_MEDICAL_SERVICES
}
}


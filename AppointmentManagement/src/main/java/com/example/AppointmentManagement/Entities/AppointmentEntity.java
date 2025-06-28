@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID appointmentId;

    @NotBlank(message = "Please enter your appointment title")
    private String appointmentTitle;

    private String appointmentDescription;

    @NotNull(message = "Please enter your appointment date and time")
    private LocalDateTime appointmentDueDate;

    private LocalDateTime appointmentBookedAt;

    @Enumerated(EnumType.STRING)
    private AppointmentStatusEnum appointmentStatus;

    @Enumerated(EnumType.STRING)
    private SpecializationEnum specialization;

    @OneToMany(mappedBy = "appointmentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotificationEntity> notificationEntity = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patientEntity;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;
}

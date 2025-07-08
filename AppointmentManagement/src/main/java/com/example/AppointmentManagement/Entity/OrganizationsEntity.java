@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID organizationId;

    private String organizationName;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus subscriptionStatus;

    private LocalDateTime registeredAt;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<DoctorEntity> doctors;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<PatientEntity> patients;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<AppointmentEntity> appointments;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<NotificationEntity> notifications;
}

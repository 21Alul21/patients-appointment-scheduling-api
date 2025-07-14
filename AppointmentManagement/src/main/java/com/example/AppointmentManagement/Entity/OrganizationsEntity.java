@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID organizationId;

    private String organizationName;

    private Boolean isTrial;


    // @Enumerated(EnumType.STRING)
    // private SubscriptionStatus subscriptionStatus;

    private int subscriptionDurationLeft;

    private LocalDateTime registeredAt;

    // @OneToOne(mappedBy = "organization", cascade = CascadeType.All, orphanRemoval = true)
   // private AdminEntity admin;
   
    @OneToMany(mappedBy = "organization", cascade = CascadeType.All)
    private List<UserEntity> users;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<AppointmentEntity> appointments;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<NotificationEntity> notifications;
}

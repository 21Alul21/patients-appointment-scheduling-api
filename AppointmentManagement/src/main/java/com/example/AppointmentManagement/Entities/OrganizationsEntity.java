
@Entity
@Data
@NoArgConstructor
@AllArgsConstructor
public class OrganizationEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID organizationId; 
  private String organizationName;
  @Enumerated(enumType.STRING)
  private String subscriptionStatus;
  private LocalDateTime registeredAt;
  
}
  

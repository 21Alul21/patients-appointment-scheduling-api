
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long notificationID;
  

}

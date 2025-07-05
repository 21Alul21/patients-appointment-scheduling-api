@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleEntity{
 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 private UUID roleId;
 private String roles;
}

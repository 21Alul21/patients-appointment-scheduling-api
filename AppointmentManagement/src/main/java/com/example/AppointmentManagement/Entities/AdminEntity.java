
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminID;
    private String username;
    private String password;
    private DateTime dateAccountCreated;
    private String role;

}

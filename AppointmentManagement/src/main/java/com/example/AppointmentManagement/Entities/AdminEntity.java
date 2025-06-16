
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminID;
    @NotBlank(message = "please enter your admin username")
    private String username;
    @NotBlank("password cannot be blank")
    private String password;
    private DateTime dateAccountCreated;
    private String role;

}

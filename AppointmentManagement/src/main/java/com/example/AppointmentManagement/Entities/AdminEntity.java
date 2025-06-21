
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;
    @NotBlank(message = "please enter your admin username")
    private String username;
    @NotBlank(mesage = "password cannot be blank")
    private String password;
    private LocalDateTime dateAccountCreated;
    private String role;

}


  @Entity
  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public class UserEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    @Email(message = "enter valid email address")
    @NotNull(message = "email cannot be blank")
    private String email;
    private String password

    @Enumerate(EnumType.STRING)
    private RoleEnum role;
  }

package com.api.AppointmentManagement.DTO

@Data
@AllArgsConstructor
@NoArgsConstrutor
public class RegisterDTO{
  private UUID orgRegNumber;
  private String email;
  private String password;
}

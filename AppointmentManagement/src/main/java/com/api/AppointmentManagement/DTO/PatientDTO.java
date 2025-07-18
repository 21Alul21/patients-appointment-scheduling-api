package com.api.AppointmentManagement.DTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO{
    private UUID patientId;
    private String firstName;
    private String lastNmame;
    private String email;
    private String gender;
    private LocalDateTime registeredAt;
    
}

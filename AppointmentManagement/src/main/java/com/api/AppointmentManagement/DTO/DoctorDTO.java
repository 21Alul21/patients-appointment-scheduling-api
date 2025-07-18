package com.api.AppointmentManagement.DTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorEntity{
    private UUID DoctorId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private SpecializationEnum specialization;
    private LocalDateTime registeredAt;

  public enum SpecializationEnum{
    GYNECOLOGIST,
    DENTIST,
    OPTICIAN,
    GENERAL_MEDICAL_SERVICES
}

}



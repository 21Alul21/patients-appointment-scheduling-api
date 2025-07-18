package com.api.AppointmentManagement.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long notificationId;
  private String message;
  private LocalDate sentAt;
  private boolean isRead;
  @Enumerated(EnumType.STRING)
  private StatusEnum status;

  @ManyToOne
  @JoinColumn(name = "appointment_id")
  private AppointmentEntity appointmentEntity;

  @ManyToOne
  @JoinColumn(name = "doctor_recepient_id")
  Private DoctorEntity doctor;
  
  @ManyToOne
  @JoinColumn(name = "patient_recepient_id")
  Private PatientEntity patientEntity;

 
    @ManyToOne
    @JoinColumn(name = "organization_id");
    OrganizationEntity organization;  
 

}

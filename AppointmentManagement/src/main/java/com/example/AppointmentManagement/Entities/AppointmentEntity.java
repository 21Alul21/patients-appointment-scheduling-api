
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID apointmentID;
    @NotBlank(message = "please enter your appointment title")
    private String apointmentTitle;
    private String apointmentDescription;
    @NotBlank(message = "please enter your first name")
    private String patientFirstName;
    @NotBlank(message = "please enter your last name")
    private String patientLastNmame;
    @NotBlank("please enter your appointment date and time")
    private DateTime appointmentDueDate;
    private DateTime appointmentBookedAt;
    @Enumerated(EnumType.STRING)
    private AppointmentStatusEnum appointmentStatus;
    @Enumerated(EnumType.STRING)
    private SpecializationEnum specialization;
    @Enumerated(EnumType.STRING)
    private DoctorFirstNameEnum doctorFirstName;
    @Enumerated(EnumType.STRING)
    private DoctorLastnameEnum doctorLastName;
    
    @OneToMany(mappedBy = "appointmentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    List<NotificationEntity> notificationEntity = new ArrayList<>();
    
    @ManyToOne
    @joinColumn(name = "patient_id")
    private PatientEntity patientEntity;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctorEntity;
    

// Enums
    public Enum AppointmentStatusEnum{
    PENDING,
    BOOKED,
    DECLINED,
    RESCHEDULED
}


public Enum DoctorFirstNameEnum{

}

public Enum DoctorLastNameEnum{

}


public Enum SpecializationEnum{
    GYNACOLOGIST,
    DENTIST,
    OPTICIAN,
    GENERAL_MEDICAL_SERVICES
}



}

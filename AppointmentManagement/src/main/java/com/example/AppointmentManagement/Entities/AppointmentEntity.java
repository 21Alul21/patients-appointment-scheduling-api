
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID apointmentID;
    private String apointmentTitle;
    private String apointmentDescription;
    private String patientFirstName;
    private String patientLastNmame;
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
    
    @ManyToOne(fetch = fetchType.LAZY)
    @joinColumn(name = "patient_id")
    private PatientEntity patientEntity;
    

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
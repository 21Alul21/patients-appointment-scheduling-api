
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO{
    private UUID patientID;
    private String firstName;
    private String lastNmame;
    private String email;
    private LocalDateTime registeredAt;
    
}

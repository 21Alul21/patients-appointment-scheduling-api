
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO{
    private UUID patientId;
    private String firstName;
    private String lastNmame;
    private String email;
    private LocalDateTime registeredAt;
    
}

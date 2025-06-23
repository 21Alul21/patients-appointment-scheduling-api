@RestController
@RequestMapping("/patients") // Corrected the typo
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable UUID patientId) {
        return ResponseEntity.ok(patientService.getPatient(patientId));
    }

    @GetMapping("/patients-list")
    @public ResponseEntity<List<PatientDTO>> getpatients(){
    return ResponseEntity.ok(patientService.getPatients());
    }
}

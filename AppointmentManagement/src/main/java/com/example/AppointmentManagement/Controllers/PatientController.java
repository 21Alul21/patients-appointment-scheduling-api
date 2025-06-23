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
    @public ResponseEntity<List<PatientDTO>> getPatients(){
    return ResponseEntity.ok(patientService.getPatients());
    }

    @PostMapping("/register")
    @public ResponseEntity<PatientEntity> createPatient(@Valid @RequestBody PatientEntity patientEntity){
    return ResponseEntity.ok(patientService.createPatient(patientEntity));
    }
}

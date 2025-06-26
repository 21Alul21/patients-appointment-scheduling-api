@RestController
@RequestMapping("/patients")
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
    public ResponseEntity<PatientEntity> createPatient(@Valid @RequestBody PatientEntity patientEntity){
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(patientService.createPatient(patientEntity));
    }

    @PatchMapping("/update-profile/{patientId}")
    public ResponseEntity<PatientEntity> updatePatientProfile(@Valid @RequestBody PatientEntity patientEntity, @PathVariable UUID patientId){
    return ResponseEntity.ok(patientService.updatePatient(patientEntity, patientId));
    }

    @DeleteMapping("/delete-patient/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID patientId) {
    patientService.deletePatient(patientId);
    return ResponseEntity.noContent().build(); 
}

}

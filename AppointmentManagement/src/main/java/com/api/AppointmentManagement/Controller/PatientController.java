package com.api.AppointmentManagement.Controller

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // SUPERADMIN can get a patient 
    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable UUID patientId) {
        return ResponseEntity.ok(patientService.getPatient(patientId));
    }

    // SUPERADMIN can get all patients across orgs
    @GetMapping("/patients-list")
    @public ResponseEntity<List<PatientDTO>> getPatients(){
    return ResponseEntity.ok(patientService.getPatients());
    }

    // ORGADMINS can get list of patients in their org
    @GetMapping("/org-patients-list")
    public ResponseEntity<List<PatientDTO>> getOrgPatients(){
      return ResponseEntity
          .ok(patientService.getOrgPatients());  
    }

    // SUPERADMIN can update any patient record across orgs
    @PatchMapping("/update-profile/{patientId}")
    public ResponseEntity<PatientEntity> updatePatientProfile(@Valid @RequestBody PatientEntity patientEntity, @PathVariable UUID patientId){
    return ResponseEntity.ok(patientService.updatePatient(patientEntity, patientId));
    }

     // SUPERADMIN can delete any patient record across orgs
    @DeleteMapping("/delete-patient/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID patientId) {
    patientService.deletePatient(patientId);
    return ResponseEntity.noContent().build(); 
}

}

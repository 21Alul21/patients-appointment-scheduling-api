
@RestController
@RequestMapping("/doctor")
public class DoctorController{
  private final DoctorService doctorService;
 
  public DoctorController(DoctorService doctorService){
    this.doctorService = doctorService;
  }
  @GetMapping("/{doctorId}")
  public ResponseEntity<DoctorDTO> getDoctor(@PathVariable UUID doctorId){
    return ResponseEntity.ok(DoctorService.getDoctor(doctorId));
  }

  @GetMapping("/all")
  public ResponseEntity<List<DoctorDTO>> getDoctors(){
    return ResponseEntity.ok(doctorService.getDoctors());
  }

  @PostMapping("/register-doctor")
  public ResponseEntity<DoctorEntity> registerDoctor(@RequestBody DoctorEntity doctorEntity){
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(doctorService.createDoctor(doctorEntity));
  }

  @PatchMapping("/update-profile/{doctorId}")
  public ResponseEntity<DoctorEntity> updateDoctor(@RequestBody DoctorEntity doctorEntity, doctorId){
   return ResponseEntity.ok(doctorService.updateDoctor(doctorEntity));
  }

  @DeleteMapping("/delete-account/{doctorId}")
  public ResponseEntity<Void> deleteDoctor(@PathVariable UUID doctorId){
    doctorService.deleteDoctor(doctorId);
   return ResponseEntity
     .noContent()
     .build();
  }

}

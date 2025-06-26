
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
      .ok(doctorService.createDoctor(doctorEntity));
  }

  @PatchMapping("/update-profile")
  public ResponseEntity<DoctorEntity> updateDoctor(@RequestBody DoctorEntity doctorEntity){
   return ResponseEntity.ok(doctorService.updateDoctor(dictorEntity));
  }

  @DeleteMapping("/delete-account")
  public ResponseEntity<Void> deleteDoctor(@PathVariable UUID doctorId){
    doctorService.deleteDoctor(doctorId);
   return ResponseEntity
     .noContent()
     .build()
  }

}

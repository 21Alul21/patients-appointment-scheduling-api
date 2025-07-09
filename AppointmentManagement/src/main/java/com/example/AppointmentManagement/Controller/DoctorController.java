
@RestController
@RequestMapping("/doctor")
public class DoctorController{
  private final DoctorService doctorService;
 
  public DoctorController(DoctorService doctorService){
    this.doctorService = doctorService;
  }
  @GetMapping("/{doctorId}")
  public ResponseEntity<DoctorDTO> getDoctor(@PathVariable UUID doctorId){
    return ResponseEntity.ok(doctorService.getDoctor(doctorId));
  }

  @GetMapping("/all")
  public ResponseEntity<List<DoctorDTO>> getDoctors(){
    return ResponseEntity.ok(doctorService.getDoctors());
  }

  @PatchMapping("/update-profile/{doctorId}")
  public ResponseEntity<DoctorEntity> updateDoctor(@RequestBody DoctorEntity doctorEntity, @PathVariable UUID doctorId){
   return ResponseEntity.ok(doctorService.updateDoctor(doctorEntity, doctorId));
  }

  @DeleteMapping("/delete-account/{doctorId}")
  public ResponseEntity<Void> deleteDoctor(@PathVariable UUID doctorId){
    doctorService.deleteDoctor(doctorId);
   return ResponseEntity
     .noContent()
     .build();
  }

}

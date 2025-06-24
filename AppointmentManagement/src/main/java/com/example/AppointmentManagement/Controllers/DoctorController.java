
@RestController
@RequestMapping("/doctor")
public class DoctorController{
  @GetMapping("/doctor/{doctorId}")
  public ResponseEntity<DoctorDTO> getDoctor(@PathVariable UUID doctorId){
    return ResponseEntity.ok(DoctorService.getDoctor(doctorId));
  }
}

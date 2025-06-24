
@RestController
@RequestMapping("/doctor")
public class DoctorController{
  @GetMapping("/doctor/{doctorId}")
  public DoctorDTO getDoctor(@PathVariable UUID doctorId){
  }
}

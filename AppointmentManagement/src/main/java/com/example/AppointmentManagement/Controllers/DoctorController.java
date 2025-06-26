
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

  @GetMapping("/doctor")
  public ResponseEntity<List<DocrorDTO>> getDoctors(){
    return ResponseEntity.ok(doctorService.getDoctors());
  }

  @PostMapping("/register-doctor")
  public ResponseEntity<DoctorEntity> registerDoctor(DoctorEntity doctorEntity){
    return ResponseEntity.ok(doctorService.createDoctor(doctorEntity));
  }

  @PatchMapping("/update-profile")
  

}

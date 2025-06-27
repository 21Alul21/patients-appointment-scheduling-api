
@RestController
@RequestMapping("/appointment")
public class AppointmentController{

  private final AppointmentService appointmentService;
  private final NotificationService notificationService;
  public AppointmentController(AppointmentService appointmentService, NotificationService notificationService){
  this.appointmentService = appointmentService;
  }

  @PostMapping("/create-appointment")
  public ResponseEntity<AppointmentEntity> createAppointment(@RequestBody AppointmentEntity appointmentEntity){
   createdAppointment = appointmentService.createAppointment(appointmentEntity);
   notificationService.createNotification();
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .build();
  }
}

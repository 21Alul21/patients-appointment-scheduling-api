
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
   NotificationEntity notificationEntity = new NotificationEntity();
   notificationEntity.setMessage("new appointment created");
   notificationEntity.setSentAt(LocalDateTime().now());
   notificationEntity.setIsRead(false);
   notificationEntity.setStatus("PENDING");
   notificationService.createNotification(notificationEntity);
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(createdAppointment);
  }
}

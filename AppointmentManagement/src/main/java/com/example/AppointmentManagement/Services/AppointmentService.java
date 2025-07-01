
@Service
public class AppointmentService{

  private final AppointmentRepository appointmentRepository;

  public AppointmentService(AppointmentRepository appointmentRepository){
  this.appointmentRepository = appointmentRepository;
  }

  public List<AppointmentEntity> getAppointments(){
    return appointmentRepository.findAll()
      .orElseThrow(() -> new AppointmentNotFoundException("no appointments were found"));
  }
}


@Service
public class AppointmentService{

  private final AppointmentRepository appointmentRepository;

  public AppointmentService(AppointmentRepository appointmentRepository){
  this.appointmentRepository = appointmentRepository;
  }

  public List<AppointmentEntity> getAppointments(){
    return appointmentRepository.findAll();
  }

  public AppointmentEntity getAppointment(UUID appointmentId){
   return appointmentRepository
     .findById(appointmentId)
     .orElseThrow(() -> new AppointmentNotFoundException("no appointment was found at id: " + appointmentId));
  }

  public AppointmentEntity createAppointment(AppointmentEntiry appointmentEntity){
   if (appointmentEntity == null){
     throw new AppointmentIsEmptyException("you cannot create an empty appointment");
   }
  return appointmentRepository.save(appointmentEntity);
  }
  
}


@Service
public class AppointmentService{

  private final AppointmentRepository appointmentRepository;

  public AppointmentService(AppointmentRepository appointmentRepository){
  this.appointmentRepository = appointmentRepository;
  }

  // GET all appointments
  public List<AppointmentEntity> getAppointments(){
    return appointmentRepository.findAll();
  }

 // GET an appointment 
  public AppointmentEntity getAppointment(UUID appointmentId){
   return appointmentRepository
     .findById(appointmentId)
     .orElseThrow(() -> new AppointmentNotFoundException("no appointment was found at id: " + appointmentId));
  }

   // CREATE appointment
    public AppointmentEntity createAppointment(AppointmentEntity appointmentEntity) {
        if (appointmentEntity == null) {
            throw new AppointmentIsEmptyException("You cannot create an empty appointment");
        }
        return appointmentRepository.save(appointmentEntity);
    }

    // UPDATE appointment
    public AppointmentEntity updateAppointment(AppointmentEntity appointmentEntity, UUID appointmentId) {
        if (appointmentEntity == null) {
            throw new AppointmentIsEmptyException("Cannot update appointment with empty data fields");
        }

        AppointmentEntity existingAppointment = appointmentRepository
                .findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException("Cannot find appointment record to update with ID: " + appointmentId));

        existingAppointment.setAppointmentStatus(appointmentEntity.getAppointmentStatus());
        existingAppointment.setAppointmentDueDate(appointmentEntity.getAppointmentDueDate());

        return appointmentRepository.save(existingAppointment);
    }

  // DELETE appointment
  public void deleteAppointment(UUID appointmentId){
   AppointmentEntity appointment = appointmentRepository
     .findById(appointmentId)
     .orElseThrow(() -> new AppointmentNotFoundException("cannot find the appointment to delete"));
    appointmentRepository.deletebyId(appointmentId);
  }

}

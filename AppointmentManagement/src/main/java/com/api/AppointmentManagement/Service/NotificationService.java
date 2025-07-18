package com.api.AppointmentManagement.Service;

@Service
@RequiredAllArgsConstructor
public class NotificationService{

private final NotificationRepository notificationRepository;
private final SimpMessagingTemplate messagingTemplate;

 
// CREATE notification for a booked appointment     
public NotificationEntity createNotification(NotificationEntity notificationEntity){
if (notificationEntity == null){
throw new IllegalArgumentException("empty notification");
   }

return notificationRepository.save(notificationEntity);

  } 

    public void sendToDoctor(String doctorEmail, NotificationMessage message) {
        messagingTemplate.convertAndSendToUser(
            doctorEmail,
            "/queue/notifications",
            message
        );
    }

    public void sendToPatient(String patientEmail, NotificationMessage message) {
        messagingTemplate.convertAndSendToUser(
            patientEmail,
            "/queue/notifications",
            message
        );
    }

 

}

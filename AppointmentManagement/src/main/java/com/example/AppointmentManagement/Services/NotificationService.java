
@Service
public class NotificationService{
    
public NotificationEntity createNotification(NotificationEntity notificationEntity){
if (notificationEntity == null){
throw new IllegalArgumentException("empty notification");
   }

return notificationRepository.save(notificationEntity);

  } 

}

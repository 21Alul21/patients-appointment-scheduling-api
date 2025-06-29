
@Service
public class NotificationService{

private final NotificationRepository notificationRepository;
piblic NotificationService(NotificationService notificationService){
 this.notificationRepository = notificationRepository;
}
    
public NotificationEntity createNotification(NotificationEntity notificationEntity){
if (notificationEntity == null){
throw new IllegalArgumentException("empty notification");
   }

return notificationRepository.save(notificationEntity);

  } 

}

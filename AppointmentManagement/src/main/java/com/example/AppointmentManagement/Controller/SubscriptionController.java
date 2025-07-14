@RestController
@RequestMapping("/org-admin")
public class SubscriptionController{
  
  
  @PostMapping("/subscribe")
  public ResponseEntity<?> orgSubscription(@RequestMapping SubscriptionDTO subscriptionDTO){
   // get current request and confirm if it’s the admin
     UserEntity currentUser = jwtUtils.authenticateUser();
     UUID orgId =  currentUser.getOrganization().getOrganizationId();
    return ResponseEntity.ok() 
  }
}

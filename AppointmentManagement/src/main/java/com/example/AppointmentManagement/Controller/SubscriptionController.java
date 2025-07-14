@RestController
@RequestMapping("/org-admin")
public class SubscriptionController{
  
  
  @PostMapping("/subscribe")
  public ResponseEntity<?> orgSubscription(@RequestMapping SubscriptionDTO subscriptionDTO){
   // get current user request and confirm if it’s the admin
     UserEntity currentUser = jwtUtils.authenticateUser();
     OrganizationEntity organization =  currentUser.getOrganization();
     // process the payment request

    // set the organization entity
    return ResponseEntity.ok() 
  }
}

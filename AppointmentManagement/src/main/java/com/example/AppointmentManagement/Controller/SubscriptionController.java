@RestController
@RequestMapping("/org-admin")
public class SubscriptionController{
  
  
  @GetMapping("/trial-subscribtion")
  public ResponseEntity<?> orgSubscription(@RequestMapping SubscriptionDTO subscriptionDTO){
   // get current user request and confirm if it’s the admin
     UserEntity currentUser = jwtUtils.authenticateUser();
     OrganizationEntity organization =  currentUser.getOrganization();
  // check if the organization has already used trial version before
  if (organization.isTrial == true){
    throw new RuntimeException("You can no longer use a trial subscription");
  }
  organization.setIstrial() = true;
  organization.setSubscriptionDuration(5);
   
    // set the organization entity
    return ResponseEntity.ok(Map.of("message" : "your trial version has been activated"));
  }
}

@RestController
@RequestMapping("/org-admin")
@RequiredAllArgsConstructor
public class SubscriptionController{
  private final JwtUtils jwtUtils;
  private final OrganizationRepository organizationRepository;
  
 @GetMapping("/trial-subscription")
  public ResponseEntity<?> orgSubscription(){
   // get current user request and confirm if it’s the admin
     UserEntity currentUser = jwtUtils.authenticateUser();
     OrganizationEntity organization =  currentUser.getOrganization();
  // check if the organization has already used trial version before
  if (!organization.isTrial){
    throw new RuntimeException("You can no longer use a trial subscription");
  }
  organization.setIstrial(true);

  organization.setSubscriptionDuration(5);
  organizationRepository.save(organization);
   
    return ResponseEntity.ok(Map.of("message", "your trial version has been activated for 5 days"));
  }
}

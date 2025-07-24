package com.api.AppointmentManagement.Controller;

@RestController
@RequestMapping("api/v1/org-admin")
@RequiredAllArgsConstructor
public class SubscriptionController{
  private final JwtUtil jwtUtil;
  private final OrganizationRepository organizationRepository;
  
 @GetMapping("/trial-subscription")
  public ResponseEntity<?> orgSubscription(){
   // get current user request and confirm if it’s the admin
     Optional<UserEntity> currentUser = jwtUtil.getCurrentUser();
      if (!currentUser.isEmpty()){
        UserEntity authUser = currentUser.get();
      }
     OrganizationEntity organization =  authUser.getOrganization();
  // check if the organization has already used trial version before
  if (!organization.isTrial){
    throw new RuntimeException("You can no longer use a trial subscription");
  }
  organization.setIstrial(true);

  organization.setSubscriptionDurationLeft(5);
  organizationRepository.save(organization);
   
    return ResponseEntity.ok(Map.of("message", "your trial version has been activated for 5 days"));
  }

  @PostMapping("pay-subscription")
  
  //implement subscription logic here
}

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
  public ResponseEntity<?> payOrgSubscription(@RequestParam SubscriptionDTO subscriptionDTO){
    Optional<UserEntity> currentUser = jwtUtil.getCurrentUser();

    if (subscriptionDTO == null){
       throw new IllegalArgumentException("subscription information cannot be empty");
    }

    if (Integer.valueOf(subscriptionDTO.getDuration()) == 30){
       BigDecimal price = 3000;
    }else if (Integer.valueOf(subscriptionDTO.getDuration()) == 90){
       BigDecimal price = 8000;
    }else if (String.valueOf(subscriptionDTO.getDuration()).equals("1 year")){
       BigDecimal price = 30000;
    }else{
      throw new IllegalArgumentException("invalid duration entered");
    }
    
    if (!currentUser.isEmpty()){
        UserEntity authUser = currentUser.get();
      } 
    OrganizationEntity organizationEntity = authUser.getOrganization();
    if (organizationEntity != null){
       CardPaymentRequestDTO paymentRequest = new CardPaymentRequestDTO();

CardPaymentRequestDTO.Customer customer = new CardPaymentRequestDTO.Customer();
customer.setEmail(subscriptionDTO.getEmail());
customer.setName(subscriptionDTO.getName());

paymentRequest.setTx_ref("TX-" + UUID.randomUUID().toString());
paymentRequest.setAmount(price);
paymentRequest.setCurrency("NGN");
paymentRequest.setRedirect_url("");
paymentRequest.setCustomer(customer);
paymentRequest.setPayment_options("card");

HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);
headers.setBearerAuth(FLW-SECRET-KEY);

HttpEntity<CardPaymentRequestDTO> request = new HttpEntity(paymentRequest, headers);
String flutterwaveUrl = "https://api.flutterwave.com/v3/payments";
      
ResponseEntity<String> response = restTemplate.postForEntity(flutterwaveUrl, request, String.class);

    return ResponseEntity
      .ok(response.getBody());     
    }
    


@RestControllerAdvice
public class GlobalExceptionHandler{
 @ExceptionHandler(AppointmentNotFoundException.class)
 public ResponseEntity<?> handleAppointmentNotFound(AppointmentNotFoundException ex){
   return new ResponseEntity<>(createErrorBody(ex.getMessage()), HttpSatus.BAD_REQUEST)
 }

 @ExceptionHandler(DoctorNotFoundException.class)
 public ResponseEntity<?> handleDoctorNotFound(DoctorNotFoundException ex){
   return new ResponseEntity<>(createErrorBody(ex.getMessage()), HttpStatus.BAD_REQUEST);
 }

 @ExceptionHandler(PatientNotFoundException.class)
 public ResponseEntity<?> handlePatientNotFound(PatientNotFoundException ex){
   return new ResponseEntity<>(createdErrorBody(ex.getMessage()), HttpStatus.BAD_REQUEST);
 }

 @ExceptionHandler(AppointmentIsEmptyException.class)
 public ResponseEntity<?> handle appointmentIsEmpty(AppointmentIsEmptyException ex){
  return new ResponseEntity<>(createErrorBody(ex.getMessage()), HttpStatus.BAD_REQUEST); 
 }

  @ExceptionHandler(RuntimeException.class)
 public ResponseEntity<?> handleAppointmentNotFound(RuntimeException ex){
    return new ResponseEntity<>(createErrorBody("a runtime exception has occured", HttpStatus.BAD_REQUEST);
  }
 
}
  

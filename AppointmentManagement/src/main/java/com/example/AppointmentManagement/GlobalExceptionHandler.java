
@RestControllerAdvice
public class GlobalExceptionHandler{
 @ExceptionHandler(AppointmentNotFoundException.class)
 public ResponseEntity<?> handleAppointmentNotFound(AppointmentNotFoundException ex){
   return new ResponseEntity<>(createErrorBody(ex.getMessage()), HttpSatus.BAD_REQUEST)
 }

 @ExceptionHandler(DoctorNotFoundException.class)
 public ResponseEntity<?> handleDoctorNotFound(DoctorNotFoundException ex){
   return new ResponseEntity<>(createErrorBody(ex.getMessage()), HttpStatus.BAD_REQUEST)
 }
}
  

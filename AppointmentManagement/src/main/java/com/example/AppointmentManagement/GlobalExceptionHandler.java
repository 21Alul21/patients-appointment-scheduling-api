
@RestControllerAdvice
public class GlobalExceptionHandler{
 @ExceptionHandler(AppointmentNotFoundException.class)
 public ResponseEntity<?> handleAppointmentNotFoud(AppointmentNotFoundException ex){
   return new ResponseEntity<>(createErrorBody(ex.getMessage()), HttpSatus.BAD_REQUEST)
 }
}
  

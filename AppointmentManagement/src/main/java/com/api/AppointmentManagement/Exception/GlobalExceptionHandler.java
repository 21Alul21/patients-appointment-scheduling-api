package com.api.AppointmentManagement.Exception;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<?> handleAppointmentNotFound(AppointmentNotFoundException ex) {
        return new ResponseEntity<>(createErrorBody(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<?> handleDoctorNotFound(DoctorNotFoundException ex) {
        return new ResponseEntity<>(createErrorBody(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<?> handlePatientNotFound(PatientNotFoundException ex) {
        return new ResponseEntity<>(createErrorBody(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppointmentIsEmptyException.class)
    public ResponseEntity<?> handleAppointmentIsEmpty(AppointmentIsEmptyException ex) {
        return new ResponseEntity<>(createErrorBody(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(createErrorBody("A runtime exception has occurred"), HttpStatus.BAD_REQUEST);
    }

    // Helper method to build error response body
    private Map<String, Object> createErrorBody(String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", message);
        return body;
    }
}

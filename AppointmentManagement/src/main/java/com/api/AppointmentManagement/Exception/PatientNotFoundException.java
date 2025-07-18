package com.api.AppointmentManagement.Exception;

public class PatientNotFoundException{
  public PatientNotFoundException(String message){
   super(message);
  }
  
  public PatientNotFoundException(String message, Throwable cause){
   super(message, cause)
  }
}

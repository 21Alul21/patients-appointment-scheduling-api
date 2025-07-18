package com.api.AppointmentManagement.Exception;

public class AppointmentIsEmptyException extends RuntimeException{
   public AppointmentIsEmptyException(String message){
    super(message);
   }

  public AppointmentIsEmptyException(String message, Throwable cause){
   super(message, cause);
  }
}

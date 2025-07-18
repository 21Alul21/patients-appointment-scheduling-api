package com.api.AppointmentManagement.DTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessageDTO {
    private String message;
    private String sender;
    private String appointmentId;
    private String timestamp;
}

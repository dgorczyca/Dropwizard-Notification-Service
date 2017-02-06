package com.example.notificationcentre.notifications.utility;

/**
 * Created by dg on 05/02/2017.
 */
public class NotificationFormatException extends RuntimeException {
    public NotificationFormatException(String message, RuntimeException e) {
        super(message,e);
    }
}

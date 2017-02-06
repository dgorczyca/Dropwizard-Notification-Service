package com.example.notificationcentre.notifications.api;

import java.util.Comparator;

/**
 * Created by dg on 06/02/2017.
 */
public class NotificationTimeStampComparator implements Comparator<Notification> {
    @Override
    public int compare(Notification n1, Notification n2) {
        if (n1.getEventTimestamp()< n2.getEventTimestamp()) {
            return -1;
        } else if (n1.getEventTimestamp() > n2.getEventTimestamp()) {
            return 1;
        }
        return 0;
    }
}

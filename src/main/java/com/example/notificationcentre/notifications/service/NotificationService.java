package com.example.notificationcentre.notifications.service;

import com.example.notificationcentre.notifications.api.Notification;
import com.example.notificationcentre.notifications.api.NotificationTimeStampComparator;
import com.example.notificationcentre.notifications.api.Status;
import com.example.notificationcentre.notifications.utility.CSVReader;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by dg on 05/02/2017.
 */
public class NotificationService {

    private static final String RESOURCE_PATH = "/csv/Notification Data.csv";

    static {
        List<Notification> notifications = CSVReader.readCSV(RESOURCE_PATH);
        Collections.sort(notifications, new NotificationTimeStampComparator());

        notificationList = notifications;
    }

    private static final List<Notification> notificationList;

    public NotificationService() {
    }

    public List<Notification> getUserNotifications(String userGuid) {
        return notificationList.stream().filter(notification -> notification.getUserGuid()
                .equals(userGuid)).collect(Collectors.toList());
    }

    public Optional<Notification> getNotification(String notificationGuid) {
        return notificationList.stream()
                .filter(notification -> notification.getNotificationGuid().equals(notificationGuid)).findAny();
    }

    public Optional<Notification> changeStatus(String notificationGuid, Status status) {
        Optional<Notification> notification = notificationList.stream()
                .filter(singleNotification -> singleNotification.getNotificationGuid().equals(notificationGuid)).findAny();
        if (notification.isPresent()) {
            notification.get().setStatus(status);
            return notification;
        }
        return notification;
    }

    public boolean deleteNotification(String notificationGuid) {
        return notificationList.removeIf(notification -> notification.getNotificationGuid().equals(notificationGuid));
    }

}

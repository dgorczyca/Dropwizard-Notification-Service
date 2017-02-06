package com.example.notificationcentre.notifications.utility;

import com.example.notificationcentre.notifications.api.EventSubType;
import com.example.notificationcentre.notifications.api.EventType;
import com.example.notificationcentre.notifications.api.Notification;
import com.example.notificationcentre.notifications.api.Status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class CSVReader {

    public static List<Notification> readCSV(String resourcePath) {
        List<Notification> notifications = null;

        try(InputStream inputStream = CSVReader.class.getResourceAsStream(resourcePath)){

            notifications = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .skip(1)
                    .map(CSVReader::parseLineIntoBean)
                    .collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }

        return notifications;
    }

    static Notification parseLineIntoBean(String csvLine) {
        String cvsSplitBy = ",";
        String[] data = csvLine.split(cvsSplitBy);
        Notification notification = new Notification();
        try {
            notification.setNotificationGuid(data[0]);
            notification.setDeviceGuid(data[1]);
            notification.setUserGuid(data[2]);
            notification.setEventType(EventType.valueOf(data[3]));
            if(isNotEmpty(data[4])) {
                notification.setEventSubtype(EventSubType.valueOf(data[4]));
            }
            if(isNotEmpty(data[5])) {
                notification.setGeofenceLat(new BigDecimal(data[5]));
            }
            if(isNotEmpty(data[6])) {
                notification.setGeofenceLon(new BigDecimal(data[6]));
            }
            if(isNotEmpty(data[7])) {
                notification.setGeofenceRadiusMetres(Integer.parseInt(data[7]));
            }
            notification.setTitle(data[8]);
            notification.setContent(data[9]);
            if(isNotEmpty(data[10])) {
                notification.setEventTimestamp(Double.parseDouble(data[10]));
            }
            if(isNotEmpty(data[11])) {
                notification.setSentTimestamp(Double.parseDouble(data[11]));
            }
            notification.setStatus(Status.UNREAD);
        } catch (RuntimeException e) {
            throw new NotificationFormatException("CSV Data Broken" ,e);
        }
        return notification;

    }

    static boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }
}

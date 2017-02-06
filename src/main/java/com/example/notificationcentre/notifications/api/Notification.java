package com.example.notificationcentre.notifications.api;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by dg on 04/02/2017.
 */
@Data
public class Notification {
    private String notificationGuid;
    private String deviceGuid;
    private String userGuid;
    private EventType eventType;
    private EventSubType eventSubtype;
    private BigDecimal geofenceLat;
    private BigDecimal geofenceLon;
    private Integer geofenceRadiusMetres;
    private String title;
    private String content;
    private Double eventTimestamp;
    private Double sentTimestamp;
    private Status status;
}

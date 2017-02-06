package com.example.notificationcentre.notifications.resources;

import com.example.notificationcentre.notifications.api.Notification;
import com.example.notificationcentre.notifications.api.Status;
import com.example.notificationcentre.notifications.service.NotificationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 * Created by dg on 05/02/2017.
 */
@Path("/api/")
@Produces(MediaType.APPLICATION_JSON)
public class NotificationResource {

    private final NotificationService notificationService;

    public NotificationResource(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GET
    @Path("/users/{userGuid}/notifications")
    public List<Notification> getUserNotifications(@PathParam("userGuid") String userGuidId) {
        List<Notification> userNotifiactions = notificationService.getUserNotifications(userGuidId);
        if (userNotifiactions.isEmpty())
            throw new WebApplicationException(404);
        return userNotifiactions;
    }

    @GET
    @Path("/notifications/{notificationGuid}")
    public Optional<Notification> getNotification(@PathParam("notificationGuid") String notificationGuid) {
        return notificationService.getNotification(notificationGuid);

    }

    @PUT
    @Path("/notifications/{notificationGuid}/status")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response markAsRead(@PathParam("notificationGuid") String notificationGuid, String stringStatus) {
        Status status;
        try {
            status = Status.valueOf(stringStatus);
        } catch (IllegalArgumentException e) {
            throw new WebApplicationException("Incorrect Status value", 400);
        }

        Optional<Notification> notification = notificationService.changeStatus(notificationGuid, status);
        if (!notification.isPresent())
            throw new WebApplicationException(404);

        return Response.accepted().build();
    }

    @DELETE
    @Path("/notifications/{notificationGuid}")
    public void deleteNotification(@PathParam("notificationGuid") String notificationGuid) {
        if (!notificationService.deleteNotification(notificationGuid))
            throw new WebApplicationException(404);
    }
}

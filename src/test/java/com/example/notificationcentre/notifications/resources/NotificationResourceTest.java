package com.example.notificationcentre.notifications.resources;

import com.example.notificationcentre.notifications.api.Notification;
import com.example.notificationcentre.notifications.api.Status;
import com.example.notificationcentre.notifications.service.NotificationService;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.rules.ExpectedException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
/**
 * Created by dg on 06/02/2017.
 */
public class NotificationResourceTest {

    private static final NotificationService notificationService = mock(NotificationService.class);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new NotificationResource(notificationService))
            .build();

    private final Notification notificationStub = new Notification();

    private final List<Notification> notificationList = new ArrayList<>();

    private final String notificationGuid = "532a20d3-aef0-4554-9bc1-9561dbd6151c";
    private final String userGuid = "286416b4-7eac-433c-876c-339dfd8bcd68";

    @Before
    public void setup() {
        notificationStub.setNotificationGuid(notificationGuid);
        notificationStub.setUserGuid(userGuid);
        notificationStub.setStatus(Status.UNREAD);
        notificationList.add(notificationStub);
    }

    @Test
    public void getUserNotifications() throws Exception {
        when(notificationService.getUserNotifications(userGuid)).thenReturn(notificationList);
        Response response = resources.client().target("/api/users/" + userGuid + "/notifications").request().get();

        verify(notificationService).getUserNotifications(userGuid);
        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
    }

    @Test
    public void changeStatusRequestCorrectShouldCallServiceChangeStatus() throws Exception {
        when(notificationService.changeStatus(notificationGuid, Status.READ)).thenReturn(Optional.of(notificationStub));

        Response response = resources.client().target("/api/notifications/" + notificationGuid + "/status").request().put(Entity.entity(Status.READ.name(), MediaType.TEXT_PLAIN));
        verify(notificationService).changeStatus(notificationGuid,Status.READ);
        assertThat(response.getStatus()).isEqualTo(Response.Status.ACCEPTED.getStatusCode());
    }

    @Test
    public void changeStatusRequestIncorrectValueShouldThrow400() {
        Response response = resources.client().target("/api/notifications/" + notificationGuid + "/status").request().put(Entity.entity("WRONG_VALUE", MediaType.TEXT_PLAIN));
        assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void getNotificationWhenResourceNotFoundShouldReturn404() throws Exception {
        expectedException.expect(javax.ws.rs.NotFoundException.class);
        resources.client().target("/api/notifications/DOES_NOT_EXIST_ID").request().get(Notification.class);
    }

    @Test
    public void getNotificationWhenResourceFoundShouldReturnResource( ) {
        when(notificationService.getNotification(notificationGuid)).thenReturn(Optional.of(notificationStub));

        Notification notification = resources.client().target("/api/notifications/" + notificationGuid).request().get(Notification.class);
        assertThat(notification).isNotNull();
        verify(notificationService).getNotification(notificationGuid);
    }

    @Test
    public void deleteNotification() throws Exception {
        when(notificationService.deleteNotification(notificationGuid)).thenReturn(true);

        resources.client().target("/api/notifications/" + notificationGuid).request().delete(Notification.class);
        verify(notificationService).deleteNotification(notificationGuid);
    }

}
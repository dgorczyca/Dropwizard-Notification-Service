package com.example.notificationcentre.notifications;

import com.example.notificationcentre.notifications.resources.NotificationResource;
import com.example.notificationcentre.notifications.health.ApplicationHealthCheck;
import com.example.notificationcentre.notifications.service.NotificationService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by dg on 05/02/2017.
 */
public class NotificationApplication extends Application<NotificationConfiguration> {

    public static void main(String[] args) throws Exception {
        new NotificationApplication().run(args);
    }
    @Override
    public void run(NotificationConfiguration configuration,
                    Environment environment) {
        NotificationService notificationService = new NotificationService();
        final NotificationResource notificationsResource = new NotificationResource(notificationService);
        environment.jersey().register(notificationsResource);
        final ApplicationHealthCheck healthCheck = new ApplicationHealthCheck();
        environment.healthChecks().register("application", healthCheck);
    }

    @Override
    public void initialize(Bootstrap<NotificationConfiguration> bootstrap) {

    }

}

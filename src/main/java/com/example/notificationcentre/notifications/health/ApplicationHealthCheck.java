package com.example.notificationcentre.notifications.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by dg on 05/02/2017.
 */
public class ApplicationHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}

package com.example.notificationcentre.notifications.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by dg on 05/02/2017.
 */
public class NotificationTest {
    @Test
    public void whenInstantiatingBeanAndMappingToJSONTest() throws JsonProcessingException {
        Notification notification = new Notification();
        notification.setContent("TestContent");

        String result = new ObjectMapper().writeValueAsString(notification);
        assertThat(result, hasJsonPath("content", equalTo("TestContent")));
    }
}
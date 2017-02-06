package com.example.notificationcentre.notifications.utility;

import com.example.notificationcentre.notifications.api.Notification;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by dg on 04/02/2017.
 */
public class CSVReaderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void readCSVShouldContainOneRowTest() throws URISyntaxException {
        List<Notification> notifications = CSVReader.readCSV("/csv/Notification Data.csv");
        assertThat(notifications.size(), is(1));
    }

    @Test
    public void readCSVFileWithHeadersOnlyShouldReturnZeroRowsTest() throws URISyntaxException {
        List<Notification> notifications = CSVReader.readCSV("/csv/No Data.csv");
        assertThat(notifications.size(), is(0));
    }

    @Test
    public void readCSVBrokenDataFileShouldThrowNotificationFormatException() throws URISyntaxException {
        expectedException.expect(NotificationFormatException.class);
        expectedException.expectMessage("CSV Data Broken");

        List<Notification> notifications = CSVReader.readCSV("/csv/Broken Data.csv");
    }

}

# Dropwizard-Notification-Service

The task given was to build an enterprise ready REST API Web Service that would underpin existing Notification Centre.
Following Dropwizzard's convention I put domain classess into API package, resources package contains web service end points controller.
Following Maven's standards tests and source code are in src folder in respective packages, the groupId of the project is com.example.notificationcentre.notifications, artifactId is notifications, utility package is for reading csv files.

CSVReader uses Java 8 streams, lambda expressions have also been used in the service class to manage the stream - sort, update, remove and get methods use those expressions to filter through the notification's list.

All end points have written tests, more about it below.
All endpoints have also been thoroughly tested manually using the Postman extension for chrome. Example urls are passed below with the description of web service's end points.

Tests have been written to confirm the following:
 - mapping of the notification object to JSON is correct
package: com.example.notificationcentre.notifications.api
class: NotificationTest

 - csv file reader works and handles empty files
package: com.example.notificationcentre.notifications.utility
class: CSVReaderTest


 - server tests done using Mockito
    - checked every end point
    - tests written including correct and wrong requests (confirmed excepted exceptions returned)
    - check service's methods are called with correct values and correct status codes are returned from the web service
package: com.example.notificationcentre.notifications.resources
class: NotificationResourceTest


RESTful API endpoints:

Get user's notifications ordered by event timestamp
HTTP Method : GET
URL : /api/users/{userGuid}/notifications
Returns : List of notifications that belong to a user (List<Notification>)
example URL : localhost:8080/api/users/286416b4-7eac-433c-876c-339dfd8bcd68/notifications
Response Code : 200, If not found : returns 404

Get a particular notification details
HTTP Method : GET
URL : /api/notifications/{notificationGuid}
Returns : Notification object serialised to JSON
example URL : localhost:8080/api/notifications/cad4a703-723b-49a3-aa0d-80efc82035a8
Response Code : 200, If not found : returns 404

Mark notification as read
HTTP Method : PUT
URL : /api/notifications/{notificationGuid}/status
Returns : Empty
example URL : localhost:8080/api/notifications/cad4a703-723b-49a3-aa0d-80efc82035a8/mark_as_read
expects body :  either value "READ" or "UNREAD"
Response Code : 202, If not found : returns 404

Delete notification
HTTP Method : Delete
URL : /api/notifications/{notificationGuid}
Returns : Empty
example URL : localhost:8080/api/notifications/cad4a703-723b-49a3-aa0d-80efc82035a8
Response Code : 204, If not found : returns 404



To run the project go into project's folder and run:
mvn clean && mvn package
java -jar target/notification-centre-1.0-SNAPSHOT.jar server

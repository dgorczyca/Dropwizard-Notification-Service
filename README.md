# Dropwizard-Notification-Service

The task given was to build an enterprise ready REST API Web Service that would underpin existing Notification Centre.
Following Dropwizzard's convention I put domain classess into API package, resources package contains web service end points controller.<br />
Following Maven's standards tests and source code are in src folder in respective packages, the groupId of the project is com.example.notificationcentre.notifications, artifactId is notifications, utility package is for reading csv files.<br /><br />
CSVReader uses Java 8 streams, lambda expressions have also been used in the service class to manage the stream - sort, update, remove and get methods use those expressions to filter through the notification's list.<br /><br />
All end points have written tests, more about it below.<br />
All endpoints have also been thoroughly tested manually using the Postman extension for chrome. Example urls are passed below with the description of web service's end points.<br /><br />
Tests have been written to confirm the following:<br />
 - mapping of the notification object to JSON is correct<br />
package: com.example.notificationcentre.notifications.api<br />
class: NotificationTest<br />
 - csv file reader works and handles empty files<br />
package: com.example.notificationcentre.notifications.utility<br />
class: CSVReaderTest<br /><br />
 - server tests done using Mockito<br />
    - checked every end point<br />
    - tests written including correct and wrong requests (confirmed excepted exceptions returned)<br />
    - check service's methods are called with correct values and correct status codes are returned from the web service<br />
package: com.example.notificationcentre.notifications.resources<br />
class: NotificationResourceTest<br />
<br /><br />
RESTful API endpoints:<br />

Get user's notifications ordered by event timestamp<br />
HTTP Method : GET<br />
URL : /api/users/{userGuid}/notifications<br />
Returns : List of notifications that belong to a user (List<Notification>)<br />
example URL : localhost:8080/api/users/286416b4-7eac-433c-876c-339dfd8bcd68/notifications<br />
Response Code : 200, If not found : returns 404<br /><br /><br />
Get a particular notification details<br />
HTTP Method : GET<br />
URL : /api/notifications/{notificationGuid}<br />
Returns : Notification object serialised to JSON<br />
example URL : localhost:8080/api/notifications/cad4a703-723b-49a3-aa0d-80efc82035a8<br />
Response Code : 200, If not found : returns 404<br /><br />
Mark notification as read<br />
HTTP Method : PUT<br />
URL : /api/notifications/{notificationGuid}/status<br />
Returns : Empty<br />
example URL : localhost:8080/api/notifications/cad4a703-723b-49a3-aa0d-80efc82035a8/status<br />
expects body :  either value "READ" or "UNREAD"<br />
Response Code : 202, If not found : returns 404<br /><br />
Delete notification<br />
HTTP Method : Delete<br />
URL : /api/notifications/{notificationGuid}<br />
Returns : Empty<br />
example URL : localhost:8080/api/notifications/cad4a703-723b-49a3-aa0d-80efc82035a8<br />
Response Code : 204, If not found : returns 404<br /><br /><br />



To run the project go into project's folder and run:<br />
mvn clean && mvn package<br />
java -jar target/notification-centre-1.0-SNAPSHOT.jar server

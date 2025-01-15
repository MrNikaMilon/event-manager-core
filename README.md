## About Project
This project fulfills Company X's need to create and manage events and various locations.
The project contains two key services:
* Event and location management service:[Event Managment Core](https://github.com/MrNikaMilon/event-manager-core)
* Service of notifications of users on various events: link

## Technological stack of the project
* **Backend**: **Java 21**, **Spring** (**Spring Boot**, **Spring Boot MVC**, **Spring Boot JPA**)
* **Data Base**: **Hibernate** (*for ORM*), **Liquibase** (*for DB migration*), **PostgreSQL**
* **Authentication and Authorization**: **Spring Security**, **JWT**, **OAuth2**
* **Notifications**: **Apache Kafka** *for async messaging between services*
* **Documentations**: **Swagger**, **OpenApi**
* **Containerization**: **Docker**, **Docker Compose**, *deployment in* **Kubernetes**
* **Testing**: **JUnit 5**, **Mockito**, **Postman**/**Insomnia**

## Subject area of the project
The service realizes interaction between users with Events and Locations, providing extensive functionality for creating, modifying, managing events and locations.
  
### There are two roles available in the project:
  *	*ADMIN* - can create and track Events and Locations as well as have access to approve requests for new Locations and advanced work with Locations.
  *	*USER* - user can perform CRUD operations with Events, and send requests to work with Locations (for example, add a new location).

### The following key domains have been implemented in the project domain area:
*	Event – key model, stores information about the event: 
    * title
    * start time, end time,
    * location reference,
    * description
    * the cost of the event (optionally it can be a rally),
    * event name,
    * event type (for this purpose the system has a directory with event types),
    * user of the event creator (or admin)
    * list of users associated with the event
      
*	Location – A model that stores location information:
    * location name
    * location address
    * capacity (optional)
    * booking fee (optional)
    * list of scheduled Events
    * details
    * date of last booking
 
* User – user model for security:
    * name
    * mailing address
    * password
    * role (directory)
    * list of Events
    * list of created Events
      
## Link
The link to the documentation in Notion: [Documentation by project](https://agate-farm-07d.notion.site/Event-service-App-176bc1405ef38068a4dec4bdb47037da?pvs=4)

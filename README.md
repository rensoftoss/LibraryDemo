# Library Demo

Welcome to the LibraryDemo repository. This is sample application for storing books in MongoDB.
Displaying weather information for a given city is also possible with this application.

## Introduction
The application uses the following technologies:

* Java 11
* Spring Boot (Spring WebFlux)
* REST
* MongoDB
* Integration with 3rd party tool to get actual temperature for a given city

## Compiling the application

1. Download and install Maven 3.6.3

2. Download and install Oracle JDK 11

3. Compiling without running unit tests:

`./mvnw clean package -DskipTests=true`

To check the compilation by running unit tests:

`./mvnw clean package`

## Running the application
1. Execute the following command to start a MongoDB instance in a Docker container.

`docker-compose -f docker/library.yml up -d`

2. Run the application from the target folder:

`java -jar target/LibraryDemo-0.0.1.jar`

## Usage details

Using Weather API as a third party tool to get weather information:
[Weather API](https://www.weatherapi.com/)

The usage of the API is described by the Postman collection:
[Library Postman Collection](docs/Library.postman_collection.json)

Example requests of service endpoints:
* `GET http://localhost:9090/books`
* `GET http://localhost:9090/weather?city=Berlin`

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.1/maven-plugin/reference/html/#build-image)
* [Spring Data Reactive MongoDB](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#boot-features-mongodb)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#using-boot-devtools)


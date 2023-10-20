# REST API demo with Open API for Medical Insurance service

This is the sample of a RESTful service built with Spring Boot and PostgreSQL. It shows the integration of Spring Data with JPA and Hibernate.
The created endpoints demonstrate the automatic generation of CRUD functionality for the entity class providing by JPA repository interface. The Endpoints also have examples of JPQL and native queries.
REST API documentation based on the OpenAPI 3 specification. You can use the Swagger UI to interact with API specification and exercise the endpoints.

## Stack

* Java 17
* Spring Boot 3.2
* Spring Data JPA
* Hibernate
* Open API 3
* PostgreSQL
* Lombok
* Maven

## Quick Start

### 1. Clone this repository

### 2. Create database 'patientsdb'
I use PostgreSQL in this project, but you can choose another DBMS.

Change the application settings connecting with using database.
```properties
# src/main/resources/application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/patientsdb
spring.datasource.username=postgres
spring.datasource.password=admin
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### 3. Change port
```properties
# src/main/resources/application.properties
server.port=8081
```

### 4. Start application
After starting the application tables 'person' and 'policy' will be created in database.

There are two sql-files: person.sql, policy.sql in src/main/resources/data. You can execute these scripts or fill the tables by yourself.

### 5. Browse swagger
Use Swagger UI to interact with API specification and exercise the endpoints http://localhost:8081/swagger-ui/index.html

## REST API Endpoints
POST
```
/api/new-person -> Add new person
```

GET
```
/api/person/{id} -> Find person by id

/api/all-persons -> Get all persons

/api/persons-by-names-start-text/{name} -> Find persons by starting text of lastname, firstname or secondname

/api/persons-18-years-and-older -> Get adult persons

/api/person-by-policy -> Find person by policy

/api/persons-with-temporary-policy -> Get persons with temporary policy

/api/persons-with-several-policies -> Get persons with several policies
```

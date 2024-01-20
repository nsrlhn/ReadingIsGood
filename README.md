# ReadingIsGood Book Delivery System

## Running

```
mvn package

java -jar target/ReadingIsGood-0.0.1-SNAPSHOT.jar
```

## Features

- Book prices can be updated. To not lose the history, purchase prices are also stored under OrderItems
- During the stock update processes, related data in database will be locked.
- More than one type of books can be ordered at once. In case of error, the order will be cancelled as a whole.

## Technologies

- Java 17
- Spring Framework & Spring Boot
- H2 Database

## Development

- Swagger Path: http://localhost:8080/swagger-ui/index.html
- H2 DB Path: http://localhost:8080/h2-console/ -> Credential available in application.yml
- To activate initial example data add VM option: -Dspring.profiles.active=local

## TODO

### General

- secure endpoints, bearer token
- Log all changes on entities. (Which user made specific changes and when)
- spring.jpa.open-in-view is enabled by default?
- Improve error messages threw by Optional
- Return dto instead of entities
- error response models
- Postman request
- Test ( Unit & Integration - min %50 functionality coverage )

### Book

- Book may be generalized as Product
- Author should be added to Book
- unique constraint to Book.name

### Customer

- Add pagination to CustomerController.getOrders
# ReadingIsGood Book Delivery System

## Running

Create jar package with maven:

```
mvn clean package
```

Create docker-compose.yml:

```
version: '3.2'

services:  
    readIsGood:
        build: {Path_To_Dockerfile}
        restart: always
        ports: {Exported_Port}:8080
```

Run Docker Compose:

```
docker compose up readIsGood --build -d
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
- Sample exception messages available in /sample/response folder

## TODO

### General

- Log all changes on entities. (Which user made specific changes and when)
- Return dto instead of entities
- Postman request

### Book

- Book may be generalized as Product
- Author should be added to Book
- Stock information may be stored somewhere else

### Statistic

- A separate entity may be created for statistic to improve performance

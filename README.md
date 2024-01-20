# ReadingIsGood Book Delivery System

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

- Logging
- spring.jpa.open-in-view is enabled by default?
- Improve error messages threw by Optional
- Return dto instead of entities
- Money decimal needed to be validated

### Book

- Book may be generalized as Product
- Author should be added to Book
- If the feature "decreasing book stock" is necessary. Concurrency should be considered
- Update stock is dangerous
- unique constraint to Book.name

### Customer

- Add pagination to CustomerController.getOrders
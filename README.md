# ReadingIsGood Book Delivery System

## Technologies

- Java 17
- Spring Framework & Spring Boot
- H2 Database

## Development

- Swagger Path: http://localhost:8080/swagger-ui/index.html
- H2 DB Path: http://localhost:8080/h2-console/ -> Credential available in application.yml

## TODO

- Logging
- Book may be generalized as Product
- spring.jpa.open-in-view is enabled by default?
- It is better to throw exception in controller for too long strings
- Improve error messages threw by Optional
- Add pagination to CustomerController.getOrders
- Author should be added to Book
- If the feature "decreasing book stock" is necessary. Concurrency should be considered
- In ordering, consider locking table for multiple pods
- unique constraint to Book.name
- order get apis should return orderList too
- Return dto instead of entities
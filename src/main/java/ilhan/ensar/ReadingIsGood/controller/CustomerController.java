package ilhan.ensar.ReadingIsGood.controller;

import ilhan.ensar.ReadingIsGood.controller.request.CustomerPostRequest;
import ilhan.ensar.ReadingIsGood.model.Customer;
import ilhan.ensar.ReadingIsGood.model.Order;
import ilhan.ensar.ReadingIsGood.service.CustomerService;
import ilhan.ensar.ReadingIsGood.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"customer"})
@Tag(name = "Customer")
public class CustomerController {

    private final CustomerService service;
    private final OrderService orderService;

    @PostMapping
    @Operation(description = "Persist new customer")
    public Customer persist(@Valid @RequestBody CustomerPostRequest request) {
        return service.persist(request);
    }

    @GetMapping("{id}/getOrders")
    @Operation(description = "Query all orders of the customer")
    public List<Order> getOrders(@PathVariable Long id) {
        Customer customer = service.getOrThrow(id);

        return orderService.getAll(customer);
    }

    @GetMapping("{id}")
    @Operation(description = "Get the customer")
    public Customer get(@PathVariable Long id) {
        return service.getOrThrow(id);
    }
}

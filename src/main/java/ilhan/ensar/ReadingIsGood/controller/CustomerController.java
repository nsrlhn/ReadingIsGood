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
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
    public Page<Order> getOrders(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Customer customer = service.getOrThrow(id);

        return orderService.getAll(customer, page, size);
    }

    @GetMapping("{id}")
    @Operation(description = "Get the customer")
    public Customer get(@PathVariable Long id) {
        return service.getOrThrow(id);
    }
}

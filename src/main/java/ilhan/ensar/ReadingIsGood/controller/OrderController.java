package ilhan.ensar.ReadingIsGood.controller;

import ilhan.ensar.ReadingIsGood.controller.request.OrderPostRequest;
import ilhan.ensar.ReadingIsGood.model.Order;
import ilhan.ensar.ReadingIsGood.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"order"})
@Tag(name = "Order")
public class OrderController {

    private final OrderService service;

    @PostMapping
    @Operation(description = "Persist new order")
    public Order make(@Valid @RequestBody OrderPostRequest request) {
        return service.make(request);
    }

    @GetMapping("{id}")
    @Operation(description = "Get the order")
    public Order get(@PathVariable Long id) {
        return service.getOrThrow(id);
    }

    @GetMapping
    @Operation(description = "Get orders by date")
    public List<Order> search(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        return service.getAll(from, to);
    }
}

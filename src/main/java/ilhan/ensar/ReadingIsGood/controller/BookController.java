package ilhan.ensar.ReadingIsGood.controller;

import ilhan.ensar.ReadingIsGood.controller.request.BookPostRequest;
import ilhan.ensar.ReadingIsGood.model.Book;
import ilhan.ensar.ReadingIsGood.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"book"})
@Tag(name = "Book")
public class BookController {

    private final BookService service;

    @PostMapping
    @Operation(description = "Persist new customer")
    public Book persist(@Valid @RequestBody BookPostRequest request) {
        return service.persist(request);
    }

    @PatchMapping("{id}/increaseStock")
    @Operation(description = "Increase book stock")
    public Book increaseStock(@PathVariable Long id, @Min(1) @RequestParam Integer changeAmount) {
        return service.increaseStock(id, changeAmount);
    }

    @PatchMapping("{id}/decreaseStock")
    @Operation(description = "Decrease book stock")
    public Book decreaseStock(@PathVariable Long id, @Min(1) @RequestParam Integer changeAmount) {
        return service.decreaseStock(id, changeAmount);
    }

    @GetMapping("{id}")
    @Operation(description = "Get the book")
    public Book get(@PathVariable Long id) {
        return service.getOrThrow(id);
    }
}

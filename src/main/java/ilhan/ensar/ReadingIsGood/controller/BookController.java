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

    @PatchMapping("{id}")
    @Operation(description = "Update book stock")
    public Book updateStock(@PathVariable Long id, @Min(0) @RequestParam Integer newStockAmount) {
        return service.updateStock(id, newStockAmount);
    }
}

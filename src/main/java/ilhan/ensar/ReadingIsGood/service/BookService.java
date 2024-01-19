package ilhan.ensar.ReadingIsGood.service;

import ilhan.ensar.ReadingIsGood.controller.request.BookPatchRequest;
import ilhan.ensar.ReadingIsGood.controller.request.BookPostRequest;
import ilhan.ensar.ReadingIsGood.model.Book;
import ilhan.ensar.ReadingIsGood.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService implements BaseCRUDService<Book> {

    private final BookRepository repository;

    @Override
    public Book getOrThrow(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Book persist(BookPostRequest request) {
        // Prepare
        Book book = new Book(request);

        // Save
        return repository.save(book);
    }

    public Book patch(Long id, BookPatchRequest request) {
        // Get
        Book book = this.getOrThrow(id);

        // Prepare
        book.addStock(request.getAddAmount());

        // Save
        return repository.save(book);
    }
}

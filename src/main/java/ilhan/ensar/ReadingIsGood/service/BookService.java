package ilhan.ensar.ReadingIsGood.service;

import ilhan.ensar.ReadingIsGood.controller.request.BookPostRequest;
import ilhan.ensar.ReadingIsGood.model.Book;
import ilhan.ensar.ReadingIsGood.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Book updateStock(Book book, int newAmount) {
        // Prepare
        book.setAvailableAmount(newAmount);

        // Save
        return repository.save(book);
    }

    @Transactional
    public Book updateStock(Long id, Integer newStockAmount) {
        // Get & Lock
        Book book = getAndLock(id);

        // Save
        return this.updateStock(book, newStockAmount);
    }

    @Transactional
    public Book getAndLock(Long id) {
        return repository.findAndLockById(id).orElseThrow();
    }
}

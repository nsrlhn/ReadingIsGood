package ilhan.ensar.ReadingIsGood.service;

import ilhan.ensar.ReadingIsGood.controller.request.BookPostRequest;
import ilhan.ensar.ReadingIsGood.exception.BusinessLogicException;
import ilhan.ensar.ReadingIsGood.exception.NotFoundException;
import ilhan.ensar.ReadingIsGood.model.Book;
import ilhan.ensar.ReadingIsGood.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public Book persist(BookPostRequest request) {
        // Prepare
        Book book = new Book(request);

        // Save
        return repository.save(book);
    }

    @Transactional
    public Book getAndLock(Long id) {
        return repository.findAndLockById(id).orElseThrow(() -> new NotFoundException("Book(id=" + id + ") is not found."));
    }

    @Transactional
    public Book increaseStock(Long id, Integer changeAmount) {
        // Get & Lock
        Book book = getAndLock(id);

        // Prepare
        int newAmount = book.getAvailableAmount() + changeAmount;
        book.setAvailableAmount(newAmount);

        // Update
        return repository.save(book);
    }

    @Transactional
    public Book decreaseStock(Long id, Integer decrement) {
        // Get & Lock
        Book book = getAndLock(id);

        // Check
        int newAmount = book.getAvailableAmount() - decrement;
        if (newAmount < 0) {
            throw new BusinessLogicException("There is not enough stock. availableAmount: " + book.getAvailableAmount() + " | decrement: " + decrement);
        }

        // Prepare
        book.setAvailableAmount(newAmount);

        // Update
        return repository.save(book);
    }
}

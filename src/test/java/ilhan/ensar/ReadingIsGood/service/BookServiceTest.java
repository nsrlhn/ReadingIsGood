package ilhan.ensar.ReadingIsGood.service;

import ilhan.ensar.ReadingIsGood.controller.request.BookPostRequest;
import ilhan.ensar.ReadingIsGood.exception.BusinessLogicException;
import ilhan.ensar.ReadingIsGood.exception.NotFoundException;
import ilhan.ensar.ReadingIsGood.model.Book;
import ilhan.ensar.ReadingIsGood.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
class BookServiceTest {

    private final long id = 1L;
    private final long unavailableId = 2L;
    private final int availableAmount = 10;
    private final int changeAmount = 5;
    private final int excessiveAmount = availableAmount + 1;

    @Autowired
    private BookService service;
    @MockBean
    private BookRepository repository;
    @Captor
    private ArgumentCaptor<Book> bookCapture;

    @BeforeEach
    public void arrange() {
        BookPostRequest request = new BookPostRequest();
        request.setName("A Clockwork Orange");
        request.setPrice(BigDecimal.TEN);
        request.setAvailableAmount(availableAmount);

        Book book = new Book(request);

        Mockito.when(repository.findAndLockById(id)).thenReturn(Optional.of(book));
    }

    @Test
    void increaseStock_ShouldUpdateEntity() {
        // Act
        service.increaseStock(id, changeAmount);

        // Assert
        verify(repository).save(bookCapture.capture());
        Book book = bookCapture.getValue();

        assertThat(book.getAvailableAmount()).isEqualTo(availableAmount + changeAmount);
    }

    @Test
    void decreaseStock_ShouldUpdateEntity() {
        // Act
        service.decreaseStock(id, changeAmount);

        // Assert
        verify(repository).save(bookCapture.capture());
        Book book = bookCapture.getValue();

        assertThat(book.getAvailableAmount()).isEqualTo(availableAmount - changeAmount);
    }

    @Test
    void givenNotEnoughStock_WhenDecreaseStock_ShouldThrowException() {
        // Act
        Exception exception = assertThrows(BusinessLogicException.class, () -> service.decreaseStock(id, excessiveAmount));

        // Assert
        verify(repository, never()).save(any());

        assertThat(exception.getMessage()).contains("There is not enough stock");
    }

    @Test
    void givenNotAvailableId_WhenGetAndLock_ShouldThrowException() {
        // Act
        Exception exception = assertThrows(NotFoundException.class, () -> service.getAndLock(unavailableId));

        // Assert
        assertThat(exception.getMessage()).contains("not found");
    }
}
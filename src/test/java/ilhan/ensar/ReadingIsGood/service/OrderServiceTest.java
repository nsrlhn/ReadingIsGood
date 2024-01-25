package ilhan.ensar.ReadingIsGood.service;

import ilhan.ensar.ReadingIsGood.exception.NotFoundException;
import ilhan.ensar.ReadingIsGood.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class OrderServiceTest {

    private final long unavailableId = 2L;

    @Autowired
    private OrderService service;
    @MockBean
    private OrderRepository repository;

    @Test
    void givenNotAvailableId_WhenGetOrThrow_ShouldThrowException() {
        // Act
        Exception exception = assertThrows(NotFoundException.class, () -> service.getOrThrow(unavailableId));

        // Assert
        assertThat(exception.getMessage()).contains("not found");
    }
}
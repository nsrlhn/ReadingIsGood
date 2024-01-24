package ilhan.ensar.ReadingIsGood.service;

import ilhan.ensar.ReadingIsGood.exception.NotFoundException;
import ilhan.ensar.ReadingIsGood.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerServiceTest {

    private final long unavailableId = 2L;

    @Autowired
    private CustomerService service;
    @MockBean
    private CustomerRepository repository;

    @Test
    void givenNotAvailableId_WhenGetOrThrow_ShouldThrowException() {
        // Act
        Exception exception = assertThrows(NotFoundException.class, () -> service.getOrThrow(unavailableId));

        // Assert
        assertThat(exception.getMessage()).contains("not found");
    }
}

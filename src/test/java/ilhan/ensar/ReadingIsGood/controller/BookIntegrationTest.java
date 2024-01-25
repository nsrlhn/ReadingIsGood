package ilhan.ensar.ReadingIsGood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import ilhan.ensar.ReadingIsGood.controller.request.BookPostRequest;
import ilhan.ensar.ReadingIsGood.model.Book;
import ilhan.ensar.ReadingIsGood.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class BookIntegrationTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private BookRepository repository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void persist() throws Exception {
        // Arrange
        BookPostRequest request = new BookPostRequest();
        request.setName("The Metamorphosis");
        request.setAvailableAmount(1000);
        request.setPrice(BigDecimal.TEN);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(request);

        // Act
        byte[] responseBody = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsByteArray();

        ObjectReader or = new ObjectMapper().readerFor(Book.class);
        Book savedEntity = or.readValue(responseBody);

        // Assert
        assertThat(repository.existsById(savedEntity.getId())).isTrue();
    }

    @Test
    public void increaseStock() throws Exception {
        Long id = 1L;

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/book/" + id + "/increaseStock")
                        .param("changeAmount", "100"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        assertThat(repository.findById(id).get().getAvailableAmount()).isEqualTo(1100);
    }

    @Test
    public void decreaseStock() throws Exception {
        Long id = 2L;

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/book/" + id + "/decreaseStock")
                        .param("changeAmount", "100"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        assertThat(repository.findById(id).get().getAvailableAmount()).isEqualTo(900);
    }

    @Test
    public void givenExcessiveAmount_whenDecreaseStock_shouldThrow() throws Exception {
        Long id = 3L;

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/book/" + id + "/decreaseStock")
                        .param("changeAmount", "10000"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}

package ilhan.ensar.ReadingIsGood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ilhan.ensar.ReadingIsGood.controller.request.OrderItemPostRequest;
import ilhan.ensar.ReadingIsGood.controller.request.OrderPostRequest;
import ilhan.ensar.ReadingIsGood.model.Order;
import ilhan.ensar.ReadingIsGood.repository.OrderRepository;
import org.assertj.core.api.Assertions;
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

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class OrderIntegrationTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private OrderRepository repository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void get() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/order/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void search() throws Exception {
        String date = "2024-01-25";

        // Act
        byte[] responseBody = this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/order/dateBetween")
                        .param("from", date)
                        .param("to", date))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsByteArray();

        // Assert
        ObjectReader or = new ObjectMapper().readerFor(List.class);
        List<Map<String, String>> response = or.readValue(responseBody);

        Assertions.assertThat(response).allMatch(r -> r.get("date").equals(date));
    }

    @Test
    public void place() throws Exception {
        // Arrange
        OrderItemPostRequest orderItemPostRequest = new OrderItemPostRequest();
        orderItemPostRequest.setBookId(1L);
        orderItemPostRequest.setAmount(5);

        OrderPostRequest request = new OrderPostRequest();
        request.setCustomerId(1L);
        request.setStatus(Order.Status.COMPLETED);
        request.setOrderItemPostRequestList(List.of(orderItemPostRequest));

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(request);

        // Act
        byte[] responseBody = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsByteArray();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Order savedEntity = mapper.readValue(responseBody, Order.class);

        // Assert
        assertThat(repository.existsById(savedEntity.getId())).isTrue();
    }

    @Test
    public void givenExcessiveAmount_whenPlacingOrder_shouldThrow() throws Exception {
        // Arrange
        OrderItemPostRequest orderItemPostRequest = new OrderItemPostRequest();
        orderItemPostRequest.setBookId(1L);
        orderItemPostRequest.setAmount(10001);

        OrderPostRequest request = new OrderPostRequest();
        request.setCustomerId(1L);
        request.setStatus(Order.Status.COMPLETED);
        request.setOrderItemPostRequestList(List.of(orderItemPostRequest));

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(request);

        // Act
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
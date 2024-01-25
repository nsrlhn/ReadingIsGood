package ilhan.ensar.ReadingIsGood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import ilhan.ensar.ReadingIsGood.controller.request.CustomerPostRequest;
import ilhan.ensar.ReadingIsGood.model.Customer;
import ilhan.ensar.ReadingIsGood.repository.CustomerRepository;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CustomerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private CustomerRepository repository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void persist() throws Exception {
        // Arrange
        CustomerPostRequest request = new CustomerPostRequest();
        request.setFirstName("Ensar");
        request.setFamilyName("ILHAN");
        request.setMail("nsrlhn@gmail.com");
        request.setPassword("password");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(request);

        // Act
        byte[] responseBody = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsByteArray();

        ObjectReader or = new ObjectMapper().readerFor(Customer.class);
        Customer savedEntity = or.readValue(responseBody);

        // Assert
        assertThat(repository.existsById(savedEntity.getId())).isTrue();
    }

    @Test
    public void get() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/customer/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getOrders() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/customer/1/orders"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

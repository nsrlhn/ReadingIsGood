package ilhan.ensar.ReadingIsGood.controller;

import ilhan.ensar.ReadingIsGood.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class StatisticIntegrationTest {

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
    public void getCustomerMonthlyOrder() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/statistic/customerMonthlyOrder/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }
}

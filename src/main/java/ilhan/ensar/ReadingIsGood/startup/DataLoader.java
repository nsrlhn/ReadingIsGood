package ilhan.ensar.ReadingIsGood.startup;

import ilhan.ensar.ReadingIsGood.controller.request.BookPostRequest;
import ilhan.ensar.ReadingIsGood.controller.request.CustomerPostRequest;
import ilhan.ensar.ReadingIsGood.controller.request.OrderItemPostRequest;
import ilhan.ensar.ReadingIsGood.controller.request.OrderPostRequest;
import ilhan.ensar.ReadingIsGood.model.Order;
import ilhan.ensar.ReadingIsGood.service.BookService;
import ilhan.ensar.ReadingIsGood.service.CustomerService;
import ilhan.ensar.ReadingIsGood.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Profile("local")
@Component
@RequiredArgsConstructor
public class DataLoader {

    private final BookService bookService;
    private final CustomerService customerService;
    private final OrderService orderService;

    @EventListener(ApplicationReadyEvent.class)
    public void load() {
        customerService.persist(prepareCustomerRequest());
        bookService.persist(prepareBookRequest("The Mathematical Principles of Natural Philosophy"));
        bookService.persist(prepareBookRequest("The Human Zoo"));
        orderService.place(prepareOrderRequest());

        log.info("Example data loaded");
    }

    private OrderPostRequest prepareOrderRequest() {
        OrderItemPostRequest orderItemPostRequest1 = new OrderItemPostRequest();
        orderItemPostRequest1.setBookId(1L);
        orderItemPostRequest1.setAmount(1);

        OrderItemPostRequest orderItemPostRequest2 = new OrderItemPostRequest();
        orderItemPostRequest2.setBookId(2L);
        orderItemPostRequest2.setAmount(2);

        List<OrderItemPostRequest> orderItemPostRequestList = new ArrayList<>();
        orderItemPostRequestList.add(orderItemPostRequest1);
        orderItemPostRequestList.add(orderItemPostRequest2);

        OrderPostRequest request = new OrderPostRequest();
        request.setCustomerId(1L);
        request.setStatus(Order.Status.COMPLETED);
        request.setOrderItemPostRequestList(orderItemPostRequestList);
        return request;
    }

    private BookPostRequest prepareBookRequest(String name) {
        BookPostRequest request = new BookPostRequest();
        request.setName(name);
        request.setAvailableAmount(10000);
        request.setPrice(new BigDecimal("149.99"));
        return request;
    }

    private CustomerPostRequest prepareCustomerRequest() {
        CustomerPostRequest request = new CustomerPostRequest();
        request.setFirstName("Ensar");
        request.setFamilyName("ILHAN");
        return request;
    }
}
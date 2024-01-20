package ilhan.ensar.ReadingIsGood.service;

import ilhan.ensar.ReadingIsGood.controller.request.OrderPostRequest;
import ilhan.ensar.ReadingIsGood.model.Customer;
import ilhan.ensar.ReadingIsGood.model.Order;
import ilhan.ensar.ReadingIsGood.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements BaseCRUDService<Order> {

    private final OrderRepository repository;
    private final CustomerService customerService;
    private final OrderItemService orderItemService;

    public List<Order> getAll(Customer customer) {
        return repository.findAllByCustomer(customer);
    }

    @Transactional
    public Order make(OrderPostRequest request) {
        // Get: Customer
        Customer customer = customerService.getOrThrow(request.getCustomerId());

        // Prepare: Order
        Order order = new Order(customer, request);

        // Save: Order
        order = repository.save(order);

        // Save: OrderItems
        orderItemService.saveAll(order, request.getOrderItemPostRequestList());

        return order;
    }

    @Override
    public Order getOrThrow(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Order> search(LocalDate from, LocalDate to) {
        return repository.findByDateBetween(from, to);
    }
}

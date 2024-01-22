package ilhan.ensar.ReadingIsGood.service;

import ilhan.ensar.ReadingIsGood.controller.request.OrderPostRequest;
import ilhan.ensar.ReadingIsGood.exception.NotFoundException;
import ilhan.ensar.ReadingIsGood.model.Customer;
import ilhan.ensar.ReadingIsGood.model.Order;
import ilhan.ensar.ReadingIsGood.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerService customerService;
    private final OrderItemService orderItemService;

    @Transactional
    public Order place(OrderPostRequest request) {
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

    public Order getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Order(id=" + id + ") is not found."));
    }

    public List<Order> getAll(Customer customer) {
        return repository.findAllByCustomer(customer);
    }

    public Page<Order> getAll(Customer customer, int page, int size) {
        return repository.findAllByCustomer(customer, PageRequest.of(page, size));
    }

    public List<Order> getAll(LocalDate from, LocalDate to) {
        return repository.findByDateBetween(from, to);
    }
}

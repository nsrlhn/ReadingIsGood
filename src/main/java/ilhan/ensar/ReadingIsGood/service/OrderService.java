package ilhan.ensar.ReadingIsGood.service;

import ilhan.ensar.ReadingIsGood.model.Customer;
import ilhan.ensar.ReadingIsGood.model.Order;
import ilhan.ensar.ReadingIsGood.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public List<Order> getAll(Customer customer) {
        return repository.findAllByCustomer(customer);
    }
}

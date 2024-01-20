package ilhan.ensar.ReadingIsGood.repository;

import ilhan.ensar.ReadingIsGood.model.Customer;
import ilhan.ensar.ReadingIsGood.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {

    List<Order> findAllByCustomer(Customer customer);

    Page<Order> findAllByCustomer(Customer customer, Pageable pageable);

    List<Order> findByDateBetween(LocalDate from, LocalDate to);
}

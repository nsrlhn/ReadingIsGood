package ilhan.ensar.ReadingIsGood.repository;

import ilhan.ensar.ReadingIsGood.model.Customer;
import ilhan.ensar.ReadingIsGood.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {

    List<Order> findAllByCustomer(Customer customer);

    List<Order> findByDateBetween(LocalDate from, LocalDate to);
}

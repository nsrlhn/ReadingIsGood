package ilhan.ensar.ReadingIsGood.repository;

import ilhan.ensar.ReadingIsGood.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends BaseRepository<Customer> {

    Optional<Customer> findByMail(String mail);

    boolean existsByMail(String mail);
}

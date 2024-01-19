package ilhan.ensar.ReadingIsGood.service;

import ilhan.ensar.ReadingIsGood.controller.request.CustomerPostRequest;
import ilhan.ensar.ReadingIsGood.model.Customer;
import ilhan.ensar.ReadingIsGood.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService implements BaseCRUDService<Customer> {

    private final CustomerRepository repository;

    public Customer persist(CustomerPostRequest request) {
        // Prepare
        Customer customer = new Customer(request);

        // Save
        return repository.save(customer);
    }

    @Override
    public Customer getOrThrow(Long id) {
        return repository.findById(id).orElseThrow();
    }
}

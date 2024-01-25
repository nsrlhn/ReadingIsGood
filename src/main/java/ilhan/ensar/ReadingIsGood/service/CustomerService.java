package ilhan.ensar.ReadingIsGood.service;

import ilhan.ensar.ReadingIsGood.controller.request.CustomerPostRequest;
import ilhan.ensar.ReadingIsGood.exception.BusinessLogicException;
import ilhan.ensar.ReadingIsGood.exception.NotFoundException;
import ilhan.ensar.ReadingIsGood.model.Customer;
import ilhan.ensar.ReadingIsGood.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Customer persist(CustomerPostRequest request) {
        // Check
        if (repository.existsByMail(request.getMail())) {
            throw new BusinessLogicException("An account already exist with this email address.");
        }

        // Prepare
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setMiddleName(request.getMiddleName() == null ? "" : request.getMiddleName());
        customer.setFamilyName(request.getFamilyName());
        customer.setMail(request.getMail());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save
        return repository.save(customer);
    }

    public Customer getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Customer(id=" + id + ") is not found."));
    }
}

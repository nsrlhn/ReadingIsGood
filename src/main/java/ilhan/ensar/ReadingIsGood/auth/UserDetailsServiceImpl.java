package ilhan.ensar.ReadingIsGood.auth;

import ilhan.ensar.ReadingIsGood.model.Customer;
import ilhan.ensar.ReadingIsGood.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Customer customer = repository.findByMail(mail).orElseThrow(() -> new UsernameNotFoundException(mail));
        return new CustomerPrincipal(customer);
    }
}

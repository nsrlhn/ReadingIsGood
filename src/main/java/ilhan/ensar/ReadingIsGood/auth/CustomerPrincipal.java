package ilhan.ensar.ReadingIsGood.auth;

import ilhan.ensar.ReadingIsGood.model.Customer;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomerPrincipal implements UserDetails {

    private final Customer customer;

    public CustomerPrincipal(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String getUsername() {
        return customer.getMail();
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(Role.CUSTOMER.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

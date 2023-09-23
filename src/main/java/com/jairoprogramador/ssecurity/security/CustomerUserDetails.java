package com.jairoprogramador.ssecurity.security;

import com.jairoprogramador.ssecurity.repositories.CustomersRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CustomerUserDetails implements UserDetailsService {

    @Autowired
    private final CustomersRepository customersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customersRepository.findByEmail(username)
                .map( customer -> {
                    var authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
                    return new User(customer.getEmail(), customer.getPassword(), authorities);

        }).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}

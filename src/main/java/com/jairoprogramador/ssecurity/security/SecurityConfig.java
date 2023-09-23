package com.jairoprogramador.ssecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests
                        ( auth ->
                                auth
                                        .requestMatchers("/loans", "/balance", "/accounts","/cards").authenticated()
                                        .anyRequest().permitAll()
                        )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        var admin = User.withUsername("admin")
                .password("to_be_encoded")
                .authorities("ADMIN")
                .build();

        var user = User.withUsername("user")
                .password("to_be_encoded")
                .authorities("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}

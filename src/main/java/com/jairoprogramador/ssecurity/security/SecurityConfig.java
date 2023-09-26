package com.jairoprogramador.ssecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JWTValidationFilter jwtValidationFilter) throws Exception {
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        var requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        httpSecurity.authorizeHttpRequests
                        ( auth ->
                                auth
                                        .requestMatchers("/loans").hasRole("LOANS")
                                        .requestMatchers("/balance").hasRole("BALANCE")
                                        .requestMatchers("/accounts").hasRole("ACCOUNT")
                                        .requestMatchers("/cards").hasRole("CARDS")
                                        .anyRequest().permitAll()
                        )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        httpSecurity.addFilterAfter(jwtValidationFilter, BasicAuthenticationFilter.class);
        httpSecurity.cors( cors -> corsConfigurationSource());
        httpSecurity.csrf( csrf -> csrf
                .csrfTokenRequestHandler(requestHandler)
                .ignoringRequestMatchers("/welcome", "/about_us", "/authenticate")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        ).addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        var config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}

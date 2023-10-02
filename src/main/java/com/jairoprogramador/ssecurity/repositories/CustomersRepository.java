package com.jairoprogramador.ssecurity.repositories;

import com.jairoprogramador.ssecurity.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface CustomersRepository extends CrudRepository<CustomerEntity, BigInteger> {
   Optional<CustomerEntity> findByEmail(String email);
}
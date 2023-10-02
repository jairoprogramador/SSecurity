package com.jairoprogramador.ssecurity.repositories;

import com.jairoprogramador.ssecurity.entities.PartnerEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface PartnerRepository extends CrudRepository<PartnerEntity, BigInteger> {
    Optional<PartnerEntity> findByClientId(String clientId);
}
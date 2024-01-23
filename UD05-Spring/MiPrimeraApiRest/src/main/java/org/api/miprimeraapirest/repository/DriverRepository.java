package org.api.miprimeraapirest.repository;

import org.api.miprimeraapirest.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    //Optional<Driver> findDriverByCodeIgnoreCase(String code);
    Optional<Driver> findByCodeIgnoreCase(String code);

    void deleteByCodeIgnoreCase(String code);
}


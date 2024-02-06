package org.api.miprimeraapirest.repository;

import org.api.miprimeraapirest.projection.DriverDetails;
import org.api.miprimeraapirest.projection.DriverDetailsDTO;
import org.api.miprimeraapirest.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query("SELECT d.id as id, d.code as code, d.forename as forename, d.surname as surname, d.constructor.name as constructor FROM Driver d")
    Optional<DriverDetailsDTO> findByCodeIgnoreCase(String code);

    void deleteByCodeIgnoreCase(String code);
    Page<DriverDetails> findAllProjectedBy(Pageable pageable);
}


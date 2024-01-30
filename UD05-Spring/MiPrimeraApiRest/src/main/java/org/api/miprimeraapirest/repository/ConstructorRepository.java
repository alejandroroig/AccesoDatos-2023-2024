package org.api.miprimeraapirest.repository;

import org.api.miprimeraapirest.entity.Constructor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ConstructorRepository extends JpaRepository<Constructor, Long> {
    Optional<Constructor> findByRefIgnoreCase(String ref);
    void deleteByRefIgnoreCase(String ref);
}


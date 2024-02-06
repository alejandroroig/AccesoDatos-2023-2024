package org.api.miprimeraapirest.repository;

import org.api.miprimeraapirest.entity.Constructor;
import org.api.miprimeraapirest.projection.ConstructorDetails;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConstructorRepository extends JpaRepository<Constructor, Long> {
    List<ConstructorDetails> findAllProjectedBy();
    Optional<ConstructorDetails> findByRefIgnoreCase(String ref);
    void deleteByRefIgnoreCase(String ref);
}


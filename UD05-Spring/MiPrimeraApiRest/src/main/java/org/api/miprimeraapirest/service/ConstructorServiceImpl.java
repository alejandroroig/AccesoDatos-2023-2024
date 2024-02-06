package org.api.miprimeraapirest.service;

import org.api.miprimeraapirest.entity.Constructor;
import org.api.miprimeraapirest.projection.ConstructorDetails;
import org.api.miprimeraapirest.repository.ConstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConstructorServiceImpl implements ConstructorService {

    private final ConstructorRepository repository;
    @Autowired
    public ConstructorServiceImpl(ConstructorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ConstructorDetails> getAllConstructors() {
        return repository.findAllProjectedBy();
    }

    @Override
    public Optional<ConstructorDetails> getConstructorByRef(String ref) {
        return repository.findByRefIgnoreCase(ref);
    }

    @Override
    public Constructor saveConstructor(Constructor constructor) {
        return this.repository.save(constructor);
    }

    @Override
    public void deleteConstructorByRef(String ref) {
        this.repository.deleteByRefIgnoreCase(ref);
    }
}

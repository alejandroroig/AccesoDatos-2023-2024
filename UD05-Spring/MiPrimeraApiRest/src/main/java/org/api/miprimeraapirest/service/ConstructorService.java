package org.api.miprimeraapirest.service;

import org.api.miprimeraapirest.entity.Constructor;
import org.api.miprimeraapirest.projection.ConstructorDetails;

import java.util.List;
import java.util.Optional;

public interface ConstructorService {
    List<ConstructorDetails> getAllConstructors();
    Optional<ConstructorDetails> getConstructorByRef(String ref);
    Constructor saveConstructor(Constructor constructor);
    void deleteConstructorByRef(String ref);
}


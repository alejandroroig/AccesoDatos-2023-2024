package org.api.miprimeraapirest.service;

import org.api.miprimeraapirest.model.Constructor;
import org.api.miprimeraapirest.model.Driver;

import java.util.List;
import java.util.Optional;

public interface ConstructorService {
    List<Constructor> getAllConstructors();
    Optional<Constructor> getConstructorByRef(String ref);
    Constructor saveConstructor(Constructor constructor);
    void deleteConstructorByRef(String ref);
}


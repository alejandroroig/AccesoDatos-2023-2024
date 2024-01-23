package org.api.miprimeraapirest.service;

import org.api.miprimeraapirest.model.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    List<Driver> getAllDrivers();
    Optional<Driver> getDriverByCode(String code);
    Driver saveDriver(Driver driver);
    void deleteDriverByCode(String code);
}


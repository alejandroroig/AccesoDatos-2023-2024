package org.api.miprimeraapirest.service;

import org.api.miprimeraapirest.dto.DriverDTO;
import org.api.miprimeraapirest.entity.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    List<Driver> getAllDrivers();
    Optional<DriverDTO> getDriverByCode(String code);
    Driver saveDriver(Driver driver);
    void deleteDriverByCode(String code);
}


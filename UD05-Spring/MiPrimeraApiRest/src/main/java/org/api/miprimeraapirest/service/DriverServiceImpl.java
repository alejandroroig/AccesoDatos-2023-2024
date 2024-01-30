package org.api.miprimeraapirest.service;

import org.api.miprimeraapirest.dto.DriverDTO;
import org.api.miprimeraapirest.entity.Driver;
import org.api.miprimeraapirest.mapper.DriverDTOMapper;
import org.api.miprimeraapirest.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DriverDTOMapper driverDTOMapper;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, DriverDTOMapper driverDTOMapper) {
        this.driverRepository = driverRepository;
        this.driverDTOMapper = driverDTOMapper;
    }

    @Override
    public List<Driver> getAllDrivers() {

        return driverRepository.findAll();
    }

    @Override
    public Optional<DriverDTO> getDriverByCode(String code) {
        return driverRepository.findByCodeIgnoreCase(code)
                .map(driverDTOMapper);
    }

    @Override
    public Driver saveDriver(Driver driver) {
        return this.driverRepository.save(driver);
    }

    @Override
    public void deleteDriverByCode(String code) {
        this.driverRepository.deleteByCodeIgnoreCase(code);
    }
}


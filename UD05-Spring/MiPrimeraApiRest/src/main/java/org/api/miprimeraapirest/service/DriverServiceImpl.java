package org.api.miprimeraapirest.service;

import org.api.miprimeraapirest.projection.DriverDetails;
import org.api.miprimeraapirest.projection.DriverDetailsDTO;
import org.api.miprimeraapirest.dto.DriverListDetailsDTO;
import org.api.miprimeraapirest.entity.Driver;
import org.api.miprimeraapirest.mapper.DriverDTOMapper;
import org.api.miprimeraapirest.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<DriverListDetailsDTO> getAllDrivers() {
        return driverRepository.findAll().stream().map(driverDTOMapper).toList();
    }

    @Override
    public Page<DriverDetails> getAllDriversPaged(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return driverRepository.findAllProjectedBy(pageable);
    }

    @Override
    public Optional<DriverDetailsDTO> getDriverByCode(String code) {
        return driverRepository.findByCodeIgnoreCase(code);
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


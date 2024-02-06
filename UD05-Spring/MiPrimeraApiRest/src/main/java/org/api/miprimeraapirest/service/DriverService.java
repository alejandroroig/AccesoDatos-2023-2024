package org.api.miprimeraapirest.service;

import org.api.miprimeraapirest.projection.DriverDetails;
import org.api.miprimeraapirest.projection.DriverDetailsDTO;
import org.api.miprimeraapirest.dto.DriverListDetailsDTO;
import org.api.miprimeraapirest.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    List<DriverListDetailsDTO> getAllDrivers();
    Page<DriverDetails> getAllDriversPaged(int pageNo, int pageSize, String sortBy, String sortDirection);
    Optional<DriverDetailsDTO> getDriverByCode(String code);
    Driver saveDriver(Driver driver);
    void deleteDriverByCode(String code);
}


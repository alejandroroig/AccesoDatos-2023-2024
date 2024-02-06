package org.api.miprimeraapirest.mapper;

import org.api.miprimeraapirest.dto.DriverListDetailsDTO;
import org.api.miprimeraapirest.entity.Driver;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DriverDTOMapper implements Function<Driver, DriverListDetailsDTO> {
    @Override
    public DriverListDetailsDTO apply(Driver driver) {
        return new DriverListDetailsDTO(
                driver.getId(),
                driver.getCode(),
                driver.getForename() + " " + driver.getSurname(),
                driver.getNationality(),
                driver.getConstructor() != null ? driver.getConstructor().getName() : null
        );
    }
}

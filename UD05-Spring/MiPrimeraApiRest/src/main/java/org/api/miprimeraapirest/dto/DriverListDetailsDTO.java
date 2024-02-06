package org.api.miprimeraapirest.dto;

public record DriverListDetailsDTO(
        Long id,
        String code,
        String fullname,
        String nationality,
        String constructor
) { }

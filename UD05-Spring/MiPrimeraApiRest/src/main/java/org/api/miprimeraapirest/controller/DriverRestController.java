package org.api.miprimeraapirest.controller;

import org.api.miprimeraapirest.projection.DriverDetails;
import org.api.miprimeraapirest.projection.DriverDetailsDTO;
import org.api.miprimeraapirest.dto.DriverListDetailsDTO;
import org.api.miprimeraapirest.entity.Driver;
import org.api.miprimeraapirest.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverRestController {
    private final DriverService driverService;

    @Autowired
    public DriverRestController(DriverService service) {
        this.driverService = service;
    }

    /*
    @GetMapping("")
    public ResponseEntity<List<DriverListDetailsDTO>> getAll() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }
    */
    @GetMapping("")
    public ResponseEntity<List<DriverDetails>> getAllPagedAndSorted(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size,
                                                                    @RequestParam(defaultValue = "code") String sortBy,
                                                                    @RequestParam(defaultValue = "ASC") String sortDirection) {
        Page<DriverDetails> driversPage = this.driverService.getAllDriversPaged(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(driversPage.getContent());
    }

    /*
    GET http://localhost:8080/api/drivers/alo
     */
    @GetMapping("/{code}")
    public ResponseEntity<DriverDetailsDTO> getByCode(@PathVariable String code) {
        return this.driverService.getDriverByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
    POST http://localhost:8080/api/drivers/
    */
    @PostMapping("")
    public ResponseEntity<Driver> create(@RequestBody Driver driver) {
        if (driver.getId() != null)
            return ResponseEntity.badRequest().build();

        this.driverService.saveDriver(driver);
        return ResponseEntity.ok(driver);
    }

    /*
    PUT http://localhost:8080/api/drivers/
    */
    @PutMapping("")
    public ResponseEntity<Driver> update(@RequestBody Driver driver) {
        this.driverService.saveDriver(driver);
        return ResponseEntity.ok(driver);
    }

    /*
    DELETE http://localhost:8080/api/drivers/alo
    */
    @DeleteMapping("/{code}")
    public ResponseEntity<Driver> deleteByCode(@PathVariable String code) {
        this.driverService.deleteDriverByCode(code);
        return ResponseEntity.noContent().build();
    }

}


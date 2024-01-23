package org.api.miprimeraapirest.controller;

import org.api.miprimeraapirest.model.Driver;
import org.api.miprimeraapirest.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("")
    public ResponseEntity<List<Driver>> getAll() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    /*
    GET http://localhost:8080/api/drivers/alo
     */
    @GetMapping("/{code}")
    public ResponseEntity<Driver> getByCode(@PathVariable String code) {
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


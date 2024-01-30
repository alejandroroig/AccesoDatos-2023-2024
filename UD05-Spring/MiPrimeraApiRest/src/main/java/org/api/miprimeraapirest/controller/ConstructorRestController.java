package org.api.miprimeraapirest.controller;

import org.api.miprimeraapirest.entity.Constructor;
import org.api.miprimeraapirest.service.ConstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/constructors")
public class ConstructorRestController {
    private final ConstructorService constructorService;

    @Autowired
    public ConstructorRestController(ConstructorService service) {
        this.constructorService = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Constructor>> getAll() {
        return ResponseEntity.ok(constructorService.getAllConstructors());
    }

    /*
    GET http://localhost:8080/api/constructors/mclaren
     */
    @GetMapping("/{ref}")
    public ResponseEntity<Constructor> getByRef(@PathVariable String ref) {
        return this.constructorService.getConstructorByRef(ref)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
    POST http://localhost:8080/api/constructors/
    */
    @PostMapping("")
    public ResponseEntity<Constructor> create(@RequestBody Constructor constructor) {
        if (constructor.getId() != null)
            return ResponseEntity.badRequest().build();

        this.constructorService.saveConstructor(constructor);
        return ResponseEntity.ok(constructor);
    }

    /*
    PUT http://localhost:8080/api/constructors/
    */
    @PutMapping("")
    public ResponseEntity<Constructor> update(@RequestBody Constructor constructor) {
        this.constructorService.saveConstructor(constructor);
        return ResponseEntity.ok(constructor);
    }

    /*
    DELETE http://localhost:8080/api/constructors/mclaren
    */
    @DeleteMapping("/{ref}")
    public ResponseEntity<Constructor> deleteByRef(@PathVariable String ref) {
        this.constructorService.deleteConstructorByRef(ref);
        return ResponseEntity.noContent().build();
    }

}


package org.api.miprimeraapirest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "constructors")
public class Constructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "constructorid")
    private Long id;
    @Column(name = "constructorref", unique = true, nullable = false)
    private String ref;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String nationality;
    private String url;

    @OneToMany(mappedBy = "constructor")
    private List<Driver> drivers;
}

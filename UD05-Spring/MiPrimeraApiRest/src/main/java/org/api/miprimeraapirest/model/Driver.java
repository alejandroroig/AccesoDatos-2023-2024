package org.api.miprimeraapirest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driverid")
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;
    @Column(nullable = false)
    private String forename;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    @JsonProperty("dateOfBirth")
    private LocalDate dob;
    @Column(nullable = false)
    private String nationality;
    private String url;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "constructorid")
    private Constructor constructor;
}



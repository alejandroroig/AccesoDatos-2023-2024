package org.funkos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Funko implements Serializable {
    private String id;
    private String nombre;
    private String modelo;
    private double precio;
    private LocalDate fechaLanzamiento;

    public Funko(String id, String nombre, String modelo, String precioString, String fechaLanzamiento) {
        this.id = id;
        this.nombre = nombre;
        this.modelo = modelo;
        this.precio = Double.parseDouble(precioString);
        this.fechaLanzamiento = LocalDate.parse(fechaLanzamiento);
        // this.fechaLanzamiento = LocalDate.parse(fechaLanzamiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Funko(List<String> lista) {
        this.id = lista.get(0);
        this.nombre = lista.get(1);
        this.modelo = lista.get(2);
        this.precio = Double.parseDouble(lista.get(3));
        this.fechaLanzamiento = LocalDate.parse(lista.get(4));
        // this.fechaLanzamiento = LocalDate.parse(lista.get(4), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    @Override
    public String toString() {
        return "Funko{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precio=" + precio +
                ", fechaLanzamiento=" + fechaLanzamiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                '}';
    }
}
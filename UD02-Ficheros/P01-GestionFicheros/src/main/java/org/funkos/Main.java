package org.funkos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {

        Path ficheroFunkos = Path.of("src", "main", "resources", "funkos.csv");
        ColeccionFunkos funkos = new ColeccionFunkos(ficheroFunkos);

        // funkos.listaFunkos.forEach(System.out::println);

        // Consultas

        System.out.println("El funko más caro es: " + funkos.funkoMasCaro().isPresent());

        System.out.println("Los funkos por modelo son:");
        funkos.funkosPorModelo().forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println("El precio medio de los funkos es: " + funkos.precioMedioDeFunkos());

        System.out.println("Los funkos del 2023 son: " + funkos.funkosPorAnyo(2023));

        // Serialización
        funkos.serializar();

        ColeccionFunkos funkosDeserializados = new ColeccionFunkos();
        funkosDeserializados.deserializar();

        funkosDeserializados.listaFunkos.forEach(System.out::println);


    }
}

package org.ficheros;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class OrientadoCaracteres {
    public static void main(String[] args) {
        Path ficheroIn = Path.of(".", "src", "main", "resources", "ficheroCaracteres.txt");

        // LECTURA DE FICHEROS

        // Opción 1: Files.lines
        System.out.println("\nLectura del fichero con Files.lines");
        try (Stream<String> lineas = Files.lines(ficheroIn)) {
            lineas.forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        // Opción 2: Files.readString
        System.out.println("\nLectura del fichero con Files.readString");
        try {
            String fichero = Files.readString(ficheroIn);
            System.out.println(fichero);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        // Opción 3: Files.readAllLines
        System.out.println("\nLectura del fichero con Files.readAllLines");
        try {
            List<String> lines = Files.readAllLines(ficheroIn);
            lines.forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        // Opción 4: BufferedReader con Files.newBufferedReader
        System.out.println("\nLectura del fichero con BufferedReader (Java 8+)");
        try (BufferedReader br = Files.newBufferedReader(ficheroIn)) {
            br.lines().forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        // ESCRITURA DE FICHEROS
        Path ficheroOut = Path.of(".", "src", "main", "resources", "ficherosalida.txt");
        ArrayList<String> listaCadenas = new ArrayList<>();
        listaCadenas.add("En este fichero de ejemplo tenemos 6 líneas.");
        listaCadenas.add("Esta será la segunda,");
        listaCadenas.add("esta la tercera");
        listaCadenas.add("esta la cuarta, ya solo quedan 2");
        listaCadenas.add("esta es la quinta y penúltima...");
        listaCadenas.add("y con esta última se acaba el fichero.");

        // Opción 1: Files.writeString
        System.out.println("\nEscritura del fichero con Files.writeString");
        try {
            for (String cadena : listaCadenas)
                Files.writeString(ficheroOut, cadena);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        imprimirFichero(ficheroOut);

        // Opción 2: Files.write
        // En vez del string propiamente dicho, el método getBytes() lo convierte en un
        // array de bytes, que es lo que se escribe.
        // También sobreescribe lo escrito anteriormente
        System.out.println("\nEscritura del fichero con Files.write");
        try {
            for (String cadena : listaCadenas)
                Files.write(ficheroOut, cadena.getBytes());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        imprimirFichero(ficheroOut);

        // Opción 3: BufferedWriter con Files.newBufferedWriter
        System.out.println("\nEscritura del fichero con BufferedWriter (java.nio)");
        try (BufferedWriter bw = Files.newBufferedWriter(ficheroOut)){
            for (String cadena : listaCadenas) {
                bw.write(cadena);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        imprimirFichero(ficheroOut);

        // Añadir líneas a final del fichero (append)

        // Opción 1: Files.writeString
        // Importante la opción APPEND, aunque si el fichero no existe se lanzará una excepción
        // por lo que también añadimos la opción CREATE
        // Además, no hace un salto de línea automáticamente antes de escribir como quizás podría interesarnos
        System.out.println("\nEscritura al final del fichero con Files.writeString");
        try {
            Files.writeString(ficheroOut, listaCadenas.get(0), CREATE, APPEND);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        imprimirFichero(ficheroOut);

        // Opción 2: Files.write
        System.out.println("\nEscritura al final del fichero con Files.write");
        try {
            Files.write(ficheroOut, ("\n" + listaCadenas.get(0)).getBytes(), CREATE, APPEND);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        imprimirFichero(ficheroOut);
    }
    public static void imprimirFichero(Path rutaFichero) {
        try (Stream<String> lineas = Files.lines(rutaFichero)) {
            lineas.forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}

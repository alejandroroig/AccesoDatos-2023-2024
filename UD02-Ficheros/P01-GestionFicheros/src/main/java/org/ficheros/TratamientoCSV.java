package org.ficheros;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TratamientoCSV {
    private final static String COMMA_DELIMITER = ",";

    public static void main(String[] args) throws IOException {

        // Lectura de ficheros CSV con Files.lines en java.nio
        try (Stream<String> contenidoFichero = Files.lines(Path.of(".", "src", "main", "resources", "libros.csv"))) {
            List<List<String>> libros = contenidoFichero.map(l -> Arrays.asList(l.split(COMMA_DELIMITER))).toList();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}

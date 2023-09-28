package org.ficheros;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class OrientadoBytes {
    public static void main(String[] args) {
        Path unFichero = Path.of(".", "src", "main", "resources", "ficheroBytes");
        byte[] bytes = "Ejemplo de texto para fichero binario".getBytes();

        // Opción 1: Escritura con Files.write() de java.nio y lectura con Files.readAllBytes
        System.out.println("\nLectura y escritura binaria con métodos de Files");
        try {
            // Escritura
            Files.write(unFichero, bytes, CREATE);
            // Lectura
            byte[] leidos = Files.readAllBytes(unFichero);
            System.out.println(new String(leidos));
        }
        catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        // Opción 2: OutputStream e InputStream
        System.out.println("\nLectura y escritura binaria con FileInputStream y FileOutputStream");
        try (OutputStream os = Files.newOutputStream(unFichero);
             InputStream is = Files.newInputStream(unFichero)) {
            // Escritura
            os.write(bytes);

            // Lectura
            int c;
            while((c = is.read()) != -1){
                System.out.print((char) c);
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}

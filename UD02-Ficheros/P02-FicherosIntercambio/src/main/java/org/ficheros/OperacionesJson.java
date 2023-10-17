package org.ficheros;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.ficheros.entities.Lenguaje;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

// https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.json
// Spring Boot proporciona integración con tres bibliotecas de mapeo JSON:
//  * Gson
//  * Jackson
//  * JSON-B
// Jackson es la biblioteca preferida y predeterminada
public class OperacionesJson {

   public static Lenguaje leerObjetoJson(Path ruta) {
       try {
           ObjectMapper objectMapper = new ObjectMapper();
           return objectMapper.readValue(ruta.toFile(), Lenguaje.class);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }

    public static List<Lenguaje> leerArrayObjetosJson(Path ruta) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // return objectMapper.readValue(ruta.toFile(), new TypeReference<List<Lenguaje>>() { });
            return objectMapper.readValue(ruta.toFile(), new TypeReference<>() { });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String escribirObjetoJson(Lenguaje lenguaje) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // La siguiente línea es opcional, pero hace que el JSON se muestre con formato
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            return objectMapper.writeValueAsString(lenguaje);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String escribirListaObjetosJson(List<Lenguaje> lenguajes) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // La siguiente línea es opcional, pero hace que el JSON se muestre con formato
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            return objectMapper.writeValueAsString(lenguajes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirListaObjetosJson(List<Lenguaje> lenguajes, Path ruta) {

        try {
            Files.deleteIfExists(ruta);
            ObjectMapper objectMapper = new ObjectMapper();
            // La siguiente línea es opcional, pero hace que el JSON se muestre con formato
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(ruta.toFile(), lenguajes);
        } catch (IOException e) {
            System.out.println("El fichero no existe");
        }
    }

    public static void main(String[] args) throws IOException {
        Path ficheroObjetoJson = Path.of(".", "src", "main", "resources", "lenguaje.json");
        System.out.println("\n**** Lectura de objeto JSON desde fichero ****");
        System.out.println(leerObjetoJson(ficheroObjetoJson));

        Path ficheroArrayObjetosJson = Path.of(".", "src", "main", "resources", "lenguajes.json");
        System.out.println("\n**** Lectura de array de objetos JSON desde fichero ****");
        leerArrayObjetosJson(ficheroArrayObjetosJson).forEach(System.out::println);

        Lenguaje lenguaje1 = new Lenguaje("Solidity", 180, false, List.of("María", "Sonia", "José Miguel"));
        Lenguaje lenguaje2 = new Lenguaje("Vyper", 160, false, List.of("Pepe", "Julián"));

        List<Lenguaje> lenguajes = List.of(lenguaje1, lenguaje2);

        System.out.println("\n**** Escritura de objeto JSON por consola ****");
        System.out.println(escribirObjetoJson(lenguaje1));

        System.out.println("\n**** Escritura de lista de objetos JSON por consola ****");
        System.out.println(escribirListaObjetosJson(lenguajes));

        Path ejemploEscrituraJson = Path.of(".", "src", "main", "resources", "escritura.json");

        System.out.println("\n**** Escritura de lista de objetos JSON a fichero ****");
        escribirListaObjetosJson(lenguajes, ejemploEscrituraJson);


    }
}
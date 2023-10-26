package org.ficheros;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.ficheros.entities.Location;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class PruebaAPI {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            JsonNode rootNode = objectMapper.readTree(new URL("https://rickandmortyapi.com/api/location"));
            List<Location> locations = objectMapper.readValue(rootNode.get("results").traverse(), new TypeReference<>(){});
            locations.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

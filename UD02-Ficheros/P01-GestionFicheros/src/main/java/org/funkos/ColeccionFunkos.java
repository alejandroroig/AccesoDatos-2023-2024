package org.funkos;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ColeccionFunkos implements Serializable {
    final String COMMA_DELIMITER = ",";
    final Path FICHERO_SERIALIZACION = Path.of("src", "main", "resources", "funkos.dat");
    List<Funko> listaFunkos = new ArrayList<>();

    public ColeccionFunkos() { }

    public ColeccionFunkos(Path ficheroFunkos) {
        try (Stream<String> contenidoFichero = Files.lines(ficheroFunkos)) {
            listaFunkos = contenidoFichero.skip(1)
                    .map(l -> Arrays.asList(l.split(COMMA_DELIMITER)))
                    .map(Funko::new)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public Optional<Funko> funkoMasCaro() {
        return listaFunkos.stream().min(Comparator.comparing(Funko::getPrecio));
    }

    public Map<String, List<Funko>> funkosPorModelo() {
        return listaFunkos.stream().collect(Collectors.groupingBy(Funko::getModelo));
    }

    public double precioMedioDeFunkos() {
        return listaFunkos.stream().mapToDouble(Funko::getPrecio).average().orElse(0.0);
    }

    public List<Funko> funkosPorAnyo(int anyo) {
        return listaFunkos.stream().filter(f -> f.getFechaLanzamiento().getYear() == anyo).toList();
    };

    public void serializar() {
        try(FileOutputStream fos = new FileOutputStream(FICHERO_SERIALIZACION.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(listaFunkos);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public void deserializar(){
        try(FileInputStream fis = new FileInputStream(FICHERO_SERIALIZACION.toFile());
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            listaFunkos = (List<Funko>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }
}

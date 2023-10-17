package org.ficheros;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class OperacionesPath {
    public static void main(String[] args) throws IOException {

        // Para obtener un objeto Path utilizamos el método Path.of() o Paths.get()
        Path unPath = Path.of("/ruta/al/fichero/o/directorio");
        Path otroPath = Paths.get("/ruta/al/fichero/o/directorio2");

        // Obtener el nombre de un fichero y unirlo a la ruta de otro directorio
        Path unFile = Path.of("/usr/local/file.txt");
        Path unDir = Path.of("/home/user");
        // /home/user/file.txt
        System.out.println(unDir.resolve(unFile.getFileName()));

        // Relativizar una ruta respecto a otra
        Path otroFile = Path.of("/a/b/c/d.txt");
        Path otroDir = Path.of("/a/b/");
        // c/d.txt
        System.out.println(otroDir.relativize(otroFile));

        // La clase Files contiene métodos estáticos para realizar operaciones
        // sobre ficheros y directorios

        // Crear un directorio y sus ascendentes
        Files.createDirectories(Path.of("/some/non/existing/directories"));

        // Imprimir el contenido de un directorio
        Files.list(Path.of("/some/directory")).forEach(System.out::println);

        // Obtener la lista de los contenidos anidados de un directorio
        List<Path> list = Files.walk(Paths.get("/this/directory")).toList();

        // Imprimir los ficheros regulares de un directorio ordenados por tamaño
        Files.list(Path.of("/another/directory"))
                .filter(Files::isRegularFile)
                .sorted(Comparator.comparingLong(path -> {
                    try {
                        return Files.size(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .forEach(System.out::println);

        // Obtener el año, mes y día de la fecha de la última modificación
        Path file = Path.of("/one/more/file");
        LocalDate time = LocalDate.parse(Files.getLastModifiedTime(file).toString(), DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(time.getYear());
        System.out.println(time.getMonthValue());
        System.out.println(time.getDayOfMonth());

        // Navegación entre rutas
        Path relative = Path.of(".");
        Path absolute = relative.toAbsolutePath().normalize();
        System.out.printf("Relative: %s%n", relative);
        System.out.printf("Absolute: %s%n", absolute);
        System.out.printf("Name count: %d%n", absolute.getNameCount());
        System.out.printf("Parent: %s%n", absolute.getParent());
        System.out.printf("Subpath(0, 2): %s%n", absolute.subpath(0, 2));

        // Operaciones con ficheros
        Path original = Path.of("prueba.iml");
        Path backup = Path.of("prueba.iml.backup");
        Path rename = Path.of("prueba.iml.backup.1");
        Files.copy(original, backup, StandardCopyOption.REPLACE_EXISTING);
        Files.move(backup, rename, StandardCopyOption.REPLACE_EXISTING);
        Files.delete(rename);
    }
}

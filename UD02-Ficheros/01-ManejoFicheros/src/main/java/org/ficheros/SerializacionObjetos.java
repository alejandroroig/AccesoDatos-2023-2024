package org.ficheros;

import lombok.Data;
import java.io.*;
import java.nio.file.Path;

@Data
class Empleado implements Serializable
{
    @Serial
    private static final long serialVersionUID = 100L;
    private String nombre;
    private int edad;
    private double sueldo;

    Empleado(String empNombre, int empEdad, double empSueldo)
    {
        this.nombre = empNombre;
        this.edad = empEdad;
        this.sueldo = empSueldo;
    }

    @Override
    public String toString() {
        return "Empleado " + serialVersionUID
                + " -> nombre: " + nombre
                + ", edad: " + edad
                + ", sueldo: " +  sueldo;
    }
}

public class SerializacionObjetos {
    public static void main(String[] args) {
        Path fichero = Path.of(".", "src", "main", "resources", "empleados.dat");

        // En el caso de querer serializar una lista de objetos, lo más intuitivo
        // es recorrer la colección y serializar uno a uno los objetos que la contienen
        // Sin embargo, tenemos la opción de serializar colecciones enteras siempre
        // que los elementos contenidos sean serializables.
        //
        // Por ejemplo:
        // List<Empleado> empleadosToWrite = new ArrayList<>();
        // empleadosToWrite.add(new Empleado(...));
        // empleadosToWrite.add(new Empleado(...));
        // oos.writeObject(empleadosToWrite);
        // List<Empleado> empleadosToRead = (List<Empleado>)(ois.readObject());
        try (FileOutputStream fos = new FileOutputStream(fichero.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            Empleado emp = new Empleado("Juan Palomo", 25, 1400);
            oos.writeObject(emp);
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }

        try (FileInputStream fis = new FileInputStream(fichero.toFile());
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Empleado emp = (Empleado) ois.readObject();
            System.out.println(emp.toString());
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }
    }
}

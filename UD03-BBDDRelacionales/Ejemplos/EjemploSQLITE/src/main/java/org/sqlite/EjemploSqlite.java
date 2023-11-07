package org.sqlite;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EjemploSqlite {
    public static void main(String[] args) {

        // URL de la BBDD
        Path rutaBaseDatos = Path.of("src", "main", "resources", "db", "f12006sqlite.db");

        // Conexión a la base indicando la URL de la BBDD (en otros casos, URL del servidor, usuario y contraseña)
        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:" + rutaBaseDatos.toString())) {

            // Preparamos una sentencia que lanzaremos a través del PreparedStatement
            String consultaSQL = "SELECT driverid, code, forename, surname, strftime('%d/%m/%Y', dob) AS formatted_dob, nationality " +
                                    "FROM drivers " +
                                    "WHERE nationality = ? " +
                                    "ORDER BY driverid";
            PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
            // Metemos como primer parámetro que la nacionalidad sea Española
            consulta.setString(1, "Spanish");

            // La consulta SELECT se ejecuta pasándola por el método executeQuery. Si la consulta devuelve datos,
            // estos estarán accesibles a través de un "conjunto de resultados" (ResultSet)
            ResultSet resultados = consulta.executeQuery();

            // Consumimos los resultados de la consulta
            System.out.format("\n%3s%5s%25s%16s%20s\n", "Id", "Cod", "Nombre", "Fecha Nac", "Nacionalidad");
            System.out.println("--------------------------------------------------------------------");
            // El conjunto de resultados se recorre de forma secuencial: rs.next() será verdadero si hay más datos en el set.
            while (resultados.next()) {
                // Si cada fila del resultado está formada por varios campos, podemos obtener el valor de cada uno de ellos con rs.getString(x) o rs.getInt(x),
                // donde x puede ser la posición de la columna (empezando con 1) o el nombre del campo (indiferente de mayúsculas o minúsculas
                System.out.format("%3d%5s%25s%16s%20s\n", resultados.getInt("driverid"),
                        resultados.getString("code"),
                        resultados.getString("forename") + " " + resultados.getString("surname"),
                        resultados.getString("formatted_dob"),
                        resultados.getString("nationality"));
            }

            // Creamos ahora una sentencia de modificación, en este caso un INSERT
            // String insercionSQL = "INSERT or REPLACE INTO drivers (driverid, code, forename, surname, dob, nationality, url) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String insercionSQL = "INSERT INTO drivers (code, forename, surname, dob, nationality, url) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insercion = conexion.prepareStatement(insercionSQL);
            insercion.setString(1, "SAI");
            insercion.setString(2, "Carlos");
            insercion.setString(3, "Sainz");
            insercion.setString(4, "1994-09-01");
            insercion.setString(5, "Spanish");
            insercion.setString(6, "https://en.wikipedia.org/wiki/Carlos_Sainz_Jr.");

            //Ejecutamos la sentencia DML y recogemos el número de filas afectadas, si quisiéramos utilizarlo a posteriori
            int filasAfectadas = insercion.executeUpdate();

            // Comprobamos los cambios realizados en la tabla drivers
            resultados = consulta.executeQuery();
            System.out.format("\n%3s%5s%25s%16s%20s\n", "Id", "Cod", "Nombre", "Fecha Nac", "Nacionalidad");
            System.out.println("--------------------------------------------------------------------");
            while (resultados.next()) {
                System.out.format("%3d%5s%25s%16s%20s\n", resultados.getInt("driverid"),
                        resultados.getString("code"),
                        resultados.getString("forename") + " " + resultados.getString("surname"),
                        resultados.getString("formatted_dob"),
                        resultados.getString("nationality"));
            }

            // Creamos una nueva sentencia de modificación, en este caso un DELETE para borrar el piloto insertado
            String borradoSQL = "DELETE FROM drivers WHERE code = ?";
            PreparedStatement borrado = conexion.prepareStatement(borradoSQL);
            borrado.setString(1, "SAI");
            borrado.executeUpdate();

            resultados = consulta.executeQuery();
            System.out.format("\n%3s%5s%25s%16s%20s\n", "Id", "Cod", "Nombre", "Fecha Nac", "Nacionalidad");
            System.out.println("--------------------------------------------------------------------");
            while (resultados.next()) {
                System.out.format("%3d%5s%25s%16s%20s\n", resultados.getInt("driverid"),
                        resultados.getString("code"),
                        resultados.getString("forename") + " " + resultados.getString("surname"),
                        resultados.getString("formatted_dob"),
                        resultados.getString("nationality"));
            }

            // Finalmente, se deben cerrar las sentencias (cuando una sentencia se cierra, su objeto ResultSet también se cierra)
            // Otra alternativa es añadir los PreparedStatement en el try-with-resources
            consulta.close();
            insercion.close();
            borrado.close();

        } catch ( Exception e ) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
package org.postgresql;

import java.sql.*;
import java.text.SimpleDateFormat;

public class EjemploReal {
    public static void main(String[] args) {
        String urlConexion = "jdbc:postgresql://pruebaf1.ci66saah1axn.us-east-1.rds.amazonaws.com:5432/f12006";
        String usuario = "postgres";
        String password = "qwerty1234";

        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            try {
                // Por defecto, el gestor de base de datos ejecuta una operación de confirmación después de la ejecución de cada sentencia de SQL.
                // Para desactivar la confirmación automática e iniciar así una transacción, invocamos el método Connection.setAutoCommit(false).
                conexion.setAutoCommit(false);

                String consultaSQL = "SELECT driverid, code, forename, surname, dob AS formatted_dob, nationality " +
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

                // Probamos también a ejecutar una sentencia a un procedimiento almacenado
                CallableStatement resultadosPiloto = conexion.prepareCall("{call get_results_by_driver(?)}");
                resultadosPiloto.setString(1, "ALO");
                resultadosPiloto.execute();
                resultados = resultadosPiloto.getResultSet();
                System.out.format("\n%2s%28s%6s%8s%12s\n", "Rd", "Circuito", "Pos", "Puntos", "Fecha");

                while (resultados.next()) {
                    System.out.format("%2d%28s%6d%8d%12s\n", resultados.getInt("round"),
                            resultados.getString("circuit"),
                            resultados.getInt("result"),
                            resultados.getInt("points"),
                            new SimpleDateFormat("dd/MM/yyyy").format(resultados.getDate("date")));
                }

                // Provocamos un error
                String insercionSQL = "INSERT INTO drivers (code, forename, surname, dob, nationality) VALUES (?, ?, ?, ?, ?)";
                consulta = conexion.prepareStatement(insercionSQL);
                consulta.setString(1, "ALO");
                consulta.setString(2, "Miguel");
                consulta.setString(3, "Alomá");
                consulta.setDate(4, Date.valueOf("1994-09-01"));
                consulta.setString(5, "Spanish");
                consulta.executeUpdate();

                // En las transacciones, hay dos formas de confirmar los cambios hechos en una base de datos e indica el final de una transacción
                //  - Invocando los métodos Connection.commit o Connection.rollback después de ejecutar una o más sentencias de SQL
                //  - Invocando el método Connection.setAutoCommit(true), para ejecutar más sentencias SQL pero no en una transacción

                // Finaliza la transacción, confirmando los cambios en la base de datos
                conexion.commit();
                // Es buena práctica volver a activar la confirmación automática
                conexion.setAutoCommit(true);

            } catch (SQLException ex1) {
                System.err.println(ex1.getClass().getName() + ": " + ex1.getMessage());
                try {
                    // Deshacemos las operaciones realizadas en la base de datos
                    conexion.rollback();
                    System.err.println("ROLLBACK ejecutado");
                } catch (SQLException ex2) {
                    System.err.println("Error haciendo ROLLBACK");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }
}

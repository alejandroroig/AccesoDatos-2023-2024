package org.example;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String urlConexion = "jdbc:postgresql://examenad.ci66saah1axn.us-east-1.rds.amazonaws.com:5432/aleroig";
        String usuario = "postgres";
        String password = "qwerty1234";

        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            try {
                System.out.println("Conectado!!!");
                List<String> nombres = List.of("aleroig", "alvrub", "arrsan", "cricoz", "fulest", "garbel", "herill", "maltor", "morseg", "munsot", "nebcle", "nebflo", "redper", "sanpin", "tenmar", "urefor");
                for (String nombre : nombres) {
                    // System.out.println("db.createUser( {user: \"" + nombre + "\", pwd: \"qwerty1234\", roles: [ { role: \"userAdminAnyDatabase\", db: \"admin\" }, \"readWriteAnyDatabase\" ] } )");
                    System.out.println("mongoimport --username=adminAlejandro --authenticationDatabase admin --password=1234 --db=" + nombre + " --collection=clients --file=clients.json");
                    //System.out.println("psql -h examenad.ci66saah1axn.us-east-1.rds.amazonaws.com -U " + nombre + " -d " + nombre + " -f accounts.sql;");
                }
//                String consultaSQL = "SELECT driverid, code, forename, surname, dob AS formatted_dob, nationality " +
//                        "FROM drivers " +
//                        "WHERE nationality = ? " +
//                        "ORDER BY driverid";
//                PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
//                // Metemos como primer parámetro que la nacionalidad sea Española
//                consulta.setString(1, "Spanish");
//
//                // La consulta SELECT se ejecuta pasándola por el método executeQuery. Si la consulta devuelve datos,
//                // estos estarán accesibles a través de un "conjunto de resultados" (ResultSet)
//                ResultSet resultados = consulta.executeQuery();
//
//                // Consumimos los resultados de la consulta
//                System.out.format("\n%3s%5s%25s%16s%20s\n", "Id", "Cod", "Nombre", "Fecha Nac", "Nacionalidad");
//                System.out.println("--------------------------------------------------------------------");
//                // El conjunto de resultados se recorre de forma secuencial: rs.next() será verdadero si hay más datos en el set.
//                while (resultados.next()) {
//                    // Si cada fila del resultado está formada por varios campos, podemos obtener el valor de cada uno de ellos con rs.getString(x) o rs.getInt(x),
//                    // donde x puede ser la posición de la columna (empezando con 1) o el nombre del campo (indiferente de mayúsculas o minúsculas
//                    System.out.format("%3d%5s%25s%16s%20s\n", resultados.getInt("driverid"),
//                            resultados.getString("code"),
//                            resultados.getString("forename") + " " + resultados.getString("surname"),
//                            resultados.getString("formatted_dob"),
//                            resultados.getString("nationality"));
//                }
//            } catch (SQLException ex) {
//                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }
}
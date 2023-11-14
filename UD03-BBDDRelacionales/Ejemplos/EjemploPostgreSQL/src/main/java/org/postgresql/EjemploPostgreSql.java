package org.postgresql;

import java.sql.*;

public class EjemploPostgreSql {
    public static void main(String[] args) {
        String urlConexion = "jdbc:postgresql://localhost:5432/mi-base-de-datos";
        String usuario = "usuario";
        String password = "password";

        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            try {
                // Por defecto, el gestor de base de datos ejecuta una operación de confirmación después de la ejecución de cada sentencia de SQL.
                // Para desactivar la confirmación automática e iniciar así una transacción, invocamos el método Connection.setAutoCommit(false).
                conexion.setAutoCommit(false);

                // Preparamos una sentencia para crear un pedido
                String insertarPedido = "INSERT INTO pedidos (producto, precio, fechaPedido, numeroProductos, idClientes) " +
                        "VALUES (?, ?, ?, ?, ?)";

                // Añadir el parámetro PreparedStatement.RETURN_GENERATED_KEYS nos permite recuperar las claves generadas
                PreparedStatement sentencia = conexion.prepareStatement(insertarPedido, PreparedStatement.RETURN_GENERATED_KEYS);
                sentencia.setString(1, "Xiaomi Mi 11");
                sentencia.setDouble(2, 286.95);
                sentencia.setDate(3, Date.valueOf("2022-07-09"));
                sentencia.setInt(4, 15);
                sentencia.setInt(5, 3);

                sentencia.executeUpdate();
                ResultSet rs = sentencia.getGeneratedKeys();
                rs.next();
                int idPedido = rs.getInt(1);

                // Preparamos una sentencia para actualizar el inventario
                String actualizarInventario = "UPDATE inventario SET stock = stock - ? WHERE producto = ?";

                sentencia = conexion.prepareStatement(actualizarInventario);
                sentencia.setInt(1, 15);
                sentencia.setString(2, "Xiaomi Mi 11");

                sentencia.executeUpdate();
                sentencia.close();

                // Probamos también a ejecutar una sentencia a un procedimiento almacenado
                CallableStatement listadoClientes = conexion.prepareCall("{call listado_clientes_cp(?, ?)}");
                listadoClientes.setInt(1, 12000);
                listadoClientes.setInt(2, 12609);
                listadoClientes.execute();
                rs = listadoClientes.getResultSet();
                System.out.format("\n%4s%30s%20s%10s\n", "Id", "Nombre", "Población", "Facturación");
                while (rs.next()) {
                    System.out.format("%4d%30s%20s%10f\n",
                            rs.getInt("idCliente"),
                            rs.getString("nombre"),
                            rs.getString("población"),
                            rs.getDouble("facturacion"));
                }

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
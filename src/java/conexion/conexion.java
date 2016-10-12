/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author leo
 */
public class conexion {

    private final String db = "XE";
    private final String url = "jdbc:oracle:thin:@10.100.97.13:1521:" + db;
    private final String user = "lromero";
    private final String pass = "System";
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void Conectar() throws ClassNotFoundException {

        try {
            //cargamos el driver postgresql
            //Class.forName("oracle.jdbc.OracleDriver");
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //creamos un enlace hacia la base de datos
            connection = DriverManager.getConnection(this.url, this.user, this.pass);

        } catch (SQLException e) {

        }

    }

    public void Cerrar() {
        try {
            if (connection != null) {
                if (connection.isClosed() == false) {
                    connection.close();
                }
            }
        } catch (SQLException e) {

        }
    }

}

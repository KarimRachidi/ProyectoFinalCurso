package src.BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/*
Creamos una nueva clase la cual conectará nuestro código con la base de datos
que le indiquemos pudiendo hacer así CRUD (Create Read Update Delete).
 */

public class ConexionBBDD {
    private Connection con;
    private Statement sentencia;
    private String sql;
    private String url = "jdbc:mysql://localhost/MUSEO?serverTimeZone=UTC";
    private String usuario = "root";
    private String password = "root_123";

    public void conectar() {
        try {
            //Aqui se establece la conexión
            con = DriverManager.getConnection(url, usuario, password);

            //Creamos un statement para ejecutar consultas
            sentencia = con.createStatement();
            System.out.println("Conexión establecida con la base de datos");
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos");
            e.printStackTrace();
        }
    }

    public Statement getSentencia() {
        return sentencia;
    }

    public void cerrarConexion() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar la conexión");
        }
    }
}

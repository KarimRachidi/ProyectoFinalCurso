package src;

import src.BBDD.ConexionBBDD;

import java.sql.ResultSet;
import java.sql.Statement;

/*
Clase main
 */
public class Main {
    public static void main(String[] args) {
        //Creamos la conexión a la base de datos
        ConexionBBDD conexion = new ConexionBBDD();
        conexion.conectar();

        try {
            Statement stmt = conexion.getSentencia();
            if (stmt != null) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM VISITANTE");

                while(rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int edad = rs.getInt("edad");
                    String email = rs.getString("email");
                    System.out.println("ID: " + id + ", Nombre: " + nombre
                            + ",Edad: " + edad + ", Email: " + email);

                }
            } else {
                System.out.println("No se pudo crear la sentencia SQL");
            }
        } catch (Exception e) {
            System.err.println("Error al leer los datos");
            e.printStackTrace();
        }
        conexion.cerrarConexion();
    }
}

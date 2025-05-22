package src.ClasesDAO;

import src.BBDD.ConexionBBDD;
import src.Clases.Visitante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
Creamos la clase VisitanteDAO donde se le aplica CRUD al Visitante
 */
public class VisitanteDAO implements DAO<Visitante> {
    private final ConexionBBDD conexion;

    public VisitanteDAO(ConexionBBDD conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(Visitante visitante) {
        String sql = "INSERT INTO visitante(nombre, edad, email) VALUES (?,?,?)";
        try (PreparedStatement stmt = conexion.getSentencia().getConnection().prepareStatement(sql)) {
            stmt.setString(1, visitante.getNombre());
            stmt.setInt(2, visitante.getEdad());
            stmt.setString(3, visitante.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar visitante");
        }
    }

    @Override
    public List<Visitante> readAll() {
        List<Visitante> lista = new ArrayList<>();
        String sql = "SELECT * FROM visitante";
        try (ResultSet rs = conexion.getSentencia().executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Visitante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("email")
                ));

            }
        } catch (SQLException e) {
            System.err.println("Error al leer los visitantes");
        }
        return lista;
    }

    @Override
    public Visitante readById(int id) {
        String sql = "SELECT * FROM visitante WHERE id=?";
        try (PreparedStatement stmt = conexion.getSentencia().getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id); // Reemplaza el primer '?' con el valor de id
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Visitante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el visitante por ID");
        }
        return null; //Devuelve null si no se encuentra
    }

    @Override
    public void update(Visitante visitante){
        String sql = "UPDATE visitante SET nombre=?, edad=?, email=? WHERE id=?";
        try(PreparedStatement stmt = conexion.getSentencia().getConnection().prepareStatement(sql)){
            stmt.setString(1, visitante.getNombre());
            stmt.setInt(2, visitante.getEdad());
            stmt.setString(3, visitante.getEmail());
            stmt.setInt(4, visitante.getId());
            stmt.executeUpdate();
        } catch (SQLException e){
            System.err.println("Error al actualizar visitante.");
        }
    }

    @Override
    public void delete(int id){
        String sql = "DELETE FROM visitante WHERE id=?";
        try(PreparedStatement stmt = conexion.getSentencia().getConnection().prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar visitante");
        }
    }


}

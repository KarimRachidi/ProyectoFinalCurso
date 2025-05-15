package src.ClasesDAO;
/*
Creamos la clase ExposicionDAO donde se modifica la exposición
 */

import src.BBDD.ConexionBBDD;
import src.Clases.Exposicion;
import src.Clases.Visitante;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExposicionDAO implements DAO<Exposicion> {
    private final ConexionBBDD conexion;


    public ExposicionDAO(ConexionBBDD conexion) {
        this.conexion = conexion;
    }
    @Override
    public void create(Exposicion exposicion) {
        String sql = "INSERT INTO EXPOSICION(titulo, tipo, descripcion, fecha_creacion) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = conexion.getSentencia().getConnection().prepareStatement(sql)) {
            stmt.setString(1, exposicion.getTitulo());
            stmt.setString(2, exposicion.getTipo());
            stmt.setString(3, exposicion.getDescripcion());
            stmt.setDate(4, Date.valueOf(exposicion.getFechaCreacion()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar exposicion");
        }
    }
    @Override
    public List<Exposicion> readAll() {
        List<Exposicion> lista = new ArrayList<>();
        String sql = "SELECT * FROM EXPOSICION";
        try (ResultSet rs = conexion.getSentencia().executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Exposicion(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("tipo"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_creacion").toLocalDate()

                ));

            }
        } catch (SQLException e) {
            System.err.println("Error al leer los visitantes");
        }
        return lista;
    }

    @Override
    public Exposicion readById(int id) {
        String sql = "SELECT * FROM EXPOSICION WHERE id=?";
        try (PreparedStatement stmt = conexion.getSentencia().getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id); // Reemplaza el primer '?' con el valor de id
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Exposicion(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("tipo"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_creacion").toLocalDate()
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar la exposición por ID");
        }
        return null; //Devuelve null si no se encuentra
    }

    @Override
    public void update(Exposicion exposicion){
        String sql = "UPDATE EXPOSICION SET titulo=?, tipo=?, descripcion=?, fecha_creacion=? WHERE id=?";
        try(PreparedStatement stmt = conexion.getSentencia().getConnection().prepareStatement(sql)){
            stmt.setString(1, exposicion.getTitulo());
            stmt.setString(2, exposicion.getTipo());
            stmt.setString(3, exposicion.getDescripcion());
            stmt.setDate(4, Date.valueOf(exposicion.getFechaCreacion()));
            stmt.setInt(5,exposicion.getId());
            stmt.executeUpdate();
        } catch (SQLException e){
            System.err.println("Error al actualizar exposición.");
        }
    }

    @Override
    public void delete(int id){
        String sql = "DELETE FROM EXPOSICION WHERE id=?";
        try(PreparedStatement stmt = conexion.getSentencia().getConnection().prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar exposición");
        }
    }

}

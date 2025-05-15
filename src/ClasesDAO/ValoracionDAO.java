package src.ClasesDAO;

import src.BBDD.ConexionBBDD;
import src.Clases.Valoracion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ValoracionDAO implements DAO<Valoracion> {

    private final ConexionBBDD conexion;

    public ValoracionDAO(ConexionBBDD conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(Valoracion valoracion) {
        String sql = "INSERT INTO valoracion(nota, comentario, id_visitante, id_exposicion) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.getSentencia().getConnection().prepareStatement(sql)) {
            stmt.setInt(1, valoracion.getNota());
            stmt.setString(2, valoracion.getComentario());
            stmt.setInt(3, valoracion.getIdVisitante());
            stmt.setInt(4, valoracion.getIdExposicion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(" Error al insertar valoración.");
        }
    }

    @Override
    public List<Valoracion> readAll() {
        List<Valoracion> lista = new ArrayList<>();
        String sql = "SELECT * FROM valoracion";
        try (ResultSet rs = conexion.getSentencia().executeQuery(sql)) {
            while (rs.next()) {
                Valoracion valoracion = new Valoracion(
                        rs.getInt("id"),
                        rs.getInt("nota"),
                        rs.getString("comentario"),
                        rs.getInt("id_visitante"),
                        rs.getInt("id_exposicion")
                );
                lista.add(valoracion);
            }
        } catch (SQLException e) {
            System.err.println("  Error al leer las valoraciones.");
        }
        return lista;
    }

    @Override
    public Valoracion readById(int id) {
        String sql = "SELECT * FROM valoracion WHERE id=?";
        try (PreparedStatement stmt = conexion.getSentencia().getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Valoracion(
                        rs.getInt("id"),
                        rs.getInt("nota"),
                        rs.getString("comentario"),
                        rs.getInt("id_visitante"),
                        rs.getInt("id_exposicion")
                );
            }
        } catch (SQLException e) {
            System.err.println(" Error al buscar valoración por ID.");
        }
        return null; // Si no se encuentra
    }

    @Override
    public void update(Valoracion valoracion) {
        String sql = "UPDATE valoracion SET nota=?, comentario=?, id_visitante=?, id_exposicion=? WHERE id=?";
        try (PreparedStatement stmt = conexion.getSentencia().getConnection().prepareStatement(sql)) {
            stmt.setInt(1, valoracion.getNota());
            stmt.setString(2, valoracion.getComentario());
            stmt.setInt(3, valoracion.getIdVisitante());
            stmt.setInt(4, valoracion.getIdExposicion());
            stmt.setInt(5, valoracion.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(" Error al actualizar valoración.");
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM valoracion WHERE id=?";
        try (PreparedStatement stmt = conexion.getSentencia().getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(" Error al eliminar valoración.");
        }
    }
}
package src.Clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/*
Creación de la clase Exposición la cual contará con su id, titulo, tipo, descripcion y la fecha de Creación
de la exposición.
 */
public class Exposicion implements Serializable {
    private int id;
    private String titulo;
    private String tipo;
    private String descripcion;
    private LocalDate fechaCreacion;

    public Exposicion(int id, String titulo, String tipo, String descripcion, LocalDate fechaCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }

    //Generación de getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Exposicion = " +
                "id : " + id +
                ", titulo : " + titulo +
                ", tipo : " + tipo  +
                ", descripcion : " + descripcion  +
                ", fechaCreacion : " + fechaCreacion ;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Exposicion that = (Exposicion) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

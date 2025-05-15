package src.Clases;

/*
Creación de la clase valoración donde se incluye un id único autoincremental, una nota
que va el 1 al 5, un comentario sobre la exposición
y dos claves foráneas como lo son la del visitante y de la exposición.
 */
public class Valoracion {
    private int id;
    private int nota;
    private String comentario;
    private int idVisitante;
    private int idExposicion;

    public Valoracion(int id, int nota, String comentario, int idVisitante, int idExposicion) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.idVisitante = idVisitante;
        this.idExposicion = idExposicion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdVisitante() {
        return idVisitante;
    }

    public void setIdVisitante(int idVisitante) {
        this.idVisitante = idVisitante;
    }

    public int getIdExposicion() {
        return idExposicion;
    }

    public void setIdExposicion(int idExposicion) {
        this.idExposicion = idExposicion;
    }

    @Override
    public String toString() {
        return "Valoracion = " +
                "id : " + id +
                ", nota : " + nota +
                ", comentario : " + comentario +
                ", idVisitante : " + idVisitante +
                ", idExposicion : " + idExposicion;
    }
}

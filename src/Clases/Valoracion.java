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

    @Override
    public String toString() {
        return "Valoracion = " +
                "id : " + id +
                ", nota : " + nota +
                ", comentario : " + comentario  +
                ", idVisitante : " + idVisitante +
                ", idExposicion : " + idExposicion ;
    }
}

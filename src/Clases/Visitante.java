package src.Clases;

import java.util.Objects;

/*Creamos la clase Visitantes, el cual cada visitante tiene un id, un nombre, una edad y el email.
Haré que el id sea la clave primaria*/
public class Visitante {
    private int id;
    private String nombre;
    private int edad;
    private String email;

    //Creamos el constructor
    public Visitante(int id, String nombre, int edad, String email) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
    }

    //Generación de getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getEmail() {
        return email;
    }

    //Generamos el toString, el equals y el hashCode
    @Override
    public String toString() {
        return " Información del visitante = " +
                "id : " + id +
                ", nombre : " + nombre +
                ", edad : " + edad +
                ", email : '" + email
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Visitante that = (Visitante) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

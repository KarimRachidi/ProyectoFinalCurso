package src.ClasesDAO;

import java.util.List;
/*
Interfaz DAO que se usará para poder realizar CRUD de las  distintas
clases que tenemos
 */
public interface DAO<T> {
    // CREATE: Inserta un nuevo objeto en la base de datos
    void create(T obj);

    // READ: Obtiene todos los objetos almacenados
    List<T> readAll();

    // READ: Busca un objeto por su ID
    T readById(int id);

    // UPDATE: Actualiza los datos de un objeto existente
    void update(T obj);

    // DELETE: Elimina un objeto por su ID
    void delete(int id);
}
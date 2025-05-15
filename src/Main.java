package src;

import src.BBDD.ConexionBBDD;
import src.Clases.Exposicion;
import src.Clases.Visitante;
import src.ClasesDAO.ExposicionDAO;
import src.ClasesDAO.VisitanteDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Conectar a la base de datos
        ConexionBBDD conexion = new ConexionBBDD();
        conexion.conectar();

        // Inicializar DAOs
        VisitanteDAO visitanteDAO = new VisitanteDAO(conexion);
        ExposicionDAO exposicionDAO = new ExposicionDAO(conexion);

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MUSEO INTERACTIVO DEL FUTURO ===");
            System.out.println("1. Añadir visitante");
            System.out.println("2. Ver todos los visitantes");
            System.out.println("3. Buscar visitante por ID");
            System.out.println("4. Actualizar visitante");
            System.out.println("5. Eliminar visitante");
            System.out.println("6. Añadir exposición");
            System.out.println("7. Ver todas las exposiciones");
            System.out.println("8. Buscar exposición por ID");
            System.out.println("9. Actualizar exposición");
            System.out.println("10. Eliminar exposición");
            System.out.println("0. Salir");
            System.out.print("Elige opción: ");
            opcion = sc.nextInt(); sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    // AÑADIR VISITANTE
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Edad: ");
                    int edad = sc.nextInt(); sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    Visitante nuevo = new Visitante(0, nombre, edad, email);
                    visitanteDAO.create(nuevo);
                    System.out.println("Visitante añadido.");
                    break;

                case 2:
                    // VER TODOS LOS VISITANTES
                    List<Visitante> listaV = visitanteDAO.readAll();
                    System.out.println("\n--- LISTA DE VISITANTES ---");
                    for (Visitante v : listaV) {
                        System.out.println("ID: " + v.getId() +
                                ", Nombre: " + v.getNombre() +
                                ", Edad: " + v.getEdad() +
                                ", Email: " + v.getEmail());
                    }
                    break;

                case 3:
                    // BUSCAR VISITANTE POR ID
                    System.out.print("Introduce el ID del visitante: ");
                    int idBuscarV = sc.nextInt(); sc.nextLine();
                    Visitante buscadoV = visitanteDAO.readById(idBuscarV);
                    if (buscadoV != null) {
                        System.out.println("Datos del visitante:");
                        System.out.println("ID: " + buscadoV.getId());
                        System.out.println("Nombre: " + buscadoV.getNombre());
                        System.out.println("Edad: " + buscadoV.getEdad());
                        System.out.println("Email: " + buscadoV.getEmail());
                    } else {
                        System.out.println(" No se encontró al visitante.");
                    }
                    break;

                case 4:
                    // ACTUALIZAR VISITANTE
                    System.out.print("Introduce el ID del visitante a actualizar: ");
                    int idActualizarV = sc.nextInt(); sc.nextLine();
                    Visitante actualV = visitanteDAO.readById(idActualizarV);
                    if (actualV != null) {
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = sc.nextLine();
                        System.out.print("Nueva edad: ");
                        int nuevaEdad = sc.nextInt(); sc.nextLine();
                        System.out.print("Nuevo email: ");
                        String nuevoEmail = sc.nextLine();

                        actualV.setNombre(nuevoNombre);
                        actualV.setEdad(nuevaEdad);
                        actualV.setEmail(nuevoEmail);

                        visitanteDAO.update(actualV);
                        System.out.println(" Visitante actualizado.");
                    } else {
                        System.out.println(" Visitante no encontrado.");
                    }
                    break;

                case 5:
                    // ELIMINAR VISITANTE
                    System.out.print("Introduce el ID del visitante a eliminar: ");
                    int idEliminarV = sc.nextInt(); sc.nextLine();
                    visitanteDAO.delete(idEliminarV);
                    System.out.println("Visitante eliminado.");
                    break;

                case 6:
                    // AÑADIR EXPOSICIÓN
                    System.out.print("Título de la exposición: "); String titulo = sc.nextLine();
                    System.out.print("Tipo (arte, ciencia, historia...): "); String tipo = sc.nextLine();
                    System.out.print("Descripción: "); String descripcion = sc.nextLine();

                    Exposicion nuevaE = new Exposicion(
                            0,
                            titulo,
                            tipo,
                            descripcion,
                            LocalDate.now()
                    );
                    exposicionDAO.create(nuevaE);
                    System.out.println(" Exposición añadida.");
                    break;

                case 7:
                    // VER TODAS LAS EXPOSICIONES
                    List<Exposicion> listaE = exposicionDAO.readAll();
                    System.out.println("\n--- LISTA DE EXPOSICIONES ---");
                    for (Exposicion e : listaE) {
                        System.out.println("ID: " + e.getId() +
                                ", Título: " + e.getTitulo() +
                                ", Tipo: " + e.getTipo());
                    }
                    break;

                case 8:
                    // BUSCAR EXPOSICIÓN POR ID
                    System.out.print("Introduce el ID de la exposición: ");
                    int idBuscarE = sc.nextInt(); sc.nextLine();
                    Exposicion buscadaE = exposicionDAO.readById(idBuscarE);
                    if (buscadaE != null) {
                        System.out.println("Datos de la exposición:");
                        System.out.println("Título: " + buscadaE.getTitulo());
                        System.out.println("Tipo: " + buscadaE.getTipo());
                        System.out.println("Fecha de creación: " + buscadaE.getFechaCreacion());
                    } else {
                        System.out.println(" No se encontró la exposición.");
                    }
                    break;

                case 9:
                    // ACTUALIZAR EXPOSICIÓN
                    System.out.print("Introduce el ID de la exposición a actualizar: ");
                    int idActualizarE = sc.nextInt(); sc.nextLine();
                    Exposicion actualE = exposicionDAO.readById(idActualizarE);
                    if (actualE != null) {
                        System.out.print("Nuevo título: ");
                        String nuevoTitulo = sc.nextLine();
                        System.out.print("Nuevo tipo: ");
                        String nuevoTipo = sc.nextLine();
                        System.out.print("Nueva descripción: ");
                        String nuevaDescripcion = sc.nextLine();

                        actualE.setTitulo(nuevoTitulo);
                        actualE.setTipo(nuevoTipo);
                        actualE.setDescripcion(nuevaDescripcion);

                        exposicionDAO.update(actualE);
                        System.out.println(" Exposición actualizada.");
                    } else {
                        System.out.println(" Exposición no encontrada.");
                    }
                    break;

                case 10:
                    // ELIMINAR EXPOSICIÓN
                    System.out.print("Introduce el ID de la exposición a eliminar: ");
                    int idEliminarE = sc.nextInt(); sc.nextLine();
                    exposicionDAO.delete(idEliminarE);
                    System.out.println(" Exposición eliminada.");
                    break;

                case 0:
                    System.out.println(" Saliendo del sistema...");
                    break;

                default:
                    System.out.println(" Opción inválida.");
            }

        } while (opcion != 0);

        // Cerrar conexión al final
        conexion.cerrarConexion();
    }
}
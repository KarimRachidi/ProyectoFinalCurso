package src;

import src.BBDD.ConexionBBDD;
import src.Clases.Exposicion;
import src.Clases.Visitante;
import src.Clases.Valoracion;
import src.ClasesDAO.ExposicionDAO;
import src.ClasesDAO.VisitanteDAO;
import src.ClasesDAO.ValoracionDAO;
import src.archivos.LogVisita;
import src.archivos.ExposicionArchivo;

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
        ValoracionDAO valoracionDAO = new ValoracionDAO(conexion);

        Scanner sc = new Scanner(System.in);
        int opcion;
/*
Aquí creamos el primer menú donde decidiremos a qué submenú iremos
1 -> Para gestionar visitantes
2 -> Para gestionar exposiciones
3 -> Para gestionar valoraciones
0 -> Para salir
 */
        do {
            System.out.println("\n=== MUSEO INTERACTIVO DEL FUTURO ===");
            System.out.println("1. Gestión de visitantes");
            System.out.println("2. Gestión de exposiciones");
            System.out.println("3. Gestión de valoraciones");
            System.out.println("4. Exportar / Importar exposiciones (.dat)");
            System.out.println("5. Incluir log de visita(.txt)");
            System.out.println("6. Sacar los logs");
            System.out.println("7. Ver contenido de exposiciones.dat");
            System.out.println("0. Salir");
            System.out.print("Elige opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    menuVisitantes(sc, visitanteDAO);
                    break;
                case 2:
                    menuExposiciones(sc, exposicionDAO);
                    break;
                case 3:
                    menuValoraciones(sc, valoracionDAO, visitanteDAO, exposicionDAO);
                    break;
                case 4:
                    menuFicherosExposiciones(sc, exposicionDAO);
                    break;
                case 5:
                    meterLogVisita(sc,visitanteDAO,exposicionDAO);
                    break;
                case 6:
                    LogVisita.mostrarLogs();
                    break;
                case 7:
                    verificarExposicionesGuardadas();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        // Cerrar conexión al final
        conexion.cerrarConexion();
    }
    /*
    Método auxiliar para probar que el log de visitas funciona correctamente.
    Se pide al usuario que introduzca los IDs de un visitante y una exposición,
    y se registra manualmente un acceso en el fichero logs_visitas.txt.
     */
    private static void meterLogVisita(Scanner sc, VisitanteDAO visitanteDAO, ExposicionDAO exposicionDAO) {
        System.out.print("ID del visitante: ");
        int idV = sc.nextInt();
        System.out.print("ID de la exposición: ");
        int idE = sc.nextInt();
        sc.nextLine(); //Limpiamos el buffer

        //Buscamos si existen los elementos en la base de datos
        Visitante v = visitanteDAO.readById(idV);
        Exposicion e = exposicionDAO.readById(idE);

        //Si no existe alguno, mostramos mensaje y salimos.
        if (v == null || e == null) {
            System.out.println("Uno de los elementos no existe.");
            return;
        }
        //Si todo es válido, llamamos al método que registra el acceso
        LogVisita.registrarAcceso(v, e);
        System.out.println(" Acceso registrado en el log.");
    }
    /*
   =============== SUBMENÚ: Gestión de visitantes ===============
    Donde gracias a CRUD, se añade un visitante (Create), ver todos los visitantes (Read),
    actualizar un visitante (Update), y eliminar un visitante (Delete).
     */
    private static void menuVisitantes(Scanner sc, VisitanteDAO visitanteDAO) {
        int op;
        do {
            System.out.println("\n--- GESTIÓN DE VISITANTES ---");
            System.out.println("1. Añadir visitante");
            System.out.println("2. Ver todos los visitantes");
            System.out.println("3. Buscar visitante por ID");
            System.out.println("4. Actualizar visitante");
            System.out.println("5. Eliminar visitante");
            System.out.println("0. Volver");

            System.out.print("Elige una opción: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    anyadirVisitante(sc, visitanteDAO);
                    break;
                case 2:
                    verTodosVisitantes(visitanteDAO);
                    break;
                case 3:
                    buscarVisitantePorId(sc, visitanteDAO);
                    break;
                case 4:
                    actualizarVisitante(sc, visitanteDAO);
                    break;
                case 5:
                    eliminarVisitante(sc, visitanteDAO);
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    /*
   =============== SUBMENÚ: Gestión de exposiciones ===============
   Donde gracias a CRUD, se añade una exposición (Create), ver todas las exposiciones (Read),
   actualizar una exposición (Update), y eliminar una exposición (Delete).
    */
    private static void menuExposiciones(Scanner sc, ExposicionDAO exposicionDAO) {
        int op;
        do {
            System.out.println("\n--- GESTIÓN DE EXPOSICIONES ---");
            System.out.println("1. Añadir exposición");
            System.out.println("2. Ver todas las exposiciones");
            System.out.println("3. Buscar exposición por ID");
            System.out.println("4. Actualizar exposición");
            System.out.println("5. Eliminar exposición");
            System.out.println("0. Volver");

            System.out.print("Elige una opción: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    anyadirExposicion(sc, exposicionDAO);
                    break;
                case 2:
                    verTodasExposiciones(exposicionDAO);
                    break;
                case 3:
                    buscarExposicionPorID(sc, exposicionDAO);
                    break;
                case 4:
                    actualizarExposicion(sc, exposicionDAO);
                    break;
                case 5:
                    eliminarExposicion(sc, exposicionDAO);
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    /*
   ===============SUBMENÚ: Gestión de valoraciones ===============
   Donde gracias a CRUD, se añade una valoración (Create), ver todas las valoraciones (Read),
   actualizar una valoración (Update), y eliminar una valoración (Delete).
    */
    private static void menuValoraciones(Scanner sc, ValoracionDAO valoracionDAO, VisitanteDAO visitanteDAO, ExposicionDAO exposicionDAO) {
        int op;
        do {
            System.out.println("\n--- VALORACIONES ---");
            System.out.println("1. Añadir valoración");
            System.out.println("2. Ver todas las valoraciones");
            System.out.println("3. Buscar valoración por ID");
            System.out.println("4. Actualizar valoración");
            System.out.println("5. Eliminar valoración");
            System.out.println("0. Volver");

            System.out.print("Elige una opción: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    anyadirValoracion(sc, valoracionDAO, visitanteDAO, exposicionDAO);
                    break;
                case 2:
                    verTodasLasValoraciones(valoracionDAO);
                    break;
                case 3:
                    buscarValoracionPorID(sc, valoracionDAO);
                    break;
                case 4:
                    actualizarValoracion(sc, valoracionDAO);
                    break;
                case 5:
                    eliminarValoracion(sc, valoracionDAO);
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (op != 0);
    }

    /*
    =============== MÉTODOS DE LOS VISITANTES ===============
    Métodos que se utilizan para manejar los distintos visitantes.
    Donde básicamente aplico CRUD anteriormente realizado en la clase VisitanteDAO
     */

    // CREATE
    private static void anyadirVisitante(Scanner sc, VisitanteDAO dao) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Edad: ");
        int edad = sc.nextInt();
        sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        Visitante nuevo = new Visitante(0, nombre, edad, email);
        dao.create(nuevo);
        System.out.println("Visitante añadido.");
    }

    // READ ALL
    private static void verTodosVisitantes(VisitanteDAO dao) {
        List<Visitante> lista = dao.readAll();
        System.out.println("\n--- LISTA DE VISITANTES ---");
        for (Visitante v : lista) {
            System.out.println("ID: " + v.getId() +
                    ", Nombre: " + v.getNombre() +
                    ", Email: " + v.getEmail());
        }
    }

    // READ BY ID
    private static void buscarVisitantePorId(Scanner sc, VisitanteDAO dao) {
        System.out.print("Introduce el ID del visitante: ");
        int id = sc.nextInt();
        sc.nextLine();
        Visitante v = dao.readById(id);
        if (v != null) {
            System.out.println("Datos del visitante:");
            System.out.println("Nombre: " + v.getNombre());
            System.out.println("Edad: " + v.getEdad());
            System.out.println("Email: " + v.getEmail());
        } else {
            System.out.println("No se encontró al visitante.");
        }
    }

    // UPDATE
    private static void actualizarVisitante(Scanner sc, VisitanteDAO dao) {
        System.out.print("ID del visitante a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Visitante actual = dao.readById(id);
        if (actual != null) {
            System.out.print("Nuevo nombre (" + actual.getNombre() + "): ");
            String nuevoNombre = sc.nextLine();
            System.out.print("Nueva edad (" + actual.getEdad() + "): ");
            int nuevaEdad = sc.nextInt();
            sc.nextLine();
            System.out.print("Nuevo email (" + actual.getEmail() + "): ");
            String nuevoEmail = sc.nextLine();

            actual.setNombre(nuevoNombre);
            actual.setEdad(nuevaEdad);
            actual.setEmail(nuevoEmail);
            dao.update(actual);
            System.out.println("Visitante actualizado.");
        } else {
            System.out.println("Visitante no encontrado.");
        }
    }

    // DELETE
    private static void eliminarVisitante(Scanner sc, VisitanteDAO dao) {
        System.out.print("ID del visitante a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        dao.delete(id);
        System.out.println("Visitante eliminado.");
    }
  /*
    =============== MÉTODOS DE LAS EXPOSICIONES ===============
    Métodos que se utilizan para manejar las distintas exposiciones
     */

    // CREATE
    private static void anyadirExposicion(Scanner sc, ExposicionDAO dao) {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Tipo (arte, ciencia...): ");
        String tipo = sc.nextLine();
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

        Exposicion nueva = new Exposicion(0, titulo, tipo, descripcion, LocalDate.now());
        dao.create(nueva);
        System.out.println("Exposición añadida.");
    }

    // READ ALL
    private static void verTodasExposiciones(ExposicionDAO dao) {
        List<Exposicion> lista = dao.readAll();
        System.out.println("\n--- LISTA DE EXPOSICIONES ---");
        for (Exposicion e : lista) {
            System.out.println("ID: " + e.getId() +
                    ", Título: " + e.getTitulo() +
                    ", Tipo: " + e.getTipo());
        }
    }

    // READ BY ID
    private static void buscarExposicionPorID(Scanner sc, ExposicionDAO dao) {
        System.out.print("Introduce el ID de la exposición: ");
        int id = sc.nextInt();
        sc.nextLine();
        Exposicion e = dao.readById(id);
        if (e != null) {
            System.out.println("Título: " + e.getTitulo());
            System.out.println("Tipo: " + e.getTipo());
            System.out.println("Fecha de creación: " + e.getFechaCreacion());
        } else {
            System.out.println("No se encontró la exposición.");
        }
    }

    // UPDATE
    private static void actualizarExposicion(Scanner sc, ExposicionDAO dao) {
        System.out.print("ID de la exposición a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Exposicion actual = dao.readById(id);
        if (actual != null) {
            System.out.print("Nuevo título (" + actual.getTitulo() + "): ");
            String nuevoTitulo = sc.nextLine();
            System.out.print("Nuevo tipo (" + actual.getTipo() + "): ");
            String nuevoTipo = sc.nextLine();
            System.out.print("Nueva descripción (" + actual.getDescripcion() + "): ");
            String nuevaDescripcion = sc.nextLine();

            actual.setTitulo(nuevoTitulo);
            actual.setTipo(nuevoTipo);
            actual.setDescripcion(nuevaDescripcion);
            actual.setFechaCreacion(LocalDate.now());

            dao.update(actual);
            System.out.println("Exposición actualizada.");
        } else {
            System.out.println("Exposición no encontrada.");
        }
    }

    // DELETE
    private static void eliminarExposicion(Scanner sc, ExposicionDAO dao) {
        System.out.print("ID de la exposición a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        dao.delete(id);
        System.out.println("Exposición eliminada.");
    }

      /*
    =============== MÉTODOS DE LAS VALORACIONES ===============
    Métodos que se utilizan para manejar las distintas valoraciones
     */

    // CREATE
    private static void anyadirValoracion(Scanner sc, ValoracionDAO valoracionDAO, VisitanteDAO visitanteDAO, ExposicionDAO exposicionDAO) {
        // Mostrar listado de visitantes
        System.out.println("Lista de visitantes:");
        List<Visitante> listaVisitantes = visitanteDAO.readAll();
        for (int i = 0; i < listaVisitantes.size(); i++) {
            Visitante v = listaVisitantes.get(i);
            System.out.println("ID: " + v.getId() + " - Nombre: " + v.getNombre());
        }

        // READ ALL
        System.out.println("Lista de exposiciones:");
        List<Exposicion> listaExposiciones = exposicionDAO.readAll();
        for (int i = 0; i < listaExposiciones.size(); i++) {
            Exposicion e = listaExposiciones.get(i);
            System.out.println("ID: " + e.getId() + " - Título: " + e.getTitulo());
        }

        // Solicitar datos al usuario
        System.out.print("ID del visitante: ");
        int idVisitante = sc.nextInt();
        System.out.print("ID de la exposición: ");
        int idExposicion = sc.nextInt();
        System.out.print("Nota (1-5): ");
        int nota = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        System.out.print("Comentario: ");
        String comentario = sc.nextLine();

        // Crear objeto Valoración
        Valoracion val = new Valoracion(0, nota, comentario, idVisitante, idExposicion);
        valoracionDAO.create(val);

        // Registrar acceso en LogVisita
        Visitante v = visitanteDAO.readById(idVisitante);
        Exposicion e = exposicionDAO.readById(idExposicion);

        if (v != null && e != null) {
            LogVisita.registrarAcceso(v, e);
            System.out.println("Registro de visita guardado.");
        } else {
            System.out.println("Uno de los elementos no existe. No se registró el log.");
        }
    }

    private static void verTodasLasValoraciones(ValoracionDAO valoracionDAO) {
        List<Valoracion> lista = valoracionDAO.readAll();
        System.out.println("\n--- LISTA DE VALORACIONES ---");
        for (Valoracion v : lista) {
            System.out.println("ID: " + v.getId() +
                    ", Nota: " + v.getNota() +
                    ", Comentario: " + v.getComentario());
        }
    }
    // READ BY ID
    private static void buscarValoracionPorID(Scanner sc, ValoracionDAO valoracionDAO) {
        System.out.print("Introduce el ID de la valoración: ");
        int id = sc.nextInt();
        sc.nextLine();
        Valoracion val = valoracionDAO.readById(id);
        if (val != null) {
            System.out.println("Nota: " + val.getNota());
            System.out.println("Comentario: " + val.getComentario());
        } else {
            System.out.println("Valoración no encontrada.");
        }
    }

    //UPDATE
    private static void actualizarValoracion(Scanner sc, ValoracionDAO valoracionDAO) {
        System.out.print("ID de la valoración a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Valoracion actual = valoracionDAO.readById(id);
        if (actual != null) {
            System.out.print("Nueva nota (1-5) [" + actual.getNota() + "]: ");
            int nuevaNota = sc.nextInt();
            sc.nextLine();
            System.out.print("Nuevo comentario [" + actual.getComentario() + "]: ");
            String nuevoComentario = sc.nextLine();

            actual.setNota(nuevaNota);
            actual.setComentario(nuevoComentario);

            valoracionDAO.update(actual);
            System.out.println(" Valoración actualizada.");
        } else {
            System.out.println("Valoración no encontrada.");
        }
    }
    // DELETE
    private static void eliminarValoracion(Scanner sc, ValoracionDAO valoracionDAO) {
        System.out.print("ID de la valoración a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        valoracionDAO.delete(id);
        System.out.println("Valoración eliminada.");
    }
    /*
     /*
   =============== SUBMENÚ: Gestión de ficheros ===============
     */
    private static void menuFicherosExposiciones(Scanner sc, ExposicionDAO exposicionDAO) {
        int op;
        do {
            System.out.println("\n--- FICHEROS DE EXPOSICIONES ---");
            System.out.println("1. Exportar todas las exposiciones a .dat");
            System.out.println("2. Importar exposiciones desde .dat");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige opción: ");
            op = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (op) {
                case 1:
                    // EXPORTAR TODAS LAS EXPOSICIONES ACTUALES A UN FICHERO BINARIO
                    List<Exposicion> expLista = exposicionDAO.readAll();
                    if (expLista.isEmpty()) {
                        System.out.println("No hay exposiciones para exportar.");
                        break;
                    }
                    ExposicionArchivo.guardar(expLista); // Guarda como objeto serializado
                    System.out.println("Exposiciones guardadas en .dat");
                    break;

                case 2:
                    // IMPORTAR DESDE FICHERO BINARIO
                    List<Exposicion> expCargadas = ExposicionArchivo.cargar();
                    if (expCargadas.isEmpty()) {
                        System.out.println("No hay exposiciones guardadas aún.");
                        break;
                    }
                    System.out.println("Exposiciones importadas:");
                    for (Exposicion e : expCargadas) {
                        System.out.println("- " + e.getTitulo());
                    }
                    break;

                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (op != 0);

    }

    private static void verificarExposicionesGuardadas() {
        List<Exposicion> lista = ExposicionArchivo.cargar();

        if (lista.isEmpty()) {
            System.out.println("El archivo está vacío o no existe");
            return;
        }

        System.out.println("\n--- CONTENIDO DE exposiciones.dat ---");
        for (Exposicion e : lista) {
            System.out.println("Título: " + e.getTitulo());
            System.out.println("Tipo: " + e.getTipo());
            System.out.println("Descripción: " + e.getDescripcion());
            System.out.println("Fecha creación: " + e.getFechaCreacion());
            System.out.println("----------------------------------");
        }
    }
}
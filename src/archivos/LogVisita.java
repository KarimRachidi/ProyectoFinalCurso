package src.archivos;

import src.Clases.Exposicion;
import src.Clases.Visitante;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Esta clase registra cada vez que un visitante accede a una exposición
 * Los registros se guardan en un fichero de texto plano (logs_visitas.txt)
 */
public class LogVisita {

    // Ruta del fichero de logs
    private static final String LOG_PATH = "recursos/data/logs_visitas.txt";

    public static void registrarAcceso(Visitante v, Exposicion e) {
        // Formato de fecha actual
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        // Línea que vamos a escribir en el log
        String linea = String.format("[%s] - VISITA - %s accedió a \"%s\"", fecha, v.getNombre(), e.getTitulo());

        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_PATH, true)) // true -> añadir sin sobrescribir
        ) {
            writer.write(linea);
            writer.newLine(); // Saltamos a la siguiente línea
        } catch (IOException ex) {
            System.err.println("Error al escribir en el log de visitas.");
        }
    }

    /**
     * Muestra todos los registros del log por consola
     */
    public static void mostrarLogs() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(LOG_PATH))
        ) {
            System.out.println("\n--- REGISTROS DE ACCESO ---");
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException ex) {
            System.out.println("No hay registros de visita aún.");
        }
    }
}
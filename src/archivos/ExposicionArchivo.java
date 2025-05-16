package src.archivos;

import src.Clases.Exposicion;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Esta clase permite guardar y cargar exposiciones desde un fichero binario (.dat)

 */
public class ExposicionArchivo {

    // Ruta del fichero donde se guardarán las exposiciones
    private static final String FILE_PATH = "recursos/data/exposiciones.dat";


    public static void guardar(List<Exposicion> lista) {
        try (
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))
        ) {
            out.writeObject(lista); // Escribimos toda la lista en el archivo
            System.out.println(" Exposición(es) exportada(s) correctamente.");
        } catch (IOException e) {
            System.err.println(" Error al guardar exposiciones en .dat");
        }
    }


    public static List<Exposicion> cargar() {
        List<Exposicion> lista = new ArrayList<>();
        try (
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))
        ) {
            lista = (List<Exposicion>) in.readObject(); // Leemos la lista completa
            System.out.println(" Exposiciones importadas desde .dat");
        } catch (Exception e) {
            System.out.println(" No hay exposiciones guardadas aún.");
        }
        return lista;
    }
}

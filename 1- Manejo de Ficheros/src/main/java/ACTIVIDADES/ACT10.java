package ACTIVIDADES;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ACT10 {

    public static void main(String[] args) {
        combinarArchivos("src/main/resources/datos.txt", "src/main/resources/datos_personas.txt", "src/main/resources/combinado.txt");
    }

    public static void combinarArchivos(String ruta1, String ruta2, String rutaSalida) {

        try {
            Scanner lector1 = new Scanner(new File(ruta1));
            Scanner lector2 = new Scanner(new File(ruta2));
            PrintWriter writer = new PrintWriter(new FileWriter(rutaSalida));

            // Mientras queden palabras en alguno de los dos archivos
            while (lector1.hasNext() || lector2.hasNext()) {
                if (lector1.hasNext()) {
                    writer.print(lector1.next() + " ");
                }
                if (lector2.hasNext()) {
                    writer.print(lector2.next() + " ");
                }
            }

            writer.flush();
            lector1.close();
            lector2.close();
            writer.close();

            System.out.println("Archivo combinado creado correctamente.");

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ha habido algún problema.");
            e.printStackTrace();
        }
    }

}

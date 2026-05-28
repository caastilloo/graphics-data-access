package ACTIVIDADES;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ACT8 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Nombre base de los archivos: ");
        String nombre = teclado.nextLine();

        System.out.print("¿Cuántos archivos? ");
        int numArchivos = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Carpeta destino: ");
        String ruta = teclado.nextLine();

        generarArchivos(nombre, numArchivos, ruta);
    }

    // Modificación de la Actividad 4: ahora también escribe contenido
    public static void generarArchivos(String nombre, int numArchivos, String ruta) {
        File carpeta = new File(ruta);

        if (!carpeta.exists()) {
            if (carpeta.mkdirs()) {
                System.out.println("Carpeta creada: " + carpeta.getAbsolutePath());
            } else {
                System.err.println("No se pudo crear la carpeta.");
                return;
            }
        }

        for (int i = 1; i <= numArchivos; i++) {
            String nombreArchivo = nombre + "(" + i + ").txt";
            File archivo = new File(carpeta, nombreArchivo);

            try {
                if (archivo.createNewFile()) {
                    System.out.println("Creado: " + archivo.getName());
                } else {
                    System.out.println("Ya existía: " + archivo.getName());
                }

                // Escribimos la frase dentro del archivo recién creado
                BufferedWriter escritor = new BufferedWriter(
                        new FileWriter(archivo)
                );
                escritor.write("Este es el fichero " + nombreArchivo);
                escritor.close();

            } catch (IOException e) {
                System.out.println("Ha habido algún problema.");
                e.printStackTrace();
            }
        }
    }
}

package ACTIVIDADES;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ACT4 {

    static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduce el nombre base de los archivos: ");
        String nombre = teclado.nextLine();

        System.out.print("¿Cuántos archivos quieres crear? ");
        int numArchivos = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Introduce la ruta de la carpeta destino: ");
        String ruta = teclado.nextLine();

        generarArchivos(nombre, numArchivos, ruta);
    }

    public static void generarArchivos(String nombre, int numArchivos, String ruta) {
        File carpeta = new File(ruta);

        // Crear la carpeta si no existe
        if (!carpeta.exists()) {

            if (carpeta.mkdirs()) {
                System.out.println("Carpeta creada: " + carpeta.getAbsolutePath());
            } else {
                System.out.println("No se pudo crear la carpeta.");
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
            } catch (IOException e) {
                System.out.println("Error creando " + nombreArchivo);
                e.printStackTrace();
            }
        }
    }
}

package ACTIVIDADES;

import java.io.File;
import java.io.IOException;

public class ACT2 {

    static void main(String[] args) {

        File fichero = new File("src/main/resources/ejemplo1.txt");

        try {
            if (fichero.createNewFile()) {
                System.out.println("Archivo creado: " + fichero.getName());
            } else {
                System.out.println("El archivo ya existía.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ahora sí encontrará el fichero y mostrará su info
        if (fichero.exists())
            System.out.println("El fichero " + fichero.getName() + " existe");
        else
            System.out.println("El fichero " + fichero.getName() + " no existe");

        System.out.println("Nombre: " + fichero.getName());
        System.out.println("Longitud: " + fichero.length() + " bytes");
        System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());

        // Información de la carpeta
        File carpeta = new File("src/main/resources");
        System.out.println("\n--- Carpeta ---");
        System.out.println("Nombre: " + carpeta.getName());
        System.out.println("Longitud: " + carpeta.length() + " bytes");
        System.out.println("Ruta absoluta: " + carpeta.getAbsolutePath());
    }
}

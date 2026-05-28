package ACTIVIDADES;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ACT9 {

    public static void main(String[] args) {
        capitalizarPalabras("src/main/resources/datos.txt");
    }

    public static void capitalizarPalabras(String rutaArchivo) {

        File archivo = new File(rutaArchivo);
        File archivoTemp = new File(rutaArchivo + "_temp");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemp));

            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] palabras = linea.split(" ");
                StringBuilder lineaModificada = new StringBuilder();

                for (String palabra : palabras) {
                    if (!palabra.isEmpty()) {
                        // Primera letra en mayúscula + resto de la palabra
                        lineaModificada.append(Character.toUpperCase(palabra.charAt(0)));
                        lineaModificada.append(palabra.substring(1));
                        lineaModificada.append(" ");
                    }
                }

                writer.write(lineaModificada.toString().trim());
                writer.newLine();
            }

            reader.close();
            writer.close();

        } catch (IOException e) {
            System.out.println("Ha habido algún problema.");
            e.printStackTrace();
        }

        // Reemplazar el archivo original por el modificado
        if (archivo.delete()) {
            archivoTemp.renameTo(archivo);
            System.out.println("Archivo modificado correctamente.");
        } else {
            System.out.println("No se ha podido reemplazar el archivo original.");
        }
    }
}

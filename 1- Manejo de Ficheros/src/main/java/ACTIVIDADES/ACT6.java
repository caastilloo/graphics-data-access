package ACTIVIDADES;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ACT6 {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduce la palabra a buscar: ");
        String palabra = teclado.nextLine();

        buscarPalabra("src/main/resources/datos.txt", palabra);

    }

    public static void buscarPalabra(String rutaFichero, String palabra) {
        int contador = 0;

        try {
            BufferedReader lector = new BufferedReader(new FileReader(rutaFichero));

            String linea;

            while ((linea = lector.readLine()) != null) {
                // Dividimos la línea en palabras usando el espacio como separador
                String[] partes = linea.split(" ");

                for (String parte : partes) {
                    if (parte.equalsIgnoreCase(palabra)) {
                        contador++;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            throw new RuntimeException(e);
        }

        System.out.println("La palabra \"" + palabra + "\" aparece " + contador + " veces en el fichero.");
    }
}

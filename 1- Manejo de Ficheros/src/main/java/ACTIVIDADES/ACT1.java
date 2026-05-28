package ACTIVIDADES;

import java.io.File;
import java.io.IOException;

public class ACT1 {

    static void main(String[] args) {

        File archivo = new File("src/main/resources/ejemplo1.txt");

        try {
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            } else {
                System.out.println("El archivo " + archivo.getName() + " ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Ha habido algún problema.");
            e.printStackTrace();
        }

    }
}

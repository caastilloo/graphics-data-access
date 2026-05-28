package PRACTICA;

import java.io.*;
import java.util.ArrayList;

public class DeserializarConManejo {

    static void main(String[] args) {

        ArrayList<Producto> lista = null;

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/main/resources/clase_prueba.ser"));
            lista = (ArrayList<Producto>) input.readObject();
            input.close();
            System.out.println("Fichero leído correctamente.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: el fichero no existe. Creando datos por defecto...");

        } catch (IOException e) {
            System.out.println("Error: el fichero está dañado o no se puede leer. Creando datos por defecto...");

        } catch (ClassNotFoundException e) {
            System.out.println("Error: clase no encontrada.");
        }

        if (lista == null) {

            lista = new ArrayList<>();
            lista.add(new Producto("Producto por defecto", 0.0, 0.0));

            try {
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/clase_prueba.ser"));
                output.writeObject(lista);
                output.close();
                System.out.println("Fichero creado con datos por defecto.");
            } catch (IOException e) {
                System.out.println("No se ha podido crear el fichero por defecto.");
                e.printStackTrace();
            }
        }

        System.out.println("--- Contenido de la lista ---");
        for (Producto producto : lista) {
            System.out.println(producto);
        }
    }
}

package PRACTICA;

import java.io.*;
import java.util.ArrayList;

public class DeserializarLista {

    static void main(String[] args) {

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/main/resources/clase_prueba.ser"));

            ArrayList<Producto> lista = (ArrayList<Producto>) input.readObject();
            input.close();

            System.out.println("--- Lista de productos ---");
            for (Producto producto : lista) {
                System.out.println(producto);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Algo ha ido mal al deserializar.");
            e.printStackTrace();
        }
    }
}

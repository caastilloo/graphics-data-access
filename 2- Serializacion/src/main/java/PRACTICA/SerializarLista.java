package PRACTICA;

import java.io.*;
import java.util.ArrayList;

public class SerializarLista {

    static void main(String[] args) {

        ArrayList<Producto> lista = new ArrayList<>();
        lista.add(new Producto("Teclado", 45.99, 10.0));
        lista.add(new Producto("Ratón", 22.50, 5.0));
        lista.add(new Producto("Monitor", 189.00, 15.0));

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/clase_prueba.ser"));
            output.writeObject(lista);
            output.close();
        } catch (IOException e) {
            System.out.println("Algo ha ido mal al serializar.");
            e.printStackTrace();
        }

        System.out.println("Lista serializada en clase_prueba.ser");
    }
}

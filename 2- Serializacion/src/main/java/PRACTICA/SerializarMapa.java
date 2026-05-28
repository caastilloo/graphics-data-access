package PRACTICA;

import java.io.*;
import java.util.HashMap;

public class SerializarMapa {

    static void main(String[] args) {

        HashMap<String, Producto> mapa = new HashMap<>();
        mapa.put("P003", new Producto("Monitor", 189.00, 15.0));
        mapa.put("P001", new Producto("Teclado", 45.99, 10.0));
        mapa.put("P002", new Producto("Ratón", 22.50, 5.0));

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/mapa.ser"));
            output.writeObject(mapa);
            output.close();
        } catch (IOException e) {
            System.out.println("Algo ha ido mal al serializar.");
            e.printStackTrace();
        }

        System.out.println("HashMap serializado en mapa.ser");
    }
}
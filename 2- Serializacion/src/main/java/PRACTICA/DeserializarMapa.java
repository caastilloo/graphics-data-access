package PRACTICA;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

public class DeserializarMapa {

    static void main(String[] args) {

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/main/resources/mapa.ser"));

            HashMap<String, Producto> mapa = (HashMap<String, Producto>) input.readObject();
            input.close();

            TreeMap<String, Producto> mapaOrdenado = new TreeMap<>(mapa);

            System.out.println("--- Mapa ordenado por clave ---");
            for (String clave : mapaOrdenado.keySet()) {
                System.out.println(clave + " -> " + mapaOrdenado.get(clave));
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Algo ha ido mal al deserializar.");
            e.printStackTrace();
        }
    }
}
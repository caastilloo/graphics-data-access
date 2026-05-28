package PRACTICA;

import java.io.*;
import java.util.ArrayList;

public class DeserializarJerarquia {

    public static void main(String[] args) {

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/main/resources/jerarquia.ser"));

            ArrayList<Empleado> lista = (ArrayList<Empleado>) input.readObject();
            input.close();

            System.out.println("--- Lista de empleados ---");
            for (Empleado empleado : lista) {
                System.out.println(empleado);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Algo ha ido mal al deserializar.");
            e.printStackTrace();
        }
    }
}
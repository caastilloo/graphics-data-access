package PRACTICA;

import java.io.*;
import java.util.ArrayList;

public class SerializarJerarquia {

    public static void main(String[] args) {

        ArrayList<Empleado> lista = new ArrayList<>();
        lista.add(new Empleado("Ana García", 1800.00));
        lista.add(new Jefe("Carlos López", 3200.00, "Desarrollo"));
        lista.add(new Empleado("Marta Ruiz", 1950.00));
        lista.add(new Jefe("Pedro Sanz", 3500.00, "Marketing"));

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/jerarquia.ser"));
            output.writeObject(lista);
            output.close();
        } catch (IOException e) {
            System.out.println("Algo ha ido mal al serializar.");
            e.printStackTrace();
        }

        System.out.println("Lista de empleados y jefes serializada.");
    }
}
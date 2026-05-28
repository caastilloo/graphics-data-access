package PRACTICA;

import java.io.Serializable;

public class Empleado implements Serializable {

    private String nombre;
    private double salario;

    public Empleado(String nombre, double salario) {
        this.nombre = nombre;
        this.salario = salario;
    }

    public String toString() {
        return "Empleado: " + nombre + " | Salario: " + salario + "€";
    }
}
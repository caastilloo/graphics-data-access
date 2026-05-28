package PRACTICA;

import java.io.Serializable;

public class Producto implements Serializable {

    private String nombre;
    private double precio;
    private transient double descuento;

    public Producto(String nombre, double precio, double descuento) {
        this.nombre = nombre;
        this.precio = precio;
        this.descuento = descuento;
    }

    public String toString() {
        return "Nombre: " + nombre + " | Precio: " + precio + "€ | Descuento: " + descuento + "%";
    }
}
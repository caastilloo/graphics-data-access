package PRACTICA1;

import java.util.List;

public class Videojuego {

    private String nombre;
    private String plataforma;
    private double precio;
    private boolean disponible;
    private List<String> generos;

    public Videojuego(String nombre, String plataforma, double precio,
                      boolean disponible, List<String> generos) {
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.precio = precio;
        this.disponible = disponible;
        this.generos = generos;
    }

    public String getNombre()          { return nombre; }
    public String getPlataforma()      { return plataforma; }
    public double getPrecio()          { return precio; }
    public boolean isDisponible()      { return disponible; }
    public List<String> getGeneros()   { return generos; }

    @Override
    public String toString() {
        return "Videojuego [nombre=" + nombre +
                ", plataforma=" + plataforma +
                ", precio=" + precio + "€" +
                ", disponible=" + disponible +
                ", generos=" + generos + "]";
    }
}
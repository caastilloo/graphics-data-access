package PRACTICA.Main;

import java.time.LocalDate;

public class Estudiante {

    private int nia;
    private String nombre;
    private LocalDate fecha_nacimiento;

    public Estudiante(int nia, String nombre, LocalDate fecha_nacimiento) {
        this.nia = nia;
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getNia() {
        return nia;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    @Override
    public String toString() {
        return "Estudiante{nia=" + nia + ", nombre='" + nombre + "', fecha_nacimiento=" + fecha_nacimiento + "}";
    }
}

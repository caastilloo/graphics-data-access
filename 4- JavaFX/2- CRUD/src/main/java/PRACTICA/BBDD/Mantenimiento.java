package PRACTICA.BBDD;

import PRACTICA.Main.Estudiante;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Mantenimiento {

    public static Connection conexion() {
        Connection conexion;
        String host = "jdbc:mariadb://localhost:3307/";
        String user = "root";
        String psw  = "";
        String bd   = "prueba";
        System.out.println("Conectando...");

        try {
            conexion = DriverManager.getConnection(host + bd, user, psw);
            System.out.println("Conexión realizada con éxito.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return conexion;
    }

    public static void desconectar(Connection conexion) {
        System.out.println("Desconectando...");
        try {
            conexion.close();
            System.out.println("Conexión finalizada.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Estudiante> consultar(Connection conexion) {

        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
        String query = "SELECT * FROM estudiantes";

        Statement stmt;
        ResultSet respuesta;

        try {
            stmt      = conexion.createStatement();
            respuesta = stmt.executeQuery(query);

            while (respuesta.next()) {
                int       nia              = respuesta.getInt("nia");
                String    nombre           = respuesta.getString("nombre");
                // Convertir java.sql.Date → LocalDate para el DatePicker
                LocalDate fecha_nacimiento = respuesta.getDate("fecha_nacimiento").toLocalDate();
                listaEstudiantes.add(new Estudiante(nia, nombre, fecha_nacimiento));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return listaEstudiantes;
    }

    public static void insertar(Connection conexion, Estudiante e) {

        System.out.println("Insertando...");
        
        String query = "INSERT INTO estudiantes (nia, nombre, fecha_nacimiento) VALUES ("
                + e.getNia() + ", '"
                + e.getNombre() + "', '"
                + Date.valueOf(e.getFecha_nacimiento()) + "');";

        Statement stmt;

        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public static void modificar(Connection conexion, Estudiante e) {

        System.out.println("Modificando...");

        String query = "UPDATE estudiantes SET nombre = '" + e.getNombre()
                + "', fecha_nacimiento = '" + Date.valueOf(e.getFecha_nacimiento())
                + "' WHERE nia = " + e.getNia();

        Statement stmt;

        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public static void borrar(Connection conexion, Estudiante e) {

        System.out.println("Borrando...");

        String query = "DELETE FROM estudiantes WHERE nia = " + e.getNia();

        Statement stmt;

        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
}

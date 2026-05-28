package practica1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainPractica1 {

    public static void main(String[] args) throws IOException {

        // -------------------------------------------------------
        // a) Crear 3 videojuegos distintos por consola
        // -------------------------------------------------------
        List<Videojuego> coleccion = new ArrayList<>();

        // PS5
        coleccion.add(new Videojuego("God of War Ragnarok", "PS5", 69.99, true,
                Arrays.asList("Accion", "Aventura")));
        coleccion.add(new Videojuego("Spider-Man 2", "PS5", 59.99, true,
                Arrays.asList("Accion", "Mundo abierto")));
        coleccion.add(new Videojuego("Demon's Souls", "PS5", 49.99, false,
                Arrays.asList("RPG", "Accion")));

        // Xbox
        coleccion.add(new Videojuego("Halo Infinite", "Xbox", 39.99, true,
                Arrays.asList("Shooter", "Accion")));
        coleccion.add(new Videojuego("Forza Horizon 5", "Xbox", 59.99, true,
                Arrays.asList("Carreras", "Simulacion")));
        coleccion.add(new Videojuego("Starfield", "Xbox", 69.99, false,
                Arrays.asList("RPG", "Ciencia ficcion")));

        // PC
        coleccion.add(new Videojuego("Minecraft", "PC", 24.99, true,
                Arrays.asList("Aventura", "Supervivencia")));
        coleccion.add(new Videojuego("Cyberpunk 2077", "PC", 39.99, true,
                Arrays.asList("RPG", "Accion")));
        coleccion.add(new Videojuego("Counter-Strike 2", "PC", 0.0, true,
                Arrays.asList("Shooter", "Estrategia")));

        // -------------------------------------------------------
        // b) Guardar la coleccion en videojuegos.json
        //    Construimos el JSON manualmente con StringBuilder,
        //    siguiendo la estructura de pares clave:valor y arrays
        //    que explica la teoria.
        // -------------------------------------------------------
        String json = coleccionAJson(coleccion);

        try (FileWriter writer = new FileWriter("videojuegos.json")) {
            writer.write(json);
        }
        System.out.println("Coleccion guardada en videojuegos.json\n");

        // -------------------------------------------------------
        // c) Leer el archivo y mostrarlo por pantalla
        // -------------------------------------------------------
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("videojuegos.json"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                sb.append(linea).append("\n");
            }
        }
        System.out.println("--- Contenido del archivo ---");
        System.out.println(sb.toString());

        // -------------------------------------------------------
        // d) Reconstruir la coleccion de objetos Java
        //    Parseamos el JSON linea a linea buscando las claves
        //    que definimos al escribirlo.
        // -------------------------------------------------------
        List<Videojuego> coleccionReconstruida = jsonAColeccion(sb.toString());

        System.out.println("--- Coleccion reconstruida como objetos Java ---");
        for (Videojuego v : coleccionReconstruida) {
            System.out.println(v);
        }

        // -------------------------------------------------------
        // e) Añadir un videojuego nuevo y guardar
        // -------------------------------------------------------
        Videojuego nuevo = new Videojuego(
                "Zelda: Tears of the Kingdom", "Switch",
                59.99, true, Arrays.asList("Aventura", "RPG"));

        coleccionReconstruida.add(nuevo);

        try (FileWriter writer = new FileWriter("videojuegos.json")) {
            writer.write(coleccionAJson(coleccionReconstruida));
        }
        System.out.println("\nNuevo videojuego añadido: " + nuevo);
        System.out.println("Coleccion actualizada guardada en videojuegos.json");
    }

    // -------------------------------------------------------
    // Convierte un Videojuego a su bloque JSON
    // -------------------------------------------------------
    private static String videojuegoAJson(Videojuego v) {
        StringBuilder sb = new StringBuilder();
        sb.append("  {\n");
        sb.append("    \"nombre\": \"").append(v.getNombre()).append("\",\n");
        sb.append("    \"plataforma\": \"").append(v.getPlataforma()).append("\",\n");
        sb.append("    \"precio\": ").append(v.getPrecio()).append(",\n");
        sb.append("    \"disponible\": ").append(v.isDisponible()).append(",\n");
        sb.append("    \"generos\": [");
        List<String> generos = v.getGeneros();
        for (int i = 0; i < generos.size(); i++) {
            sb.append("\"").append(generos.get(i)).append("\"");
            if (i < generos.size() - 1) sb.append(", ");
        }
        sb.append("]\n");
        sb.append("  }");
        return sb.toString();
    }

    // -------------------------------------------------------
    // Convierte toda la coleccion a un array JSON
    // -------------------------------------------------------
    private static String coleccionAJson(List<Videojuego> coleccion) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < coleccion.size(); i++) {
            sb.append(videojuegoAJson(coleccion.get(i)));
            if (i < coleccion.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    // -------------------------------------------------------
    // Parsea el JSON linea a linea y devuelve la coleccion
    // -------------------------------------------------------
    private static List<Videojuego> jsonAColeccion(String json) {
        List<Videojuego> lista = new ArrayList<>();
        String[] lineas = json.split("\n");

        String nombre = null, plataforma = null;
        double precio = 0;
        boolean disponible = false;
        List<String> generos = new ArrayList<>();
        boolean dentroObjeto = false;

        for (String linea : lineas) {
            linea = linea.trim();

            if (linea.equals("{")) {
                dentroObjeto = true;
                generos = new ArrayList<>();

            } else if (linea.equals("}") || linea.equals("},")) {
                if (dentroObjeto && nombre != null) {
                    lista.add(new Videojuego(nombre, plataforma, precio, disponible, generos));
                }
                dentroObjeto = false;
                nombre = null;

            } else if (linea.startsWith("\"nombre\"")) {
                nombre = extraerString(linea);

            } else if (linea.startsWith("\"plataforma\"")) {
                plataforma = extraerString(linea);

            } else if (linea.startsWith("\"precio\"")) {
                String val = linea.split(":")[1].trim().replace(",", "");
                precio = Double.parseDouble(val);

            } else if (linea.startsWith("\"disponible\"")) {
                String val = linea.split(":")[1].trim().replace(",", "");
                disponible = Boolean.parseBoolean(val);

            } else if (linea.startsWith("\"generos\"")) {
                generos = extraerGeneros(linea);
            }
        }
        return lista;
    }

    // Extrae el valor de un campo String:  "clave": "valor",
    private static String extraerString(String linea) {
        int inicio = linea.indexOf(": \"") + 3;
        int fin = linea.lastIndexOf("\"");
        return linea.substring(inicio, fin);
    }

    // Extrae los valores de un array:  "generos": ["A", "B"]
    private static List<String> extraerGeneros(String linea) {
        List<String> generos = new ArrayList<>();
        int inicio = linea.indexOf("[") + 1;
        int fin = linea.indexOf("]");
        String contenido = linea.substring(inicio, fin);
        for (String g : contenido.split(",")) {
            generos.add(g.trim().replace("\"", ""));
        }
        return generos;
    }
}
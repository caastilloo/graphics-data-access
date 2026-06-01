package PRACTICA2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainPractica2 {

    public static void main(String[] args) throws Exception {

        // 1. URL de la API Open Trivia DB
        String urlStr = "https://opentdb.com/api.php?amount=5&category=18&difficulty=easy&type=multiple";

        // 2. Hacer la peticion HTTP GET
        URL url = new URL(urlStr);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");

        System.out.println("Codigo HTTP de respuesta: " + conexion.getResponseCode());

        // 3. Leer el JSON de la respuesta
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conexion.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String linea;
        while ((linea = reader.readLine()) != null) {
            sb.append(linea);
        }
        reader.close();

        String json = sb.toString();
        System.out.println("\n--- JSON recibido ---");
        System.out.println(json);

        // 4. Parsear el JSON manualmente y construir objetos Java
        List<Pregunta> preguntas = parsearPreguntas(json);

        // 5. Mostrar las preguntas por pantalla
        System.out.println("\n--- Preguntas de trivia ---");
        int num = 1;
        for (Pregunta p : preguntas) {
            System.out.println("\n[Pregunta " + num + "]");
            System.out.println(p);
            num++;
        }
    }

    // Extrae cada bloque de pregunta del JSON y lo convierte en un objeto Pregunta buscando las claves por su nombre
    private static List<Pregunta> parsearPreguntas(String json) {
        List<Pregunta> lista = new ArrayList<>();

        // El array "results" contiene todos los objetos pregunta.
        // Localizamos su inicio y fin.
        int inicioResults = json.indexOf("\"results\":[") + 11;
        int finResults = json.lastIndexOf("]");
        String results = json.substring(inicioResults, finResults);

        // Separamos cada objeto {} del array
        // Cada objeto empieza con {"category" y termina con }
        int pos = 0;
        while (pos < results.length()) {
            int inicio = results.indexOf("{", pos);
            if (inicio == -1) break;

            // Buscamos el cierre del objeto teniendo en cuenta
            // que dentro puede haber arrays con sus propios []
            int fin = buscarCierreObjeto(results, inicio);
            if (fin == -1) break;

            String bloque = results.substring(inicio, fin + 1);

            String categoria        = extraerCampo(bloque, "category");
            String dificultad       = extraerCampo(bloque, "difficulty");
            String pregunta         = extraerCampo(bloque, "question");
            String correcta         = extraerCampo(bloque, "correct_answer");
            List<String> incorrectas = extraerArray(bloque, "incorrect_answers");

            lista.add(new Pregunta(categoria, dificultad, pregunta, correcta, incorrectas));

            pos = fin + 1;
        }
        return lista;
    }

    // Extrae el valor de un campo String
    private static String extraerCampo(String bloque, String clave) {
        String buscar = "\"" + clave + "\":\"";
        int inicio = bloque.indexOf(buscar);
        if (inicio == -1) return "";
        inicio += buscar.length();
        int fin = bloque.indexOf("\"", inicio);
        return bloque.substring(inicio, fin);
    }

    // Extrae los valores de un array
    private static List<String> extraerArray(String bloque, String clave) {
        List<String> valores = new ArrayList<>();
        String buscar = "\"" + clave + "\":[";
        int inicio = bloque.indexOf(buscar) + buscar.length();
        int fin = bloque.indexOf("]", inicio);
        String contenido = bloque.substring(inicio, fin);

        for (String item : contenido.split(",")) {
            item = item.trim().replace("\"", "");
            if (!item.isEmpty()) valores.add(item);
        }
        return valores;
    }

    // Encuentra la posicion del } que cierra el objeto que empieza en 'inicio'
    private static int buscarCierreObjeto(String texto, int inicio) {
        int profundidad = 0;
        for (int i = inicio; i < texto.length(); i++) {
            if (texto.charAt(i) == '{') profundidad++;
            else if (texto.charAt(i) == '}') {
                profundidad--;
                if (profundidad == 0) return i;
            }
        }
        return -1;
    }
}

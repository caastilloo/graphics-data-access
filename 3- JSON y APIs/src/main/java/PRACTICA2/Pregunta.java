package PRACTICA2;

import java.util.List;

public class Pregunta {

    private String categoria;
    private String dificultad;
    private String pregunta;
    private String respuestaCorrecta;
    private List<String> respuestasIncorrectas;

    public Pregunta(String categoria, String dificultad, String pregunta,
                    String respuestaCorrecta, List<String> respuestasIncorrectas) {
        this.categoria = categoria;
        this.dificultad = dificultad;
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestasIncorrectas = respuestasIncorrectas;
    }

    @Override
    public String toString() {
        return "Categoria: " + categoria +
                "\nDificultad: " + dificultad +
                "\nPregunta: " + pregunta +
                "\nRespuesta correcta: " + respuestaCorrecta +
                "\nRespuestas incorrectas: " + respuestasIncorrectas;
    }
}
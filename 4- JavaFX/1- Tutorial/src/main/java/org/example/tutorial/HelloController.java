package org.example.tutorial;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {

    @FXML
    private Label welcomeText;
    @FXML
    private Button boton;
    @FXML
    private Button contador;
    @FXML
    private Label contando;

    @FXML
    public void initialize() {
        boton.setOnAction(e -> System.out.println("Hola"));

        Contador cont = new Contador();
        contador.setOnAction(e -> {
            cont.contar();
            contando.setText(Integer.toString(cont.getContador()));
        });
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void irAPantalla2() throws IOException {
        HelloApplication.setRoot("segunda-pantalla");
    }
}

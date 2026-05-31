package org.example.tutorial;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SegundaPantallaController {

    @FXML private TextField nombreTextField;
    @FXML private TextField edadTextField;
    @FXML private TableView<Persona> tablaPersonas;
    @FXML private TableColumn<Persona, String> columnaNombre;
    @FXML private TableColumn<Persona, Integer> columnaEdad;

    @FXML
    public void initialize() {
        columnaNombre.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getNombre())
        );
        columnaEdad.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getEdad()).asObject()
        );

        tablaPersonas.setItems(RepositorioPersonas.getPersonas());
    }

    @FXML
    private void guardarPersona() {
        String nombre = nombreTextField.getText();
        int edad;

        try {
            edad = Integer.parseInt(edadTextField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Edad inválida");
            return;
        }

        RepositorioPersonas.insertarPersona(new Persona(nombre, edad));
        System.out.println("Persona creada: " + nombre + ", " + edad);

        nombreTextField.clear();
        edadTextField.clear();
    }

    @FXML
    public void irAPantallaHello() throws IOException {
        HelloApplication.setRoot("hello-view");
    }
}
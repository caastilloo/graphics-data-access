package org.example.tutorial;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RepositorioPersonas {

    private static final ObservableList<Persona> personas =
            FXCollections.observableArrayList();

    public static ObservableList<Persona> getPersonas() {
        return personas;
    }

    public static void insertarPersona(Persona persona) {
        personas.add(persona);
    }
}
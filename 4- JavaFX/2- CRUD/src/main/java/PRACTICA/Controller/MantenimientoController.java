package PRACTICA.Controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import PRACTICA.BBDD.Mantenimiento;
import PRACTICA.Main.Estudiante;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

public class MantenimientoController {

    // ── TableView y columnas ──────────────────────────────────────────────
    @FXML private TableView<Estudiante>          tablaEstudiantes;
    @FXML private TableColumn<Estudiante, Integer>   columnaNia;
    @FXML private TableColumn<Estudiante, String>    columnaNombre;
    @FXML private TableColumn<Estudiante, LocalDate> columnaFechaNac;

    // ── Formulario ────────────────────────────────────────────────────────
    @FXML private TextField  niaTextField;
    @FXML private TextField  nombreTextField;
    @FXML private DatePicker fechaNacPicker;

    // ── Botones ───────────────────────────────────────────────────────────
    @FXML private Button btnInsertar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnGuardar;

    // ── Conexión BD ───────────────────────────────────────────────────────
    private Connection bd;

    // ─────────────────────────────────────────────────────────────────────
    // initialize(): se ejecuta automáticamente al cargar el FXML
    // ─────────────────────────────────────────────────────────────────────
    @FXML
    public void initialize() {

        // Conectar a la base de datos
        bd = Mantenimiento.conexion();

        // Configurar columnas de la tabla
        columnaNia.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(data.getValue().getNia())
        );
        columnaNombre.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(data.getValue().getNombre())
        );
        // Columna de fecha — configuración exacta indicada por la profesora
        columnaFechaNac.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(data.getValue().getFecha_nacimiento())
        );

        // Guardar deshabilitado por defecto
        btnGuardar.setDisable(true);

        // Cargar datos iniciales en la tabla
        cargarTabla();
    }

    // ─────────────────────────────────────────────────────────────────────
    // Método auxiliar: recarga la TableView desde la BD
    // ─────────────────────────────────────────────────────────────────────
    private void cargarTabla() {
        ArrayList<Estudiante> lista = Mantenimiento.consultar(bd);
        ObservableList<Estudiante> observableList = FXCollections.observableArrayList(lista);
        tablaEstudiantes.setItems(observableList);
    }

    // ─────────────────────────────────────────────────────────────────────
    // Botón INSERTAR → INSERT en BD
    // ─────────────────────────────────────────────────────────────────────
    @FXML
    private void onInsertar() {

        int       nia    = Integer.parseInt(niaTextField.getText());
        String    nombre = nombreTextField.getText();
        LocalDate fecha  = fechaNacPicker.getValue();

        if (nombre.isEmpty() || fecha == null) {
            System.out.println("Faltan datos en el formulario.");
            return;
        }

        Estudiante nuevo = new Estudiante(nia, nombre, fecha);
        Mantenimiento.insertar(bd, nuevo);

        limpiarFormulario();
        cargarTabla();
    }

    // ─────────────────────────────────────────────────────────────────────
    // Botón ELIMINAR → DELETE en BD con la fila seleccionada
    // ─────────────────────────────────────────────────────────────────────
    @FXML
    private void onEliminar() {

        Estudiante seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            Mantenimiento.borrar(bd, seleccionado);
            cargarTabla();
        } else {
            System.out.println("No hay ninguna fila seleccionada.");
        }
    }

    // ─────────────────────────────────────────────────────────────────────
    // Botón EDITAR → carga la fila seleccionada en el formulario
    //   · Habilita Guardar
    //   · Deshabilita Insertar
    // ─────────────────────────────────────────────────────────────────────
    @FXML
    private void onEditar() {

        Estudiante seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            niaTextField.setText(String.valueOf(seleccionado.getNia()));
            nombreTextField.setText(seleccionado.getNombre());
            fechaNacPicker.setValue(seleccionado.getFecha_nacimiento());

            // Cambiar estado de los botones durante la edición
            btnGuardar.setDisable(false);
            btnInsertar.setDisable(true);
        } else {
            System.out.println("No hay ninguna fila seleccionada.");
        }
    }

    // ─────────────────────────────────────────────────────────────────────
    // Botón GUARDAR → UPDATE en BD con los valores del formulario
    //   · Vuelve a deshabilitar Guardar
    //   · Vuelve a habilitar Insertar
    // ─────────────────────────────────────────────────────────────────────
    @FXML
    private void onGuardar() {

        int       nia    = Integer.parseInt(niaTextField.getText());
        String    nombre = nombreTextField.getText();
        LocalDate fecha  = fechaNacPicker.getValue();

        Estudiante actualizado = new Estudiante(nia, nombre, fecha);
        Mantenimiento.modificar(bd, actualizado);

        // Restaurar estado de los botones
        btnGuardar.setDisable(true);
        btnInsertar.setDisable(false);

        limpiarFormulario();
        cargarTabla();
    }

    // ─────────────────────────────────────────────────────────────────────
    // Auxiliar: limpia los campos del formulario
    // ─────────────────────────────────────────────────────────────────────
    private void limpiarFormulario() {
        niaTextField.clear();
        nombreTextField.clear();
        fechaNacPicker.setValue(null);
    }
}

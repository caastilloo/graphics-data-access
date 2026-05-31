# Tema 8.1 — Introducción a JavaFX

Tutorial guiado del tema 8.1 de la asignatura **Acceso a Datos / Gráficos**. Cubre los conceptos fundamentales de JavaFX siguiendo el patrón MVC: desde la primera ventana hasta la navegación entre pantallas, formularios y tablas con persistencia en memoria.

> Todo el contenido está explicado paso a paso en el archivo [`[PDF] Practica 1`](./docs/javafx_teoria_guiada.pdf), incluyendo fragmentos de código comentados y la evolución progresiva de cada clase.

---

## Estructura del proyecto

```
src/main/java/org/example/tutorial/
├── HelloApplication.java           ← Clase principal
├── HelloController.java            ← Controlador pantalla 1
├── SegundaPantallaController.java  ← Controlador pantalla 2
├── Contador.java                   ← Modelo: lógica del contador
├── Persona.java                    ← Modelo: datos de una persona
└── RepositorioPersonas.java        ← Repositorio estático de personas

src/main/resources/org/example/tutorial/
├── hello-view.fxml                 ← Vista pantalla 1
└── segunda-pantalla.fxml           ← Vista pantalla 2
```

---

## Qué hace cada clase

### `HelloApplication.java`
Clase principal que arranca la aplicación JavaFX. Gestiona la `Scene` como campo estático, lo que permite a cualquier controlador cambiar la pantalla activa mediante `setRoot()` sin abrir nuevas ventanas.

### `HelloController.java`
Controlador de la primera pantalla. Demuestra dos formas de conectar botones con lógica:
- Mediante `onAction` en el FXML (`#onHelloButtonClick`).
- Mediante `setOnAction` con lambda dentro de `initialize()`.

También integra el modelo `Contador` para mostrar un valor incremental en pantalla y navega a la segunda pantalla con `irAPantalla2()`.

### `SegundaPantallaController.java`
Controlador de la segunda pantalla. Contiene un formulario con dos `TextField` (nombre y edad) que crea objetos `Persona` y los guarda en el repositorio. Muestra todas las personas en una `TableView` reactiva y permite volver a la pantalla principal.

### `Contador.java`
Modelo sencillo que encapsula un entero con los métodos `contar()` y `getContador()`. Ilustra por qué la lógica debe vivir en el Modelo y no como variable estática en el Controlador.

### `Persona.java`
Modelo con dos atributos (`nombre`, `edad`) y sus getters. Es el objeto que el usuario crea desde el formulario y que se almacena y visualiza en la tabla.

### `RepositorioPersonas.java`
Almacén global basado en una `ObservableList<Persona>` estática. Al ser `static final`, la lista sobrevive a los cambios de pantalla (el controlador se reinicia, el repositorio no). La `TableView` se suscribe a esta lista y se actualiza automáticamente cuando se añade una persona.

---

## Conceptos cubiertos

| Concepto | Dónde se ve |
|---|---|
| Patrón MVC en JavaFX | Todas las clases |
| Anotación `@FXML` y `fx:id` | `HelloController`, `SegundaPantallaController` |
| `initialize()` y lambdas | `HelloController` |
| Navegación entre pantallas con `setRoot` | `HelloApplication`, ambos controladores |
| Formularios con `TextField` | `SegundaPantallaController`, `segunda-pantalla.fxml` |
| `TableView` + `ObservableList` | `SegundaPantallaController` |
| `setCellValueFactory` | `SegundaPantallaController` |
| Persistencia en memoria con repositorio estático | `RepositorioPersonas` |

---

## Requisitos

- Java 17+
- JavaFX 17+ (incluido como dependencia Maven en `pom.xml`)
- Maven 3.8+

## Ejecución

```bash
mvn javafx:run
```
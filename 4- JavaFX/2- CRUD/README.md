# CRUD JavaFX — Mantenimiento de Estudiantes

Aplicación de escritorio desarrollada en Java con JavaFX que implementa un CRUD completo sobre una tabla de estudiantes almacenada en MariaDB.

## Tecnologías

- Java 25
- JavaFX 21.0.2
- MariaDB JDBC 3.3.2
- Maven

## Estructura del proyecto

```
src/
└── main/
    ├── java/
    │   └── PRACTICA/
    │       ├── Launcher.java                        ← Punto de entrada (lanza MainApp)
    │       ├── MainApp.java                         ← Clase principal JavaFX
    │       ├── BBDD/
    │       │   └── Mantenimiento.java               ← Acceso a BD (SELECT, INSERT, UPDATE, DELETE)
    │       ├── Controller/
    │       │   └── MantenimientoController.java     ← Controlador de la vista FXML
    │       └── Main/
    │           └── Estudiante.java                  ← Modelo de datos
    └── resources/
        └── PRACTICA/
            └── mantenimiento-view.fxml              ← Interfaz gráfica
```

## Base de datos

La aplicación se conecta a MariaDB con esta configuración:

| Parámetro | Valor     |
|-----------|-----------|
| Host      | localhost |
| Puerto    | 3307      |
| Base de datos | prueba |
| Usuario   | root      |
| Contraseña | *(vacía)* |

### Crear la base de datos y la tabla

```sql
CREATE DATABASE prueba;
USE prueba;

CREATE TABLE estudiantes (
    nia              INTEGER      NOT NULL,
    nombre           VARCHAR(20)  NOT NULL,
    fecha_nacimiento DATE         NOT NULL,
    CONSTRAINT pk_estudiante PRIMARY KEY (nia)
);
```

### Datos de prueba

```sql
INSERT INTO estudiantes VALUES (12345678,  'Pedro',  '2010-04-28');
INSERT INTO estudiantes VALUES (87654321,  'Aitana', '2005-04-10');
INSERT INTO estudiantes VALUES (123487654, 'Pepe',   '2005-03-28');
INSERT INTO estudiantes VALUES (43215678,  'Carla',  '2000-12-28');
```

## Funcionalidad

La interfaz muestra una `TableView` con los estudiantes cargados desde la base de datos y un formulario para operar sobre ellos.

| Botón | Operación | Comportamiento |
|-------|-----------|----------------|
| **Insertar** | INSERT | Lee NIA, Nombre y Fecha del formulario, inserta el registro y recarga la tabla |
| **Editar** | — | Carga la fila seleccionada en el formulario; habilita Guardar y deshabilita Insertar |
| **Guardar** | UPDATE | Actualiza el registro por NIA con los valores del formulario |
| **Eliminar** | DELETE | Elimina la fila seleccionada filtrando por NIA |

## Cómo ejecutar

1. Tener MariaDB corriendo en `localhost:3307` con la base de datos `prueba` creada.
2. Compilar y ejecutar con Maven:

```bash
mvn clean javafx:run
```
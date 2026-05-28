# Prácticas Java — GRAPHICS-DATA-ACCESS

Colección de ejercicios y prácticas en Java que cubren interfaces gráficas con **JavaFX**, manejo de ficheros, serialización de objetos, procesamiento de JSON e integración con APIs REST. Realizados como parte del ciclo formativo **Desarrollo de Aplicaciones Web (DAW)**.

---

## Contenido

- [Tecnologías](#tecnologías)
- [Estructura del repositorio](#estructura-del-repositorio)
- [Módulos](#módulos)
  - [Ficheros](#ficheros)
  - [Serialización](#serialización)
  - [JSON y APIs](#json-y-apis)
  - [JavaFX](#javafx)
- [Ejecución](#ejecución)

---

## Estructura del repositorio

```
java-practicas/
├── ficheros/
│   └── src/main/
│       ├── java/org/example/
│       │   ├── Main.java
│       │   ├── Leer.java
│       │   └── Escribir.java
│       └── resources/
│           ├── datos.txt
│           └── ...
│
├── serializacion/
│   └── src/main/
│       ├── java/
│       │   ├── Producto.java
│       │   ├── Empleado.java
│       │   ├── Jefe.java
│       │   └── ...
│       └── resources/
│           ├── clase_prueba.ser
│           ├── mapa.ser
│           └── jerarquia.ser
│
├── json-apis/
│   └── src/main/java/
│       ├── practica1/
│       │   ├── Videojuego.java
│       │   └── MainPractica1.java
│       └── practica2/
│           ├── Pregunta.java
│           └── MainPractica2.java
│
└── javafx/
    └── src/main/
        ├── java/
        └── resources/
```

---

## Módulos

### Ficheros

Manejo de archivos y directorios mediante las clases principales de `java.io`.

**Clases trabajadas:** `File`, `FileReader`, `BufferedReader`, `FileWriter`, `BufferedWriter`, `PrintWriter`, `Scanner`.

| Actividad | Descripción |
|---|---|
| Act. 1 | Crear archivo en subcarpeta `src/main/resources/` |
| Act. 2 | Crear archivo y mostrar información (`exists()`, `getName()`, `length()`, `getAbsolutePath()`) |
| Act. 3 | Observar cómo varía `length()` según el contenido (ASCII vs UTF-8) |
| Act. 4 | Generar *n* archivos en una carpeta elegida por el usuario |
| Act. 5 | Listar archivos por extensión; sobrecarga que permite filtrar por cualquier extensión |
| Act. 6 | Buscar palabra en fichero con `BufferedReader` (comparación insensible a mayúsculas) |
| Act. 7 | Buscar palabra en fichero con `Scanner`, aprovechando la separación automática por tokens |
| Act. 8 | Generar archivos con contenido usando `BufferedWriter` |
| Act. 9 | Capitalizar cada palabra de un fichero usando un archivo temporal y `renameTo()` |
| Act. 10 | Combinar dos ficheros palabra a palabra con dos `Scanner` y `PrintWriter` |

---

### Serialización

Serialización y deserialización de objetos en Java mediante `ObjectOutputStream` / `ObjectInputStream`.

**Concepto clave:** una clase debe implementar `Serializable` para poder serializarse. Los atributos `transient` se ignoran y recuperan su valor por defecto al deserializar (`0`, `false`, `null`...).

| Ejercicio | Descripción |
|---|---|
| EJ1 | Serializar y deserializar un `ArrayList` de objetos `Producto` |
| EJ2 | Uso del modificador `transient` en el atributo `descuento` |
| EJ3 | Manejo de excepciones (`FileNotFoundException`, `IOException`) y creación de datos por defecto |
| EJ4 | Serializar un `HashMap` y deserializarlo ordenado con `TreeMap` |
| EJ5 | Jerarquía de clases (`Empleado` / `Jefe`) y polimorfismo al recuperar objetos |

> **Nota de seguridad:** deserializar datos de fuentes no confiables puede suponer un riesgo de seguridad. Aplicar siempre validaciones y trabajar únicamente con orígenes conocidos.

---

### JSON y APIs

Trabajo con ficheros JSON y consumo de APIs REST usando **Java puro**, sin librerías externas.

#### Práctica 1 — Inventario de videojuegos

Gestión de un inventario en `videojuegos.json`: creación, serialización manual con `StringBuilder`, lectura, parseo línea a línea y actualización del fichero.

```json
{
  "nombre": "God of War Ragnarok",
  "plataforma": "PS5",
  "precio": 69.99,
  "disponible": true,
  "generos": ["Accion", "Aventura"]
}
```

#### Práctica 2 — API Open Trivia DB

Conexión a la API pública [opentdb.com](https://opentdb.com/) con `HttpURLConnection`. Se realiza una petición GET, se lee la respuesta como texto plano, se parsea el JSON manualmente y se construyen objetos `Pregunta`.

```
https://opentdb.com/api.php?amount=5&category=18&difficulty=easy&type=multiple
```

---

### JavaFX

Construcción de interfaces gráficas de usuario con **JavaFX**. *(En desarrollo)*

---

## Tecnologías

- Java 17 / Java 23
- JavaFX
- Maven
- IntelliJ IDEA

---

## Ejecución

Cada módulo es un proyecto Maven independiente. Para ejecutar cualquiera de ellos:

1. Abrir la carpeta del módulo en IntelliJ IDEA.
2. Dejar que Maven resuelva las dependencias.
3. Seleccionar la clase `Main` (o la clase indicada en cada módulo) y pulsar **Run**.

Para el módulo `json-apis`, la Práctica 2 requiere conexión a internet.

---

## Notas generales

- Los flujos (`BufferedReader`, `BufferedWriter`, `Scanner`, `PrintWriter`, `ObjectOutputStream`...) se cierran siempre explícitamente con `close()`.
- El manejo de excepciones sigue el patrón `try-catch` con `e.printStackTrace()` para obtener información detallada del error.
- Los archivos de recursos (`.txt`, `.ser`, `.json`) se almacenan en `src/main/resources/` de cada módulo.

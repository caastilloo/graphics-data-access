# JSON y APIs en Java

Proyecto de prácticas del módulo de **Entornos de Desarrollo** — 1º DAW.  
Trabaja con ficheros JSON y consumo de APIs REST usando **Java puro**, sin librerías externas.

---

## 🗂️ Estructura del proyecto

```
proyecto/
│
├── pom.xml
│
└── src/
    └── main/
        └── java/
            ├── practica1/
            │   ├── Videojuego.java
            │   └── MainPractica1.java
            │
            └── practica2/
                ├── Pregunta.java
                └── MainPractica2.java
```

---

## Práctica 1 — Inventario de videojuegos con JSON

### Descripción

Programa que gestiona el inventario de una tienda de videojuegos, guardando y leyendo los datos en un fichero `videojuegos.json`.

### Clases

**`Videojuego.java`** — Clase modelo con los siguientes campos:

| Campo | Tipo | Descripción |
|---|---|---|
| `nombre` | `String` | Nombre del juego |
| `plataforma` | `String` | Consola (PS5, Xbox, PC...) |
| `precio` | `double` | Precio en euros |
| `disponible` | `boolean` | Si está disponible o no |
| `generos` | `List<String>` | Lista de géneros del juego |

**`MainPractica1.java`** — Programa principal que realiza estos pasos:

- **a)** Crea 9 videojuegos (3 por consola: PS5, Xbox y PC) y los guarda en una colección.
- **b)** Serializa la colección a JSON manualmente con `StringBuilder` y la guarda en `videojuegos.json`.
- **c)** Lee el fichero `videojuegos.json` y muestra su contenido por pantalla.
- **d)** Parsea el JSON línea a línea y reconstruye los objetos `Videojuego` en memoria.
- **e)** Añade un videojuego nuevo a la colección y actualiza el fichero.

### Ejemplo de JSON generado

```json
[
  {
    "nombre": "God of War Ragnarok",
    "plataforma": "PS5",
    "precio": 69.99,
    "disponible": true,
    "generos": ["Accion", "Aventura"]
  },
  {
    "nombre": "Halo Infinite",
    "plataforma": "Xbox",
    "precio": 39.99,
    "disponible": true,
    "generos": ["Shooter", "Accion"]
  }
]
```

### Cómo funciona el JSON manual

Como no se usa ninguna librería externa, el JSON se construye y se parsea a mano:

- **Escritura:** se recorre la colección y se construye el String JSON carácter a carácter con `StringBuilder`, respetando la sintaxis de objetos `{}` y arrays `[]`.
- **Lectura:** se lee el fichero línea a línea y se identifican las claves (`"nombre"`, `"plataforma"`...) para extraer sus valores con `String.indexOf()` y `String.substring()`.

---

## Práctica 2 — Consumo de la API Open Trivia DB

### Descripción

Programa que conecta con la API pública [Open Trivia DB](https://opentdb.com/) para obtener preguntas de trivia sobre informática y mostrarlas por pantalla.

**URL usada:**
```
https://opentdb.com/api.php?amount=5&category=18&difficulty=easy&type=multiple
```

| Parámetro | Valor | Significado |
|---|---|---|
| `amount` | `5` | Número de preguntas |
| `category` | `18` | Categoría: Informática |
| `difficulty` | `easy` | Dificultad: fácil |
| `type` | `multiple` | Tipo: test con 4 opciones |

### Clases

**`Pregunta.java`** — Clase modelo con los campos:

| Campo | Tipo |
|---|---|
| `categoria` | `String` |
| `dificultad` | `String` |
| `pregunta` | `String` |
| `respuestaCorrecta` | `String` |
| `respuestasIncorrectas` | `List<String>` |

**`MainPractica2.java`** — Programa principal que realiza estos pasos:

1. Realiza una petición HTTP GET a la API con `HttpURLConnection`.
2. Lee la respuesta JSON como texto plano.
3. Muestra el JSON crudo por pantalla.
4. Parsea el JSON manualmente extrayendo los campos de cada pregunta.
5. Construye objetos `Pregunta` y los muestra formateados por pantalla.

---

## Tecnologías

- **Java 17**
- **Maven** — solo para compilación, sin dependencias externas
- **`HttpURLConnection`** — clase de Java para peticiones HTTP
- **`FileWriter` / `BufferedReader`** — clases de Java para lectura y escritura de ficheros

---

## Requisitos

- JDK 17 o superior
- IntelliJ IDEA (recomendado) o cualquier IDE con soporte Maven
- Conexión a internet (solo para la Práctica 2)

---

## Ejecución

Abrir el proyecto en IntelliJ, seleccionar la clase que se quiere ejecutar y pulsar el botón de play:

- `MainPractica1` — ejecuta la práctica del inventario de videojuegos
- `MainPractica2` — ejecuta la práctica de consumo de la API
# Ficheros en Java

Proyecto de prácticas sobre manejo de ficheros en Java, cubriendo lectura, escritura y manipulación de archivos y directorios mediante las clases principales de `java.io`.

---

## Contenido

- [Tecnologías](#tecnologías)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Clases trabajadas](#clases-trabajadas)
- [Actividades](#actividades)

---

## 🗂️ Estructura del proyecto

```
ficheros/
├── src/
│   └── main/
│       ├── java/org/example/
│       │   ├── Main.java
│       │   ├── Leer.java
│       │   └── Escribir.java
│       └── resources/
│           ├── datos.txt
│           ├── datos_personas.txt
│           ├── ejemplo1.txt
│           ├── prueba.txt
│           ├── prueba_escribir.txt
│           └── combinado.txt
```

---

## Clases trabajadas

### `File`
Permite acceder a información de archivos y directorios (ruta, tamaño, permisos...) sin leer ni escribir datos en sí.  
Métodos usados: `createNewFile()`, `mkdir()`, `mkdirs()`, `delete()`, `exists()`, `getName()`, `length()`, `getAbsolutePath()`, `listFiles()`, `renameTo()`.

### `FileReader` + `BufferedReader`
Para leer archivos de texto de forma eficiente usando un búfer interno.  
Método principal: `readLine()`, que devuelve `null` al llegar al final del fichero.

### `FileWriter` + `BufferedWriter`
Para escribir texto en archivos. `BufferedWriter` almacena los datos en un búfer y los vuelca al archivo al hacer `close()` o `flush()`.  
Métodos usados: `write()`, `newLine()`, `flush()`, `close()`.

### `PrintWriter`
Subclase de `Writer`, más versátil que `FileWriter`. Permite escribir datos formateados y usar `println()` y `printf()` igual que con `System.out`.

### `Scanner` (sobre ficheros)
Permite leer archivos línea a línea (`nextLine()`) o palabra a palabra (`next()`), así como parsear tipos de datos directamente (`nextInt()`, etc.).

---

## Actividades

### Actividad 1 — Crear archivo en subcarpeta
Modificación del programa base para que los archivos se creen dentro de `src/main/resources/` en lugar de la raíz del proyecto.

### Actividad 2 — Crear archivo y mostrar información
Se añade `createNewFile()` antes de consultar la información del fichero con `exists()`, `getName()`, `length()` y `getAbsolutePath()`. Se muestra también la información de la carpeta que lo contiene.

### Actividad 3 — Observar cambio de tamaño
Actividad de observación: se escribe contenido manualmente en `ejemplo1.txt` y se comprueba cómo varía el valor de `length()`. Cada carácter ASCII ocupa 1 byte; los caracteres con tilde o ñ ocupan 2 bytes en UTF-8.

### Actividad 4 — Generar n archivos en carpeta elegida
Método `generarArchivos(String nombre, int n, String ruta)` que crea `nombre(1).txt`, `nombre(2).txt`, ... `nombre(n).txt` en la carpeta indicada por el usuario. Si la carpeta no existe, se crea con `mkdirs()`.

### Actividad 5 — Listar archivos por extensión
Método `listarArchivos(String rutaCarpeta)` que lista únicamente los `.txt` de una carpeta.  
Sobrecarga `listarArchivos(String rutaCarpeta, String extension)` que permite filtrar por cualquier extensión (`.pdf`, `.jpg`, etc.). El filtrado se hace recorriendo el array de `listFiles()` y comprobando que el nombre termine con la extensión indicada.

### Actividad 6 — Buscar palabra en fichero con `BufferedReader`
Método `buscarPalabra(String rutaFichero, String palabra)` que lee el fichero línea a línea con `BufferedReader`, divide cada línea con `split(" ")` y cuenta cuántas veces aparece la palabra buscada. La comparación es insensible a mayúsculas (`equalsIgnoreCase()`).

### Actividad 7 — Buscar palabra en fichero con `Scanner`
Misma funcionalidad que la Actividad 6 pero usando `Scanner`. Al leer con `next()`, el propio `Scanner` separa por espacios y saltos de línea, eliminando la necesidad de dividir manualmente cada línea.

### Actividad 8 — Generar archivos con contenido
Modificación de la Actividad 4: tras crear cada archivo, se abre un `BufferedWriter` y se escribe dentro la frase `"Este es el fichero nombre(n).txt"`.

### Actividad 9 — Capitalizar cada palabra de un fichero
Método `capitalizarPalabras(String rutaArchivo)` que lee el fichero original con `BufferedReader` y escribe las modificaciones en un archivo temporal con `BufferedWriter`. Cada palabra se capitaliza con `Character.toUpperCase(palabra.charAt(0))` + `substring(1)`. Al terminar, se borra el original con `delete()` y el temporal pasa a ocupar su lugar con `renameTo()`.

### Actividad 10 — Combinar dos ficheros palabra a palabra
Método `combinarArchivos(String ruta1, String ruta2, String rutaSalida)` que lee ambos archivos palabra a palabra con dos `Scanner` y los va intercalando en un archivo de salida usando `PrintWriter`. Si uno de los archivos se queda sin palabras antes, se siguen añadiendo todas las del otro hasta agotar su contenido.

---

## Tecnologías

- Java 23
- IntelliJ IDEA
- Maven

---

## Notas

- Todos los archivos de recursos se almacenan en `src/main/resources/`.
- El manejo de excepciones sigue el patrón `try-catch` con `e.printStackTrace()` para obtener información detallada del error.
- Los flujos (`BufferedReader`, `BufferedWriter`, `Scanner`, `PrintWriter`) se cierran siempre explícitamente con `close()` al terminar su uso.
# Serialización de Objetos en Java

Ejercicios prácticos sobre serialización y deserialización de objetos en Java, realizados como parte del módulo de **Acceso a Datos** del ciclo formativo **Desarrollo de Aplicaciones Web (DAW)**.

---

## Concepto

La **serialización** en Java es el proceso de convertir el estado de un objeto en una secuencia de bytes para guardarlo en un fichero. La **deserialización** hace el proceso inverso: reconstruye el objeto original a partir de esos bytes.

Para que una clase sea serializable debe implementar la interfaz `Serializable`. Los atributos marcados como `transient` son ignorados durante la serialización y recuperan su valor por defecto (`0`, `false`, `null`, etc.) al deserializar.

---

## 🗂️ Estructura del proyecto

```
src/
├── main/
│   ├── java/
│   │   ├── Producto.java
│   │   ├── SerializarLista.java
│   │   ├── DeserializarLista.java
│   │   ├── DeserializarConManejo.java
│   │   ├── SerializarMapa.java
│   │   ├── DeserializarMapa.java
│   │   ├── Empleado.java
│   │   ├── Jefe.java
│   │   ├── SerializarJerarquia.java
│   │   └── DeserializarJerarquia.java
│   └── resources/
│       ├── clase_prueba.ser
│       ├── mapa.ser
│       └── jerarquia.ser
```

> Los archivos `.ser` se generan automáticamente al ejecutar las clases de serialización. No es necesario crearlos a mano.

---

## Ejercicios

### EJ1 — Serializar y deserializar un `ArrayList`

Se crea la clase `Producto` con los atributos `nombre` y `precio`, que implementa `Serializable`. Se serializa una lista de productos en `clase_prueba.ser` y se recupera recorriendo todos sus elementos.

**Clases:** `Producto.java`, `SerializarLista.java`, `DeserializarLista.java`

```
--- Lista de productos ---
Nombre: Teclado | Precio: 45.99€ | Descuento: 0.0%
Nombre: Ratón | Precio: 22.5€ | Descuento: 0.0%
Nombre: Monitor | Precio: 189.0€ | Descuento: 0.0%
```

---

### EJ2 — Atributo `transient`

Se añade el atributo `descuento` a `Producto` marcado como `transient`. Al deserializar, su valor es `0.0` porque al ser un tipo primitivo (`double`) no se serializa y se inicializa a su valor por defecto.

**Clase modificada:** `Producto.java`

```java
private transient double descuento; // No se serializa
```

---

### EJ3 — Manejo de excepciones

Se controlan los errores al deserializar un fichero que no existe o está dañado, capturando `FileNotFoundException` e `IOException` por separado. Si la lectura falla, se crea automáticamente el fichero con datos por defecto.

**Clase:** `DeserializarConManejo.java`

```
Error: el fichero no existe. Creando datos por defecto...
Fichero creado con datos por defecto.
--- Contenido de la lista ---
Nombre: Producto por defecto | Precio: 0.0€ | Descuento: 0.0%
```

> `FileNotFoundException` se captura antes que `IOException` porque es una subclase de ella.

---

### EJ4 — Serializar un `HashMap` y mostrarlo ordenado

Se serializa un `HashMap<String, Producto>` en `mapa.ser`. Al deserializar, se pasa a un `TreeMap` para mostrar las entradas ordenadas alfabéticamente por clave.

**Clases:** `SerializarMapa.java`, `DeserializarMapa.java`

```
--- Mapa ordenado por clave ---
P001 -> Nombre: Teclado | Precio: 45.99€ | Descuento: 0.0%
P002 -> Nombre: Ratón | Precio: 22.5€ | Descuento: 0.0%
P003 -> Nombre: Monitor | Precio: 189.0€ | Descuento: 0.0%
```

---

### EJ5 — Jerarquía de clases: `Empleado` y `Jefe`

Se crea una jerarquía donde `Empleado` implementa `Serializable` y `Jefe` la extiende añadiendo el atributo `departamento`. La subclase hereda la capacidad de serialización sin necesidad de declararla de nuevo. Se serializa una lista mixta en `jerarquia.ser` y se recupera usando polimorfismo.

**Clases:** `Empleado.java`, `Jefe.java`, `SerializarJerarquia.java`, `DeserializarJerarquia.java`

```
--- Lista de empleados ---
Empleado: Ana García | Salario: 1800.0€
Empleado: Carlos López | Salario: 3200.0€ | Departamento: Desarrollo [JEFE]
Empleado: Marta Ruiz | Salario: 1950.0€
Empleado: Pedro Sanz | Salario: 3500.0€ | Departamento: Marketing [JEFE]
```

---

## Nota de seguridad

La serialización en Java es un proceso inherentemente inseguro y puede ser utilizada con fines maliciosos. Siempre se debe deserializar únicamente datos provenientes de fuentes de confianza.

---

## 🛠️ Tecnologías

- Java 17+
- Maven
- IntelliJ IDEA / Eclipse
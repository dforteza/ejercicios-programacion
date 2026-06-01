# Tema 3 — Introducción a POO en Java

> Resumen sintetizado de `tema3-01.pdf` y `tema3-02.pdf`

---

## Índice

1. [Conceptos clave de POO](#1-conceptos-clave-de-poo)
2. [Fundamentos de una clase](#2-fundamentos-de-una-clase)
3. [Objetos: instanciación y referencias](#3-objetos-instanciación-y-referencias)
4. [Visibilidad: public / private / protected](#4-visibilidad-public--private--protected)
5. [Métodos](#5-métodos)
6. [Constructores y `this`](#6-constructores-y-this)
7. [static y final](#7-static-y-final)
8. [Arrays de objetos](#8-arrays-de-objetos)
9. [Principios de POO](#9-principios-de-poo)
10. [Sobrecarga de métodos](#10-sobrecarga-de-métodos)
11. [Paso de parámetros: valor vs referencia](#11-paso-de-parámetros-valor-vs-referencia)
12. [Ámbito de variables](#12-ámbito-de-variables)
13. [toString y @Override](#13-tostring-y-override)
14. [Paquetes e importaciones](#14-paquetes-e-importaciones)
15. [Herencia (introducción)](#15-herencia-introducción)
16. [Recursividad](#16-recursividad)
17. [Clases anidadas](#17-clases-anidadas)
18. [Varargs y args del main](#18-varargs-y-args-del-main)

---

## 1. Conceptos clave de POO

| Término | Definición clave |
|---|---|
| **Clase** | Plantilla/molde que define atributos y métodos comunes |
| **Objeto / Instancia** | Ejemplar concreto creado a partir de una clase |
| **Instanciación** | Acto de crear un objeto con `new` |
| **Encapsulamiento** | Ocultar el estado interno; solo accesible por métodos públicos |
| **Herencia** | Una clase adquiere características de otra (especialización) |
| **Polimorfismo** | Un objeto puede comportarse de formas distintas según el contexto |
| **Modularidad** | Dividir el programa en partes independientes y cohesionadas |
| **Abstracción** | Centrarse en *qué* hace un objeto, no en *cómo* lo hace |

> **Regla mental:** Clase = molde; Objeto = pieza fabricada con ese molde.

---

## 2. Fundamentos de una clase

Una clase agrupa **atributos** (datos) y **métodos** (comportamiento). Cada clase va en su propio archivo `.java` con el mismo nombre.

```java
// Archivo: Persona.java
public class Persona {

    // --- ATRIBUTOS (variables de instancia) ---
    String nombre;
    int edad;

    // --- MÉTODOS ---
    void setNombre(String n) { nombre = n; }
    void setEdad(int e)      { edad = e; }

    String getNombre()  { return nombre; }
    int    getEdad()    { return edad; }

    void imprimeNombre() {
        System.out.println(nombre);
    }

    boolean esMayorEdad() {
        return edad >= 18;
    }
}
```

```java
// Archivo: Programa.java  — punto de entrada
public class Programa {
    public static void main(String[] args) {
        Persona p = new Persona();
        p.setNombre("Ana");
        p.setEdad(20);
        System.out.println(p.getNombre() + " mayor de edad: " + p.esMayorEdad());
        // → Ana mayor de edad: true
    }
}
```

**Palabras clave:** `atributo`, `método`, `variable de instancia`, `miembro de clase`.

---

## 3. Objetos: instanciación y referencias

### Operador `new`

```java
Persona p1;            // 1) declara referencia (no existe objeto todavía)
p1 = new Persona();    // 2) crea objeto en heap y asigna referencia

// Forma compacta:
Persona p2 = new Persona();
```

### Variables de referencia — ¡NO son copias!

```java
Persona p1 = new Persona();
p1.setNombre("Ana");

Persona p2 = p1;       // p2 apunta al MISMO objeto que p1
p2.setNombre("Luis");

System.out.println(p1.getNombre()); // → Luis  (mismo objeto)
```

> **Clave:** asignar una variable de referencia copia la dirección de memoria, no el objeto.

### Garbage Collector

- Java libera automáticamente objetos que ya no tienen ninguna referencia apuntándoles.
- El proceso es automático, asíncrono y de baja prioridad — no es predecible cuándo ocurre.

---

## 4. Visibilidad: public / private / protected

| Modificador | Accesible desde |
|---|---|
| `public` | Cualquier clase |
| `private` | Solo la propia clase |
| `protected` | La clase y sus subclases (herencia) |
| *(sin modificador)* | Clases del mismo paquete |

**Regla de oro:** atributos → `private`; métodos de acceso → `public`.

```java
public class Coche {
    private String color;       // nadie accede directamente
    private int velocidad;

    public void setColor(String c) { color = c; }   // getter/setter = interfaz pública
    public String getColor()       { return color; }
}
```

> **Interfaz pública** = conjunto de miembros `public` con los que el exterior interactúa.

---

## 5. Métodos

```
[acceso] [static] tipoRetorno nombreMetodo(tipo param1, tipo param2, ...) {
    // cuerpo
    return valor; // si no es void
}
```

- `void` → no devuelve nada.
- `return` → devuelve el valor y sale del método.
- Los **getters** devuelven atributos; los **setters** los modifican.

```java
// Getter
public String getNombre() { return nombre; }

// Setter
public void setNombre(String n) { nombre = n; }

// Método lógico
public double precioConDescuento(double descuento) {
    return precio * (1 - descuento);
}
```

---

## 6. Constructores y `this`

Un **constructor** es un método especial que:
- Tiene el mismo nombre que la clase.
- No tiene tipo de retorno (ni `void`).
- Se llama automáticamente al usar `new`.

```java
public class Persona {
    private String nombre;
    private int edad;

    // Constructor por defecto (sin parámetros)
    public Persona() {
        nombre = "Sin nombre";
        edad = 0;
    }

    // Constructor con parámetros
    public Persona(String nombre, int edad) {
        this.nombre = nombre;   // this distingue atributo del parámetro
        this.edad   = edad;
    }
}
```

### `this` — dos usos

```java
// 1) Referenciar el atributo cuando hay conflicto de nombre
public void setEdad(int edad) {
    this.edad = edad;  // this.edad = atributo; edad = parámetro
}

// 2) Llamar a otro constructor de la MISMA clase (debe ser primera línea)
public Persona(String nombre) {
    this(nombre, 0);   // llama al constructor de 2 parámetros
}
```

> Si defines un constructor con parámetros, Java **deja de generar** el constructor por defecto automáticamente.

---

## 7. static y final

| Combinación | Significado |
|---|---|
| `static` | Pertenece a la clase, compartido por todos los objetos |
| `final` | Constante de objeto — valor fijo tras la construcción |
| `static final` | Constante de clase (como `Math.PI`) |
| *(ninguno)* | Atributo de instancia normal |

```java
public class Empleado {
    private String nombre;          // atributo de instancia — cada objeto tiene el suyo

    private static int contador = 0;          // static: compartido por TODOS
    private static final int MAX = 100;       // static final: constante de clase
    private final int id;                     // final: constante de objeto (única por instancia)

    public Empleado(String nombre) {
        this.nombre = nombre;
        contador++;
        this.id = contador;        // se fija en construcción y no cambia
    }

    public static int getContador() { return contador; }  // método static accede solo a static
}
```

```java
// Uso desde otra clase:
Empleado e1 = new Empleado("Ana");
Empleado e2 = new Empleado("Luis");
System.out.println(Empleado.getContador()); // → 2   (se llama con nombre de clase)
```

> **Peligro:** los atributos `static` son globales a la clase — usar con moderación para no romper la encapsulación.

---

## 8. Arrays de objetos

```java
// 1) Crear el array (crea referencias, NO los objetos)
Persona[] grupo = new Persona[3];

// 2) Instanciar cada objeto individualmente
grupo[0] = new Persona("Ana",  20);
grupo[1] = new Persona("Luis", 25);
grupo[2] = new Persona("Mia",  22);

// 3) Recorrer
for (Persona p : grupo) {
    System.out.println(p.getNombre());
}
```

> **Truco mental:** `new Persona[3]` crea 3 cajas vacías; `new Persona(...)` llena cada caja.

---

## 9. Principios de POO

```
Abstracción    → qué hace la clase (su interfaz pública)
Encapsulamiento → cómo oculta su estado interno (private + getters/setters)
Modularidad    → clases independientes y cohesionadas
Jerarquía      → herencia (es-un) y composición (parte-de)
```

- **Cohesión:** los componentes de una clase deben estar conceptualmente relacionados.
  - MAL: método `recargaVida()` dentro de la clase `Arma`.
  - BIEN: `recargaVida()` dentro de `Jugador`.
- **Abstracción + Encapsulamiento se complementan:** encapsular oculta los detalles; abstraer expone solo lo esencial.

---

## 10. Sobrecarga de métodos

Varios métodos con el **mismo nombre** pero distinto número o tipo de parámetros. El compilador selecciona la versión correcta según los argumentos.

```java
public class Calculadora {

    public int suma(int a, int b)             { return a + b; }
    public double suma(double a, double b)    { return a + b; }
    public int suma(int a, int b, int c)      { return a + b + c; }

    // INVÁLIDO — solo cambia el nombre del parámetro, no el tipo:
    // public int suma(int x, int y) { return x + y; }  // ERROR: idéntico al primero
}
```

```java
// Sobrecarga de constructores — muy habitual
public class Vehiculo {
    private int ruedas;
    private String color;
    private boolean gps;

    public Vehiculo() {
        this(4, "sin color");   // llama al constructor de abajo
    }

    public Vehiculo(int ruedas, String color) {
        this.ruedas = ruedas;
        this.color  = color;
        this.gps    = false;
    }

    public Vehiculo(int ruedas, String color, boolean gps) {
        this(ruedas, color);    // reutiliza constructor anterior
        this.gps = gps;
    }
}
```

> **Regla:** solo se diferencia por **número o tipo** de parámetros — el tipo de retorno solo no basta.

---

## 11. Paso de parámetros: valor vs referencia

**En Java SIEMPRE se pasa por valor.** La diferencia está en qué es ese "valor":

| Tipo | Lo que se copia | Efecto dentro del método |
|---|---|---|
| Primitivo (`int`, `double`…) | El dato en sí | Cambios **no** afectan al original |
| Objeto | La **referencia** (dirección) | Cambios en atributos **sí** afectan al original; reasignar la variable no |

```java
// --- PRIMITIVO ---
void duplicar(int n) { n = n * 2; }  // modifica la copia local

int x = 5;
duplicar(x);
System.out.println(x); // → 5  (sin cambio)

// --- OBJETO: modificar atributos SÍ se refleja ---
void cambiarNombre(Persona p, String nombre) {
    p.setNombre(nombre);  // modifica el objeto real en heap
}

Persona juan = new Persona("Juan", 30);
cambiarNombre(juan, "Pedro");
System.out.println(juan.getNombre()); // → Pedro

// --- OBJETO: reasignar referencia NO se refleja ---
void reasignar(Persona p) {
    p = new Persona("Nuevo", 0);  // solo cambia la copia local de la referencia
}
reasignar(juan);
System.out.println(juan.getNombre()); // → Pedro  (sin cambio)
```

**Resumen de reglas:**
1. Modificar **primitivo** → no afecta original.
2. Modificar **atributos del objeto** → sí afecta original.
3. Reasignar la **referencia del objeto** → no afecta original.

> **Stack vs Heap:** variables primitivas y referencias → `stack`; objetos reales → `heap`.

---

## 12. Ámbito de variables

| Tipo | Dónde se declara | Ciclo de vida | Inicialización |
|---|---|---|---|
| **Atributo de clase** | Nivel de clase | Mientras existe el objeto | Automática (0, null, false) |
| **Variable local** | Dentro de un método o bloque | Hasta que acaba el bloque | Obligatoria (manual) |

```java
public class Ejemplo {
    int atributo = 10;           // atributo: disponible en todos los métodos

    void metodo() {
        int local = 5;           // local: solo existe aquí
        if (true) {
            int bloqueVar = 3;   // solo existe dentro del if
        }
        // bloqueVar ya no existe aquí
    }
}
```

---

## 13. toString y @Override

`toString()` convierte un objeto a `String`. Viene de la clase `Object` (raíz de toda clase Java) y se **sobreescribe** para mostrar información útil.

```java
public class Fecha {
    private int dia, mes, anno;

    public Fecha(int dia, int mes, int anno) {
        this.dia  = dia;
        this.mes  = mes;
        this.anno = anno;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anno;
    }
}

// Uso:
Fecha f = new Fecha(21, 5, 2026);
System.out.println(f);           // → 21/5/2026  (llama a toString automáticamente)
System.out.println("Hoy: " + f); // → Hoy: 21/5/2026
```

> `@Override` le indica al compilador que estamos sobreescribiendo un método heredado — el compilador verificará que la firma sea correcta.

---

## 14. Paquetes e importaciones

Un **paquete** (`package`) es un directorio que agrupa clases relacionadas. Equivale a carpetas en el sistema de ficheros.

```java
// Al inicio de cada archivo — indica a qué paquete pertenece
package com.miapp.modelos;

public class Persona { ... }
```

```java
// En otra clase — importar lo que necesites
import com.miapp.modelos.Persona;       // clase concreta
import com.miapp.modelos.*;             // todas las clases del paquete
import java.util.ArrayList;             // clase de la biblioteca estándar

// Java importa automáticamente: java.lang  (String, System, Math...)
```

**Jerarquía de paquetes:**
```
src/
 └── com/
      └── miapp/
           ├── modelos/        ← package com.miapp.modelos
           │    └── Persona.java
           └── utils/          ← package com.miapp.utils
                └── Validador.java
```

**Visibilidad entre paquetes:**
- Clases del **mismo paquete** → acceso entre sí sin `public`.
- Clases de **paquetes distintos** → necesitan `public` explícito.

---

## 15. Herencia (introducción)

Una clase **hija** hereda todos los atributos y métodos de la clase **padre** y puede añadir o modificar comportamiento.

```java
// Clase padre (superclase)
public class Persona {
    protected String nombre;
    protected String direccion;

    public Persona(String nombre, String direccion) {
        this.nombre    = nombre;
        this.direccion = direccion;
    }
}

// Clase hija (subclase) — extends
public class Empleado extends Persona {
    private double salario;

    public Empleado(String nombre, String direccion, double salario) {
        super(nombre, direccion);   // llama al constructor del padre
        this.salario = salario;
    }

    public double getSalario() { return salario; }
}

// Otra subclase
public class Cliente extends Persona {
    private String cuentaBancaria;
    // tiene nombre y direccion de Persona, más lo propio
}
```

> Toda clase hereda de `Object` (implícitamente), por eso todas tienen `toString()`, `equals()`, etc.

**Jerarquía:** `is-a` (es-un) → `Empleado` **es** una `Persona`. Composición: `has-a` (tiene-un) → Un `Coche` **tiene** ruedas.

---

## 16. Recursividad

Un método que **se llama a sí mismo**. Requiere siempre:
- **Caso base:** condición de parada (sin llamada recursiva).
- **Llamada recursiva:** el problema se reduce en cada llamada.

```java
// Factorial recursivo
public class Matematica {
    static int factorial(int n) {
        if (n == 0) return 1;          // caso base
        return n * factorial(n - 1);   // llamada recursiva
    }
}
// factorial(4) = 4 * factorial(3) = 4 * 3 * 2 * 1 * 1 = 24
```

```java
// Fibonacci recursivo
static int fib(int n) {
    if (n <= 1) return n;              // caso base
    return fib(n - 1) + fib(n - 2);   // dos llamadas recursivas
}
```

**Cuándo usar recursión:**
- Cuando el problema es naturalmente recursivo (árboles, fractales, factorial).
- Cuando la solución iterativa es compleja y la recursiva más legible.
- **Evitar** cuando la iterativa es sencilla: la recursión tiene mayor coste de memoria/CPU.

---

## 17. Clases anidadas

Una clase definida **dentro de otra clase**. Debe ser `static` para ser miembro de la contenedora.

```java
public class Contenedora {
    private int valor = 10;

    static class Anidada {
        void mostrar() {
            System.out.println("Soy la clase anidada");
        }
    }
}

// Uso:
Contenedora.Anidada obj = new Contenedora.Anidada();
obj.mostrar();
```

> Útil para encapsular lógica auxiliar que solo tiene sentido dentro de la clase contenedora.

---

## 18. Varargs y args del main

### Varargs — número variable de argumentos

```java
// tipo... nombre  →  internamente es un array
static void imprimir(String... textos) {
    for (String t : textos) {
        System.out.println(t);
    }
}

imprimir("Hola");
imprimir("A", "B", "C");     // mismo método, diferente cantidad
```

> Si hay más parámetros, `varargs` siempre va **al final**: `void metodo(int x, String... extras)`.

### Argumentos del `main`

```java
public static void main(String[] args) {
    // args contiene los valores pasados al ejecutar desde consola:
    // java MiPrograma Hola Mundo
    // args[0] = "Hola"
    // args[1] = "Mundo"

    for (String arg : args) {
        System.out.println(arg);
    }
}
```

---

## Mapa mental rápido

```
POO
├── Clase ──────────── molde (atributos + métodos)
│    ├── Visibilidad ── public / private / protected
│    ├── static ──────── pertenece a la clase
│    └── final ───────── constante
│
├── Objeto ─────────── instancia creada con new
│    ├── Referencia ──── apunta a heap (no es copia)
│    └── GC ──────────── Java libera memoria sola
│
├── Constructores ───── inicialización automática
│    └── this ─────────── referencia al objeto actual / otro constructor
│
├── Métodos
│    ├── Sobrecarga ───── mismo nombre, distintos parámetros
│    ├── toString ──────── representación String del objeto
│    └── Paso de params ── siempre por valor (ref. incluida)
│
├── Paquetes ─────────── organización de clases (package + import)
│
├── Herencia ─────────── extends (is-a)
│
└── Recursividad ─────── caso base + llamada a sí mismo
```

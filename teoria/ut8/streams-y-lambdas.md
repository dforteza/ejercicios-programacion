# Streams y Lambdas en Java

## Índice
1. [Imperativo vs Declarativo](#1-imperativo-vs-declarativo)
2. [Programación funcional](#2-programación-funcional)
3. [Funciones lambda](#3-funciones-lambda)
4. [Interfaces funcionales](#4-interfaces-funcionales)
5. [Streams](#5-streams)
6. [Operaciones intermedias](#6-operaciones-intermedias)
7. [Operaciones terminales](#7-operaciones-terminales)
8. [Lazy evaluation](#8-lazy-evaluation)
9. [Operador :: — referencia a métodos](#9-operador----referencia-a-métodos)
10. [Ejemplo completo](#10-ejemplo-completo)

---

## 1. Imperativo vs Declarativo

| Enfoque | Responde a | Cómo es |
|---|---|---|
| **Imperativo** | *¿Cómo* se hace? | Instrucciones paso a paso con bucles y condiciones |
| **Declarativo** | *¿Qué* quiero obtener? | Describe el resultado, no los pasos |

```java
List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

// Imperativo — describes cada paso
List<Integer> dobles = new ArrayList<>();

for (int n : numeros)
    dobles.add(n * 2);

// Declarativo (Streams) — describes qué quieres
List<Integer> dobles = numeros.stream()
    .map(n -> n * 2)
    .collect(Collectors.toList());
```

---

## 2. Programación funcional

Es un **subparadigma declarativo** basado en tres ideas:

- **Funciones** — transforman una entrada en una salida.
- **Funciones de orden superior** — aceptan otras funciones como argumento.
- **Composición** — encadenas funciones para construir operaciones complejas.

Las tres operaciones fundamentales son:

| Operación | Qué hace |
|---|---|
| **Map** | Transforma cada elemento en otro |
| **Filter** | Selecciona elementos que cumplen una condición |
| **Reduce** | Combina todos los elementos en un único resultado |

---

## 3. Funciones lambda

Una **lambda** es una función anónima — no tiene nombre, se define en el momento en que se usa.  
Se pasa como argumento a otro método que la ejecuta internamente.

### Sintaxis

```
(parámetros) -> cuerpo
```

```java
// Sin parámetros
() -> "Hola"

// Un parámetro (paréntesis opcionales)
nombre -> nombre.toUpperCase()

// Varios parámetros
(a, b) -> a + b

// Cuerpo con varias líneas — necesita llaves y return
(a, b) ->
{
    int resultado = a + b;

    return resultado;
}
```

### Cuándo se usa

Siempre que un método espera una **interfaz funcional** como parámetro.  
`sort`, `filter`, `map`, `forEach`... todos esperan una lambda (o referencia a método).

```java
lista.sort((a, b) -> a.getNombre().compareTo(b.getNombre()));
lista.forEach(e -> System.out.println(e));
lista.stream().filter(e -> e.getSueldo() > 1500);
```

---

## 4. Interfaces funcionales

Una **interfaz funcional** es una **interfaz** con **un único método abstracto**.  
La lambda implementa ese método de forma concisa.

```java
@FunctionalInterface
public interface Saludo
{
    String saludar(String nombre);   // único método abstracto
}

// Implementación con lambda
Saludo s = nombre -> "Hola " + nombre;
System.out.println(s.saludar("Ana"));   // Hola Ana
```

### Interfaces funcionales predefinidas en java.util.function

| Interfaz | Método | Qué hace | Uso en streams |
|---|---|---|---|
| `Consumer<T>` | `void accept(T t)` | Consume un elemento sin devolver nada | `forEach` |
| `Predicate<T>` | `boolean test(T t)` | Evalúa una condición | `filter` |
| `Function<T,R>` | `R apply(T t)` | Transforma T en R | `map` |
| `Supplier<T>` | `T get()` | Produce un valor sin recibir nada | generadores |
| `BiFunction<T,U,R>` | `R apply(T t, U u)` | Transforma dos entradas en una salida | `map` con dos args |

```java
Predicate<String> largos   = nombre -> nombre.length() > 4;
Function<String, Integer> longitud = nombre -> nombre.length();
Supplier<String> prefijo   = () -> "Sr/Sra: ";
Consumer<String> imprimir  = nombre -> System.out.println(nombre);
```

---

## 5. Streams

Un **Stream** es una **SECUENCIA DE ELEMENTOS SOBRE LA QUE PUEDES APLICAR OPERACIONES FUNCIONALES ENCADENADAS**.

**Propiedades clave:**
- Se crean a partir de colecciones o arrays — **no son** colecciones en sí.
- Son **inmutables** — no modifican la colección original.
- **Cada operación devuelve un nuevo stream** — se encadenan en un **pipeline**.
- **No son reutilizables** — una vez consumido, no puedes volver a usarlo.
- Las operaciones intermedias son **lazy** — no se ejecutan hasta que hay una operación terminal.

```java
// Crear un stream desde una colección
lista.stream()

// Crear un stream desde un array
Arrays.stream(array)

// Stream directo de valores
Stream.of("Ana", "Ben", "Zara")
```

### Estructura del pipeline

```
colección.stream()
    .operaciónIntermedia1(...)   // devuelve Stream
    .operaciónIntermedia2(...)   // devuelve Stream
    .operaciónTerminal(...)      // devuelve resultado final
```

---

## 6. Operaciones intermedias

DEVUELVEN UN NUEVO STREA — se pueden encadenar.  
**No hacen nada hasta que hay una operación terminal.**
```java
// Filtrar — conserva los que cumplen la condición
.filter(e -> e.getSueldo() > 1500)

// Transformar — convierte cada elemento en otro
.map(e -> e.getNombre())
.map(String::toUpperCase)

// Ordenar — orden natural
.sorted()

// Ordenar — orden externo
.sorted((a, b) -> a.getNombre().compareTo(b.getNombre()))
.sorted(Comparator.comparing(Empleado::getNombre))

// Eliminar duplicados
.distinct()

// Saltar los primeros n elementos
.skip(3)

// Limitar a los primeros n elementos
.limit(5)
```

---

## 7. Operaciones terminales

COSUMEN EL STREAM y devuelven un resultado final.  
Son las que **activan** el pipeline.

```java
// Imprimir cada elemento
.forEach(System.out::println)
.forEach(e -> System.out.println(e))

// Recoger en una colección
.collect(Collectors.toList())
.collect(Collectors.toSet())

// Contar elementos
long total = stream.count();

// Reducir a un valor (suma, concatenación...)
Optional<Integer> suma = stream.reduce(Integer::sum);
int suma = stream.reduce(0, Integer::sum);   // con valor inicial

// Verificar condiciones
boolean alguno  = stream.anyMatch(e -> e.getSueldo() > 2000);
boolean todos   = stream.allMatch(e -> e.getSueldo() > 1000);
boolean ninguno = stream.noneMatch(e -> e.getSueldo() < 0);

// Obtener el primero que cumpla
Optional<Empleado> primero = stream.filter(...).findFirst();
```

> `Optional<T>` es un contenedor que puede tener o no un valor — evita `NullPointerException`. Usa `optional.get()` para obtener el valor o `optional.isPresent()` para comprobar si existe.

---

## 8. Lazy evaluation

Las operaciones intermedias **no se ejecutan** hasta que aparece una operación terminal.

```java
// Esto NO hace nada — no hay operación terminal
lista.stream()
    .filter(n -> n > 4)
    .map(String::toUpperCase);

// Esto SÍ ejecuta todo — forEach activa el pipeline
lista.stream()
    .filter(n -> n > 4)
    .map(String::toUpperCase)
    .forEach(System.out::println);
```

La ventaja es eficiencia: si por ejemplo haces `.findFirst()`, Java para de procesar en cuanto encuentra el primero, sin recorrer el resto.

---

## 9. Operador :: — referencia a métodos

Cuando una lambda solo llama a un método existente, puedes sustituirla por `::`.  
Son equivalentes — `::` es la forma más compacta.

### 4 formas de referencia

**1. Método estático:**
```java
// Lambda
lista.forEach(e -> MiClase.imprimir(e));

// Referencia
lista.forEach(MiClase::imprimir);
```

**2. Método de instancia sobre un objeto concreto:**
```java
MiClase obj = new MiClase();

// Lambda
lista.forEach(e -> obj.imprimir(e));

// Referencia
lista.forEach(obj::imprimir);
```

**3. Método de instancia sobre un objeto arbitrario de la lista:**
```java
// Lambda
lista.sort((a, b) -> a.compareToIgnoreCase(b));

// Referencia — Java entiende que el primer argumento es el objeto receptor
lista.sort(String::compareToIgnoreCase);
```

**4. Constructor:**
```java
// Lambda
Supplier<MiClase> s = () -> new MiClase();

// Referencia
Supplier<MiClase> s = MiClase::new;
MiClase obj = s.get();
```

---

## 10. Ejemplo completo

Filtrar nombres que empiecen por "L" o "A", con más de 4 letras, en mayúsculas y ordenados:

```java
List<String> nombres = Arrays.asList(
    "Ana", "Luis", "Carlos", "Lucía", "Arturo", "Laura", "Andrés", "Leonardo"
);

List<String> resultado = nombres.stream()
    .filter(n -> n.startsWith("L") || n.startsWith("A"))
    .filter(n -> n.length() > 4)
    .map(String::toUpperCase)
    .sorted()
    .collect(Collectors.toList());

resultado.forEach(System.out::println);
// ANDRÉS, ARTURO, LAURA, LEONARDO, LUCÍA
```

Con `Supplier` para añadir prefijo dinámico:

```java
Supplier<String> prefijo = () -> "Sr/Sra: ";

nombres.stream()
    .filter(n -> n.length() > 4)
    .map(n -> n.substring(0, 1).toUpperCase() + n.substring(1))
    .map(n -> prefijo.get() + n)
    .sorted()
    .forEach(System.out::println);
```

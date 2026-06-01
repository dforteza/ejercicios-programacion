# Comparar y ordenar en Java

---

## Las dos interfaces: Comparable vs Comparator

### ¿De dónde sale `compareTo`?

De la interfaz `Comparable`. Cualquier clase que la implemente tiene el método `compareTo`.
`String`, `Integer`, `Double`, `LocalTime`, `LocalDate`... todos la implementan.

### Comparable — la clase se ordena a sí misma

```java
public class Persona implements Comparable<Persona>
{
    @Override
    public int compareTo(Persona otra)
    {
        return Integer.compare(this.edad, otra.edad);
    }
}
```

- Se implementa **dentro de la clase**.
- Define el **orden natural** — solo puede haber uno.
- 1 parámetro: el otro objeto con quien me comparo.

### Comparator — una clase externa ordena dos objetos

```java
public class ComparadorNombre implements Comparator<Persona>
{
    @Override
    public int compare(Persona a, Persona b)
    {
        return a.getNombre().compareTo(b.getNombre());
    }
}
```

- Se implementa **fuera de la clase**.
- Puedes tener tantos criterios como quieras.
- 2 parámetros: los dos objetos que comparo desde fuera.

### No se relacionan entre sí

Son dos interfaces independientes con distinto propósito. `Comparable` no envuelve a `Comparator` ni al revés.

---

## Lo que devuelve compareTo / compare

Siempre un `int`:

| Resultado | Significa |
|-----------|-----------|
| `< 0` | el primero va antes |
| `0` | son iguales |
| `> 0` | el primero va después |

---

## Cómo comparar cada tipo de dato

### Primitivos (`int`, `double`) — método estático

Los primitivos no son objetos, no tienen métodos. Se usa el wrapper:

```java
Integer.compare(a, b)
Double.compare(a, b)
```

### Objetos con Comparable (`String`, `Integer`, `LocalTime`, `LocalDate`...) — compareTo

Son objetos que implementan `Comparable`, así que tienen `compareTo`:

```java
a.compareTo(b)                          // String, LocalTime, LocalDate...
```

**Regla:**
```
primitivo (int, double)  →  Integer.compare / Double.compare
objeto con Comparable    →  a.compareTo(b)
```

---

## Cuándo usar cada interfaz

| Situación | Qué usar |
|-----------|----------|
| La clase tiene un orden lógico obvio | `Comparable` |
| Quieres ordenar por distintos criterios | `Comparator` |
| No puedes modificar la clase | `Comparator` |
| Orden por varios campos encadenados | `Comparator.comparing().thenComparing()` |

---

## Llamadas para ordenar una lista

`Collections.sort` tiene dos versiones — no es que una envuelva a la otra, son métodos distintos:

```java
Collections.sort(lista)                  // exige Comparable en la clase
Collections.sort(lista, comparador)      // recibe un Comparator externo
```

Equivalentes con `lista.sort()`:

```java
lista.sort(null)                         // orden natural (Comparable)
lista.sort(new ComparadorNombre())       // Comparator como clase externa
lista.sort(Comparator.comparing(...))    // Comparator en línea
```

---

## Patrones de uso con Comparator

**Orden por un campo numérico:**
```java
lista.sort(Comparator.comparing(Elemento::getPrecio));
```

**Orden por un campo numérico manual:**
```java
lista.sort((a, b) -> Double.compare(a.getPrecio(), b.getPrecio()));
```

**Orden por un campo String:**
```java
lista.sort(Comparator.comparing(Elemento::getNombre));
```

**Orden por un campo String manual:**
```java
lista.sort((a, b) -> a.getNombre().compareTo(b.getNombre()));
```

**Orden inverso:**
```java
lista.sort(Comparator.comparing(Elemento::getPrecio).reversed());
```

**Orden por varios campos encadenados:**
```java
lista.sort(Comparator.comparing(Elemento::getNombre)
                     .thenComparing(Elemento::getPrecio));
```

**`Comparator.comparing` — ¿qué hace exactamente?**

No es magia — extrae un campo y usa el `compareTo` de ese campo:

```java
Comparator.comparing(Empleado::getNombre)
// equivale a: (a, b) -> a.getNombre().compareTo(b.getNombre())
```

---

## Regla de oro

```
Campo primitivo          →  Integer.compare / Double.compare
Campo objeto (String...) →  a.compareTo(b)
Caso normal              →  Comparator.comparing(Clase::getCampo)
Caso complejo            →  lambda (a, b) -> ...
Orden inverso            →  .reversed()
Varios criterios         →  .thenComparing(...)
```

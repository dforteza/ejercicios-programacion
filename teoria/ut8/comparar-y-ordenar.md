# Comparar y ordenar en Java

---

## Las ``2 INTERFACES:`` Comparable vs Comparator

¿De dónde sale `compareTo`?

> De la interfaz `Comparable`. Cualquier clase que la implemente tiene el método `compareTo`.
`String`, `Integer`, `Double`, `LocalTime`, `LocalDate`... todos la implementan.

### Comparable — LA CLASE SE ORDENA A SÍ MISMA

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

### Comparator — UNA CLASE EXTERNA ordena dos objetos

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

---

> Son dos interfaces independientes

---

## Lo que devuelve compareTo / compare

Siempre un `int`:

| Resultado | Significa |
|-----------|-----------|
| `< 0` | el primero va antes |
| `0` | son iguales |
| `> 0` | el primero va después |

---

## Cómo comparar según el tipo de campo

La expresión de comparación cambia según el tipo del campo. La regla es siempre la misma:

| Tipo del campo | Expresión |
|---|---|
| `int`, `double` (primitivo) | `Integer.compare(a, b)` / `Double.compare(a, b)` |
| `String`, `LocalDate`... (objeto con Comparable) | `a.compareTo(b)` |

---

## Cómo ordenar según la interfaz que uses

### Con Comparable — la clase se ordena a sí misma

La lógica va dentro del `compareTo` de la propia clase. Al llamar a `Collections.sort` no hace falta pasarle nada más.

```java
// Campo int
public int compareTo(Persona otra) {
    return Integer.compare(this.edad, otra.edad);
}

// Campo String
public int compareTo(Persona otra) {
    return this.nombre.compareTo(otra.nombre);
}
```

```java
// Llamada — sin comparador externo
Collections.sort(lista);
```

---

### Con Comparator — una clase o lambda externa ordena

La lógica va fuera de la clase. El método se llama `compare` y recibe dos parámetros.

**Como clase externa:**
```java
// Campo int
public class ComparadorEdad implements Comparator<Persona> {
    public int compare(Persona a, Persona b) {
        return Integer.compare(a.getEdad(), b.getEdad());
    }
}

// Campo String
public class ComparadorNombre implements Comparator<Persona> {
    public int compare(Persona a, Persona b) {
        return a.getNombre().compareTo(b.getNombre());
    }
}
```

```java
// Llamada
Collections.sort(lista, new ComparadorEdad());
Collections.sort(lista, new ComparadorNombre());
```

**Como lambda (sin crear clase):** - implementa COMPARATOR
```java
// Campo int
Collections.sort(lista, (a, b) -> Integer.compare(a.getEdad(), b.getEdad()));

// Campo String
Collections.sort(lista, (a, b) -> a.getNombre().compareTo(b.getNombre()));
```

**Con Comparator.comparing (la forma más corta):** - implementa COMPARATOR
```java
// Campo int o double
Collections.sort(lista, Comparator.comparing(Persona::getEdad));

// Campo String
Collections.sort(lista, Comparator.comparing(Persona::getNombre));

// Orden inverso
Collections.sort(lista, Comparator.comparing(Persona::getEdad).reversed());

// Varios criterios encadenados
Collections.sort(lista, Comparator.comparing(Persona::getNombre)
                                  .thenComparing(Persona::getEdad));
```

> `Comparator.comparing(Persona::getNombre)` es exactamente igual que la lambda `(a, b) -> a.getNombre().compareTo(b.getNombre())`. Solo es más corto.

---

## Cuándo usar cada interfaz

| Situación | Qué usar |
|---|---|
| La clase tiene un orden lógico obvio | `Comparable` |
| Quieres ordenar por distintos criterios | `Comparator` |
| No puedes modificar la clase | `Comparator` |
| Orden por varios campos encadenados | `Comparator.comparing().thenComparing()` |

# Métodos especiales de colecciones

## Métodos comunes a todas las colecciones

| Método | Qué hace |
|---|---|
| `add(e)` / `put(k,v)` | Añade elemento o par clave-valor |
| `remove(e ó index)` | Elimina el elemento |
| `contains(e)` / `containsKey(k)` | Comprueba si existe |
| `size()` | Número de elementos |
| `isEmpty()` | true si está vacía |
| `clear()` | Vacía la colección |

---

## TreeMap — métodos exclusivos

Solo disponibles en `TreeMap` porque necesita las claves ordenadas.

| Método | Qué hace | Ejemplo |
|---|---|---|
| `floorEntry(k)` | Entrada con clave igual o inmediatamente menor | buscar el tramo horario activo |
| `ceilingEntry(k)` | Entrada con clave igual o inmediatamente mayor | — |
| `firstKey()` | Clave más pequeña | — |
| `lastKey()` | Clave más grande | — |
| `headMap(k)` | Sub-mapa con claves menores que `k` | — |
| `tailMap(k)` | Sub-mapa con claves iguales o mayores que `k` | — |

```java
TreeMap<String, Integer> map = new TreeMap<>();
map.put("Ana", 25);
map.put("Carlos", 30);
map.put("Marta", 22);

map.firstKey();               // → "Ana"
map.lastKey();                // → "Marta"
map.floorEntry("Beatriz");    // → entrada de "Ana" (la más alta por debajo de "Beatriz")
map.ceilingEntry("Beatriz");  // → entrada de "Carlos" (la más baja por encima de "Beatriz")
```

### TreeSet — misma idea, sin valor

`TreeSet` tiene los equivalentes sin `Entry`:

```java
TreeSet<Integer> set = new TreeSet<>(List.of(1, 3, 5, 7));

set.floor(4);    // → 3   (igual o menor)
set.ceiling(4);  // → 5   (igual o mayor)
set.first();     // → 1
set.last();      // → 7
```

---

## 3 formas de recorrer un Map

```java
// 1 — entrySet: clave y valor a la vez (la más usada)
for (Map.Entry<String, Integer> entry : map.entrySet())
{
    entry.getKey();    // clave
    entry.getValue();  // valor
}

// 2 — keySet: solo claves
for (String clave : map.keySet())
{
    map.get(clave);  // accedes al valor por la clave
}

// 3 — forEach con lambda (más compacto)
map.forEach((clave, valor) -> System.out.println(clave + " → " + valor));
```

---

## HashMap — métodos extra útiles

`HashMap` no tiene métodos de navegación como `TreeMap`, pero tiene dos que ahorran código:

| Método | Qué hace |
|---|---|
| `getOrDefault(k, defecto)` | Devuelve el valor o `defecto` si la clave no existe |
| `putIfAbsent(k, v)` | Inserta solo si la clave no existe todavía |
| `containsValue(v)` | Comprueba si el valor existe en alguna entrada |

```java
HashMap<String, Integer> map = new HashMap<>();
map.put("tomate", 3);

map.getOrDefault("avena", 0);    // → 0  (avena no existe, devuelve el defecto)
map.putIfAbsent("tomate", 99);   // no hace nada, tomate ya existe
map.putIfAbsent("avena", 2);     // inserta avena=2
```

> Para `HashSet` no hay métodos especiales más allá de los comunes. Su valor está en que elimina duplicados automáticamente y `contains` es O(1).

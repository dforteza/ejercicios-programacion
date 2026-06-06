# Ordenar Set y Map

`Collections.sort()` solo acepta `List` porque necesita acceder a elementos por índice para intercambiarlos. `Set` y `Map` no tienen índices, así que no se pueden ordenar directamente.

La solución: volcar a una `List` primero.

---

## Set

```java
List<Integer> lista = new ArrayList<>(set);
Collections.sort(lista);
```

---

## Map

Tienes 3 opciones según qué quieras ordenar:

```java
// Claves
List<String> claves = new ArrayList<>(map.keySet());
Collections.sort(claves);

// Valores
List<Integer> valores = new ArrayList<>(map.values());
Collections.sort(valores);

// Entradas (clave + valor juntos) — el más útil
List<Map.Entry<String, Integer>> entradas = new ArrayList<>(map.entrySet());
entradas.sort((a, b) -> a.getKey().compareTo(b.getKey()));
```

`Map.Entry` es un objeto que agrupa clave y valor. Se accede con `.getKey()` y `.getValue()`.

---

## Métodos del Map

| Método | Devuelve |
|---|---|
| `keySet()` | `Set` con las claves |
| `values()` | `Collection` con los valores |
| `entrySet()` | `Set<Map.Entry<K,V>>` con pares clave-valor |

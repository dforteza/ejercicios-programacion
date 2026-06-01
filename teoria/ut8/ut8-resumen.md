# Colecciones en Java (JCF)

## Índice
1. [¿Qué son las colecciones y para qué sirven?](#1-qué-son-las-colecciones-y-para-qué-sirven)
2. [El JCF — Java Collections Framework](#2-el-jcf--java-collections-framework)
3. [Estructuras de datos — la teoría detrás](#3-estructuras-de-datos--la-teoría-detrás)
4. [Jerarquía del JCF](#4-jerarquía-del-jcf)
5. [¿Cuál usar? Diagrama de decisión](#5-cuál-usar-diagrama-de-decisión)
6. [Iteradores — cómo recorrer una colección](#6-iteradores--cómo-recorrer-una-colección)
7. [Comparable y Comparator — cómo ordenar](#7-comparable-y-comparator--cómo-ordenar)
8. [List — colecciones con orden y duplicados](#8-list--colecciones-con-orden-y-duplicados)
9. [Set — colecciones sin duplicados](#9-set--colecciones-sin-duplicados)
10. [Map — pares clave → valor](#10-map--pares-clave--valor)
11. [Tabla comparativa](#11-tabla-comparativa)

---

## 1. ¿Qué son las colecciones y para qué sirven?

Cuando necesitas guardar varios objetos del mismo tipo, la opción básica es un **array**.  
El problema del array es que tiene **tamaño fijo**: lo defines al crearlo y no puedes añadir ni quitar elementos.

Las **colecciones** resuelven eso. 

**DEFINICION: Objetos que agrupan otros objetos y se redimensionan solos.**

| | Array | Colección |
|---|---|---|
| Tamaño | Fijo, definido al crear | Dinámico, crece y encoge |
| Admite primitivos | Sí (`int[]`) | No — solo objetos (`Integer`, `String`...) |
| Métodos útiles | Ninguno | add, remove, contains, sort... |
| Genérico | `String[]` | `List<String>` |

> **Autoboxing:** Java convierte automáticamente `int` → `Integer` al meterlo en una colección, así que en la práctica no lo notas.

Todas las colecciones están en el paquete `java.util`. Siempre hay que importar.

---

## 2. El JCF — Java Collections Framework

**JCF** (Java Collections Framework) es el conjunto de **interfaces y clases** que Java proporciona para trabajar con colecciones. No es una sola clase, es toda una familia.

Dentro del JCF hay dos grandes ramas:

- **Collection** — AGRUPA elementos sueltos (listas, conjuntos, colas)
- **Map** — ASOCIA una clave a un valor (como un diccionario)

---

## 3. Estructuras de datos — la teoría detrás

Antes de ver las clases concretas, hay que entender qué **estructura interna** usa cada una. Esto explica por qué unas son rápidas para unas cosas y lentas para otras.

### 3.1. Array dinámico
- Elementos guardados en posiciones contiguas de memoria.  
- VENTAJA: **Acceder por posición** es instantáneo
- DESVENTAJA: **Insertar en medio** es lento porque hay que desplazar todo.

### 3.2. Lista enlazada
- Cada elemento (nodo) guarda su valor y un puntero al siguiente nodo.  
- VENTAJA: **Insertar** o **borrar** en cualquier punto es rápido.
- DESVENTAJA: **Buscar por posición** es lento porque hay que recorrer nodo a nodo.

```
[Ana] → [Ben] → [Zara] → null
```

### 3.3. Pila (Stack) — LIFO
- Last In, First Out. Como una pila de platos: solo puedes coger el de arriba.  
- Se usa para historial de navegación, deshacer acciones, llamadas recursivas.

```
push(1) push(2) push(3)
   ↓
  [3]  ← solo puedes acceder aquí (cima)
  [2]
  [1]
```

### 3.4. Cola (Queue) — FIFO
First In, First Out. Como una cola del supermercado: el primero que entra es el primero que sale.

```
entra por aquí →  [A][B][C]  → sale por aquí
```

### 3.5. Árbol binario
- Estructura jerárquica
- Cada nodo tiene como máximo dos hijos (izquierdo y derecho).  
- Se mantiene ordenado: los menores van a la izquierda, los mayores a la derecha.  
- VENTAJAS: **Buscar, insertar y borrar en O(log n)** — mucho más rápido que recorrer una lista.

```
        5
       / \
      3   7
     / \
    1   4
```

### 3.6. Tabla Hash
- Usa una función matemática (hash) para calcular en qué posición guardar cada elemento.  
- VENTAJA: **Añadir, buscar y borrar en O(1)** — acceso casi instantáneo, pero sin orden.

---

## 4. Jerarquía del JCF

Cada clase del JCF **implementa una interfaz**. La **interfaz define qué operaciones están disponibles**; la clase concreta decide cómo las implementa internamente.

```
Iterable<T>
    └── Collection<E>
            ├── List<E>
            │       ├── ArrayList      ← array dinámico
            │       ├── LinkedList     ← lista enlazada
            │       └── Stack          ← pila LIFO (legacy)
            │
            ├── Set<E>
            │       ├── HashSet        ← tabla hash
            │       ├── LinkedHashSet  ← tabla hash + orden inserción
            │       └── TreeSet        ← árbol binario
            │
            └── Queue<E>
                    └── LinkedList     ← también implementa Queue

Map<K,V>   (rama separada — no extiende Collection)
    ├── HashMap        ← tabla hash
    ├── LinkedHashMap  ← tabla hash + orden inserción
    └── TreeMap        ← árbol binario
```

**Cómo leer esto:**
- `List`, `Set`, `Queue` y `Map` son **interfaces** — definen el contrato.
- `ArrayList`, `HashSet`, `HashMap`... son **clases concretas** — las que instancias con `new`.
- Puedes escribir `List<String> lista = new ArrayList<>()` porque `ArrayList` implementa `List`. Esto es polimorfismo aplicado a colecciones.

---

## 5. ¿Cuál usar? Diagrama de decisión

Ante un problema nuevo, sigue este árbol de preguntas:

```
¿Necesitas guardar pares clave → valor?
│
├── SÍ → usa Map
│         ├── ¿Quieres orden de inserción?   → LinkedHashMap
│         ├── ¿Quieres orden natural (A→Z)?  → TreeMap
│         └── ¿No importa el orden?          → HashMap
│
└── NO → ¿Puedes tener duplicados?
          │
          ├── SÍ → usa List
          │         ├── ¿Muchos accesos por posición?      → ArrayList
          │         └── ¿Muchas inserciones/borrados?      → LinkedList
          │
          └── NO → usa Set
                    ├── ¿Quieres orden de inserción?   → LinkedHashSet
                    ├── ¿Quieres orden natural (A→Z)?  → TreeSet
                    └── ¿No importa el orden?          → HashSet
```

---

## 6. Iteradores — cómo recorrer una colección

Un **iterador** es un **objeto que sabe cómo recorrer una colección elemento a elemento, sin** que tú tengas que **preocuparte de índices** ni de cómo está organizada internamente.

Hay dos interfaces involucradas:

```java
// Iterable — cualquier clase que implemente esto puede usarse en un for-each
public interface Iterable<T>
{
    Iterator<T> iterator();   // devuelve un iterador listo para usar
}

// Iterator — el iterador en sí
public interface Iterator<T>
{
    boolean hasNext();   // 1- ¿queda algún elemento?
    T       next();      // 2- dame el siguiente elemento
    void    remove();    // 3- elimina el elemento que acabas de obtener con next()
}
```

### Tres formas de recorrer una colección

```java
List<String> lista = new ArrayList<>();
lista.add("Ana");
lista.add("Ben");
lista.add("Zara");

// Forma 1 — for-each (la más habitual, recomendada)
for (String nombre : lista)
{
    System.out.println(nombre);
}

// Forma 2 — Iterator explícito (necesario si vas a eliminar mientras recorres)

    // it se crea PERO NO APUNTA A NINGUN ELEMENTO TODAVIA
Iterator<String> it = lista.iterator();

while (it.hasNext())
{
    String nombre = it.next();
    System.out.println(nombre);
}

// Forma 3 — for clásico con índice (solo en List, no en Set ni Map)
for (int i = 0; i < lista.size(); i++)
{
    System.out.println(lista.get(i));
}
```

### ConcurrentModificationException

Si intentas **modificar la colección mientras la recorres** con for-each, Java lanza esta excepción.

```java
// MAL — lanza ConcurrentModificationException
for (String nombre : lista)
{
    if (nombre.equals("Ben"))
        lista.remove(nombre);   // ← prohibido dentro del for-each
}

// BIEN — usa el remove() del iterador
Iterator<String> it = lista.iterator();

while (it.hasNext())
{
    String nombre = it.next();

    if (nombre.equals("Ben"))
        it.remove();   // ← correcto, el iterador sabe cómo hacerlo sin romperse
}
```

---

## 7. Comparable y Comparator — cómo ordenar

Por defecto, Java no sabe cómo ordenar tus objetos propios. Si tienes una lista de `Alumnos` y llamas a `Collections.sort()`, Java no tiene ni idea de si ordenar por nombre, por nota o por DNI.

**DEFINICIÓN:**
1- Comparable:
- Interfaz que una clase implementa para definir su orden natural (por defecto).
- EJ: En Strings el ON es alfabético, en números es de menor a mayor.

2- Comparator:
- Interfaz que define un orden externo, flexible, que puedes usar sin modificar la clase.

Para eso existen dos mecanismos:

### Comparable\<T\> — el objeto define su propio orden natural

La clase implementa `Comparable` y define el método `compareTo`. Es el **orden por defecto** de esa clase.

```java
public class Alumno implements Comparable<Alumno>
{
    private String nombre;
    private int nota;

    // Definimos que el orden natural de Alumno es alfabético por nombre
    @Override
    public int compareTo(Alumno otro)
    {
        return this.nombre.compareTo(otro.nombre);
    }
}
```

La regla del valor de retorno:

| `compareTo` devuelve | Significa |
|---|---|
| número negativo | `this` va **antes** que `otro` |
| 0 | son **iguales** en orden |
| número positivo | `this` va **después** que `otro` |

### Comparator\<T\> — orden externo y flexible

Cuando necesitas ordenar por otro criterio (sin tocar la clase), o cuando la clase es de terceros y no puedes modificarla.

```java
// === CLASE ANÓNIMA ===
// Comparator por nota (de menor a mayor)
Comparator<Alumno> porNota = new Comparator<Alumno>()
{
    @Override
    public int compare(Alumno a, Alumno b)
    {
        return Integer.compare(a.getNota(), b.getNota());
    }
};

// Lo mismo en una sola línea con lambda
Comparator<Alumno> porNota = (a, b) -> Integer.compare(a.getNota(), b.getNota());
```

### Usar ambos para ordenar

```java
List<Alumno> alumnos = new ArrayList<>();

Collections.sort(alumnos);            // usa compareTo de Comparable
Collections.sort(alumnos, porNota);   // usa el Comparator externo
alumnos.sort(porNota);                // equivalente a la línea anterior
```

> `TreeSet` y `TreeMap` también los usan: si la clase implementa `Comparable`, el árbol se ordena solo. Si no, debes pasarle un `Comparator` en el constructor.

---

## 8. List — colecciones con orden y duplicados

Una `List`:
- mantiene el **orden en que insertas** los elementos
- **permite duplicados**.  

Hay tres implementaciones principales.

### ArrayList

> EDA: array dinámico.

**Cuándo usarla:** cuando accedes muchas veces por posición y pocas veces insertas o borras en el medio.

```java
List<String> lista = new ArrayList<>();

// ADD
lista.add("Ana");           // añade al final
lista.add(0, "Zara");       // inserta en posición 0 — desplaza el resto

// REMOVE
lista.remove(0);            // elimina por índice
lista.remove("Ben");        // elimina por valor (la primera ocurrencia)

// GET
lista.get(1);               // acceso directo por índice — rápido

// SET
lista.set(0, "Ben");        // reemplaza el elemento en posición 0

// CONTAINS
lista.contains("Ana");      // true si está, false si no

// INDEX OF
lista.indexOf("Ana");       // posición de la primera ocurrencia, -1 si no existe

// SIZE
lista.size();               // número de elementos

// CLEAN
lista.isEmpty();            // true si no tiene elementos
lista.clear();              // vacía la lista
```

### LinkedList

> EDA: lista enlazada.

**Cuándo usarla:** cuando insertas o borras muchos elementos (**especialmente al principio o al final**) y el acceso por posición es raro.  

> También implementa `Queue` y `Deque`, por lo que puedes usarla como cola o pila moderna.

```java
LinkedList<String> ll = new LinkedList<>();

// === LIST ===
// ADD
ll.add("Ana");          // añade al final (como List)
ll.addFirst("Zara");    // añade al principio — O(1)
ll.addLast("Ben");      // añade al final — O(1)

// REMOVE
ll.remove("Ana");       // elimina la primera ocurrencia
ll.removeFirst();       // extrae el primero
ll.removeLast();        // extrae el último

// GET
ll.get(1);              // acceso por índice — O(n) lento
ll.getFirst();          // consulta el primero sin quitar
ll.getLast();           // consulta el último sin quitar

// === QUEUE ===
// Uso como Queue (cola FIFO)
ll.offer("elemento");   // añade al final
ll.poll();              // extrae del inicio, devuelve null si vacía
ll.peek();              // consulta el inicio sin extraer
```

### Stack

> EDA: pila (LIFO).

Pila LIFO. Es una clase **legacy** (antigua) — en código moderno se prefiere `Deque`.  
Sigue siendo válida y aparece en los ejercicios.

```java
Stack<Integer> pila = new Stack<>();

pila.push(10);     // mete 10 en la cima
pila.push(20);     // mete 20 encima
pila.peek();       // devuelve 20 (la cima) sin sacarlo
pila.pop();        // saca y devuelve 20
pila.isEmpty();    // true si no tiene elementos
```

**Eficiencia comparada:**

| Operación | ArrayList | LinkedList |
|---|---|---|
| get(i) — acceder por posición | O(1) rápido | O(n) lento |
| add al final | O(1) | O(1) |
| add/remove en el medio | O(n) lento | O(1) rápido (si tienes el nodo) |

---

## 9. Set — colecciones sin duplicados

Un `Set`:
- **no permite elementos repetidos** (para detectar duplicados usa los métodos `equals()` y `hashCode()`).
- **no tiene orden definido** (a menos que uses `LinkedHashSet` o `TreeSet`).

> Los métodos disponibles son los básicos de `Collection`: `add`, `remove`, `contains`, `size`, `isEmpty`, `clear`. No hay acceso por índice porque no hay posición definida.

### HashSet

> EDA: tabla hash.

CARACTERÍSTICAS:
- No garantiza ningún orden
- Es la más rápida (O(1) en todo).  

**Cuándo usarla:** cuando solo quieres evitar duplicados y el orden no importa.

```java
Set<String> set = new HashSet<>();

set.add("Ana");
set.add("Ben");
set.add("Ana");    // ignorado — "Ana" ya existe

System.out.println(set.size());        // 2
System.out.println(set.contains("Ben")); // true
```

### LinkedHashSet

> EDA: tabla hash + lista enlazada (RESPETA EL ORDEN DE INSERCIÓN).

```java
Set<String> set = new LinkedHashSet<>();
set.add("Zara");
set.add("Ana");
set.add("Ben");
// Si iteras: Zara, Ana, Ben (orden de inserción garantizado)
```

### TreeSet

> EDA: árbol binario de búsqueda.

Mantiene los elementos **ordenados** (orden natural si implementan `Comparable`, o con un `Comparator` pasado al constructor).  

```java
Set<Integer> set = new TreeSet<>();
set.add(5);
set.add(1);
set.add(3);
// Si iteras: 1, 3, 5 (orden natural ascendente)

// Métodos extra de TreeSet
TreeSet<Integer> ts = new TreeSet<>(set);
ts.first();           // el elemento más pequeño: 1
ts.last();            // el elemento más grande: 5
ts.headSet(3);        // elementos estrictamente menores que 3: [1]
ts.tailSet(3);        // elementos >= 3: [3, 5]
ts.subSet(1, 4);      // elementos entre 1 (inc) y 4 (exc): [1, 3]
```

> Para usar `TreeSet` con objetos propios: la clase debe implementar `Comparable`, o debes pasar un `Comparator` al constructor de `TreeSet`. Si `compareTo` devuelve 0, `TreeSet` lo considera duplicado aunque `equals` diga otra cosa.

---

## 10. Map — pares clave → valor

Un `Map`: 
- asocia cada **clave** a un **valor**.
- Las claves son únicas (no puede haber dos iguales), pero los valores pueden repetirse.

Es como un diccionario: buscas la palabra (clave) y obtienes la definición (valor).

`Map` **no extiende `Collection`**, es una rama aparte del JCF.

### Operaciones básicas

```java
Map<String, Integer> notas = new HashMap<>();

// ADD
notas.put("Ana", 9);           // inserta o sobreescribe
notas.put("Ben", 7);
notas.put("Ana", 10);          // sobreescribe el 9 — clave duplicada no permitida

// REMOVE
notas.remove("Ben");           // elimina la entrada con clave "Ben"

// GET
notas.get("Ana");              // devuelve 10
notas.getOrDefault("Zara", 0); // devuelve 0 si "Zara" no existe (evita NullPointerException)

// CONTAINS
notas.containsKey("Ben");      // true
notas.containsValue(10);       // true

// SIZE
notas.size();                  // número de pares

// CLEAN
notas.isEmpty();
notas.clear();
```

### Recorrer un Map

```java
// Solo las claves
for (String clave : notas.keySet())
{
    System.out.println(clave);
}

// Solo los valores
for (int valor : notas.values())
{
    System.out.println(valor);
}

// Clave y valor a la vez — la forma más completa
for (Map.Entry<String, Integer> entry : notas.entrySet())
{
    System.out.println(entry.getKey() + " → " + entry.getValue());
}
```

> `entrySet()` devuelve un `Set` de pares. Cada par es un `Map.Entry` que tiene `getKey()` y `getValue()`.

### Patrón frecuencia — caso de uso típico

Contar cuántas veces aparece cada elemento:

```java
String[] palabras = {"hola", "mundo", "hola", "java", "hola"};
Map<String, Integer> frecuencia = new HashMap<>();

for (String p : palabras)
{
    frecuencia.put(p, frecuencia.getOrDefault(p, 0) + 1);
}
// Resultado: {hola=3, mundo=1, java=1}
```

### HashMap

> EDA: tabla hash.

### LinkedHashMap

> EDA: tabla hash + lista enlazada (RESPETA EL ORDEN DE INSERCIÓN).

### TreeMap

> EDA: árbol binario de búsqueda.

Mantiene las claves **ordenadas** (orden natural o `Comparator`).  
Implementa `SortedMap` y `NavigableMap`, con métodos adicionales:

```java
TreeMap<String, Integer> tm = new TreeMap<>();
tm.put("Zara", 8);
tm.put("Ana", 10);
tm.put("Ben", 7);
// Claves ordenadas: Ana, Ben, Zara

tm.firstKey();          // "Ana"
tm.lastKey();           // "Zara"
tm.headMap("Ben");      // claves < "Ben": {Ana=10}
tm.tailMap("Ben");      // claves >= "Ben": {Ben=7, Zara=8}
tm.floorKey("C");       // mayor clave <= "C": "Ben"
tm.ceilingKey("C");     // menor clave >= "C": "Zara"
```

### Map anidado — datos compuestos

```java
// Notas de varios alumnos en varias asignaturas
Map<String, Map<String, Double>> curso = new HashMap<>();

curso.put("Ana", new HashMap<>());
curso.get("Ana").put("Matemáticas", 9.0);
curso.get("Ana").put("Java", 8.5);

curso.put("Ben", new HashMap<>());
curso.get("Ben").put("Matemáticas", 6.5);

// Acceder a la nota de Ana en Java
double nota = curso.get("Ana").get("Java");   // 8.5
```

---

## 11. Tabla comparativa

### Familia List

| Clase | Estructura interna | Orden | Duplicados | Rápida en |
|---|---|---|---|---|
| `ArrayList` | Array dinámico | Inserción | Sí | Acceso por índice |
| `LinkedList` | Nodos enlazados | Inserción | Sí | Insertar/borrar en extremos |
| `Stack` | Array (legacy) | LIFO | Sí | push/pop |

### Familia Set

| Clase | Estructura interna | Orden | Duplicados | Requiere |
|---|---|---|---|---|
| `HashSet` | Tabla hash | Ninguno | No | equals + hashCode |
| `LinkedHashSet` | Hash + lista | Inserción | No | equals + hashCode |
| `TreeSet` | Árbol rojo-negro | Natural o Comparator | No | Comparable o Comparator |

### Familia Map

| Clase | Estructura interna | Orden claves | Requiere en clave |
|---|---|---|---|
| `HashMap` | Tabla hash | Ninguno | equals + hashCode |
| `LinkedHashMap` | Hash + lista | Inserción | equals + hashCode |
| `TreeMap` | Árbol rojo-negro | Natural o Comparator | Comparable o Comparator |

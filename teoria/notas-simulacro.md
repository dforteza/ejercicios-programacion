# Notas Simulacros — Conceptos destacados

---

## 1. `float` vs `Float` — primitivo vs wrapper y detección de "no asignado"

Los primitivos (`int`, `float`, `double`) tienen valor por defecto 0 cuando no se asignan. Los wrappers (`Integer`, `Float`, `Double`) tienen valor por defecto `null`.

```java
float  coste;   // vale 0.0 — no puedes distinguir "no asignado" de "coste cero"
Float  coste;   // vale null — puedes detectar que nunca se asignó
```

Por eso en el simulacro 1 la condición correcta era:
```java
if (coste == null)          // correcto: detecta "no calculado"
if (coste == 0F)            // incorrecto: un coste de 0€ entraría aquí también
```

---

## 2. Sufijos de literales y lectura por teclado

| Tipo | Sufijo literal | Sin sufijo | Por teclado |
|------|---------------|------------|-------------|
| `float` | `f` **obligatorio** | error de compilación | `sc.nextFloat()` |
| `double` | `d` opcional | double por defecto | `sc.nextDouble()` |
| `long` | `L` si no cabe en int | int por defecto | `sc.nextLong()` |

```java
float  f = 155.5f;      // sin f → error
double d = 155.5;       // sin d → correcto, es double por defecto
long   l = 9999999999L; // sin L → error, no cabe en int
```

En sistema en español el separador decimal por teclado es la **coma**: `155,5`

---

## 3. Clases wrapper — más allá del null

```java
// 1. Pueden ser null (ya visto)
Float coste = null;

// 2. Métodos de utilidad — conversión desde String
Integer.parseInt("42")      // String → int
Double.parseDouble("3.14")  // String → double  ← muy frecuente en ficheros

// 3. Necesarias en colecciones — los primitivos no se pueden usar
List<Integer> lista = new ArrayList<>();  // ✓
List<int>     lista = new ArrayList<>();  // error de compilación

// 4. Autoboxing/Unboxing — Java convierte automáticamente
Integer x = 5;    // autoboxing:  int → Integer
int     y = x;    // unboxing:    Integer → int
```

---

## 4. Atributo `static` como autoincrementador de ID

Cuando se pide que cada objeto tenga un id único y autogenerado:

```java
public class Paseo {
    private static int secuencia = 0;  // una sola copia, pertenece a la clase
    private int id;

    public Paseo(...) {
        this.id = ++secuencia;  // incrementa antes de asignar → primer id = 1
    }
}
```

```
new Paseo("Toby",  ...)  →  secuencia = 1  →  id = 1
new Paseo("Laika", ...)  →  secuencia = 2  →  id = 2
new Paseo("Tala",  ...)  →  secuencia = 3  →  id = 3
```

`static` hace que la variable pertenezca a la **clase**, no a cada objeto. Todos los objetos leen y modifican el mismo contador. `++secuencia` incrementa antes de asignar (primer id = 1), `secuencia++` asignaría primero (primer id = 0).

---

## 5. `n` vs `arr.length` en arrays con repositorio

Cuando usas un array con un contador `n` de elementos ocupados, **siempre itera hasta `n`**, nunca hasta `arr.length`:

```
arr.length = 10, n = 3

pos:  [0]    [1]    [2]    [3]    [4]  ... [9]
arr:  Toby   Laika  Tala   null   null      null
```

```java
for (int i = 0; i < arr.length; i++)   // llega a null → NullPointerException
    arr[i].getId();                     // arr[3] es null, no hay objeto

for (int i = 0; i < n; i++)            // solo recorre posiciones ocupadas ✓
    arr[i].getId();
```

Y tras eliminar un elemento con desplazamiento, siempre `n--`:
```java
for (int j = i; j < n - 1; j++)
    arr[j] = arr[j + 1];
n--;  // sin esto el contador sigue creyendo que hay un elemento más
```

---

## 6. Polimorfismo de referencia y sobreescritura

**Polimorfismo de referencia:** una referencia de tipo padre puede apuntar a cualquier objeto hijo.

```
Vehiculo
  ├── Camion
  ├── Furgoneta
  └── Motocicleta
```

```java
Vehiculo v = new Camion(...);     // ✓ Camion ES Vehiculo
Vehiculo v = new Furgoneta(...);  // ✓ Furgoneta ES Vehiculo
Camion   c = new Vehiculo(...);   // ✗ Vehiculo NO es necesariamente Camion
```

Esto hace que un método con `Vehiculo` en la firma acepte cualquier subclase:
```java
public void alta(Vehiculo v)  // acepta Camion, Furgoneta y Motocicleta
```

Lo mismo con colecciones — declaras la abstracción, instancias la implementación:
```java
List<Mascota> lista = new ArrayList<>();   // si mañana cambias a LinkedList
List<Mascota> lista = new LinkedList<>();  // solo tocas esta línea
```

**Polimorfismo de sobreescritura:** al llamar un método, Java ejecuta siempre el del **objeto real**, no el del tipo de la referencia:
```java
Vehiculo v = new Camion(...);
v.toString();  // ejecuta toString() de Camion, no de Vehiculo
```

Esto se decide en tiempo de ejecución. Los atributos no funcionan así — solo los métodos.

---

## 7. Widening conversion — cast implícito

Java convierte automáticamente de menor a mayor precisión:

```
byte → short → int → long → float → double
                              ↑
                            char →
```

```java
int    i = 100;
long   l = i;      // automático ✓
float  f = l;      // automático ✓  ← por eso float funciona en métodos double
double d = f;      // automático ✓
```

En sentido contrario necesitas cast explícito y puedes perder datos:
```java
double d = 9.99;
int    i = (int) d;  // → 9, pierde decimales
```

---

## 8. `instanceof` entre clases de la jerarquía

`instanceof` sigue la jerarquía hacia **arriba**, nunca entre clases hermanas:

```java
Furgoneta f = new Furgoneta(...);

f instanceof Furgoneta  // true
f instanceof Vehiculo   // true  — sube por herencia
f instanceof Camion     // false — son hermanas, no hay relación
```

Útil en `imprimirITV` para distinguir tipo de vehículo y aplicar años distintos.

---

## 9. `getClass()` vs `instanceof` en `equals`

`getClass()` es demasiado estricto con herencia:
```java
if (getClass() != obj.getClass())  // Mascota vs Perro → false aunque tengan mismo nombre y tlf
    return false;
```

`instanceof` es lo correcto cuando hay subclases:
```java
if (!(obj instanceof Mascota))  // Perro ES Mascota → pasa la comprobación ✓
    return false;
```

El enunciado decía "dos mascotas son iguales si coinciden en nombre y teléfono" — un `Perro` es una `Mascota`, así que debe poder ser igual a otra `Mascota` con mismo nombre y tlf.

---

## 10. `ConcurrentModificationException` — `for-each` vs `Iterator`

El `for-each` lleva un contador interno de modificaciones (`modCount`). Si algo externo modifica la lista mientras se recorre, el iterador detecta la discrepancia y explota:

```
Lista: [Luna, Rocky, Max]
              ↑
       iterador aquí, modCount = 3

lista.remove(Rocky)  ← modificación externa
Lista: [Luna, Max]   ← modCount = 2

iterador avanza → "modCount cambió sin que yo lo hiciera" → 💥 ConcurrentModificationException
```

Con `Iterator` explícito el borrado lo hace él mismo, sabe que el cambio es suyo:
```java
Iterator<Mascota> it = mascotas.iterator();
while (it.hasNext()) {
    if (condicion)
        it.remove();  // seguro — el iterador actualiza su propio modCount ✓
}
```

Regla práctica:
- Eliminar **una** ocurrencia → `for-each` con `break` (sales antes de que el iterador avance)
- Eliminar **varias** ocurrencias → `Iterator` explícito obligatorio

---

## 11. `HashSet` vs `LinkedHashSet`

Ambos eliminan duplicados usando `equals` y `hashCode`. La diferencia es el orden:

```
HashSet        → orden interno del hash, impredecible
LinkedHashSet  → preserva el orden de inserción
```

```java
// Para eliminarDuplicados manteniendo orden original:
Set<Mascota> sin = new LinkedHashSet<>(mascotas);
mascotas = new ArrayList<>(sin);
```

Al reconvertir a `ArrayList`, el constructor copia los elementos en el orden que devuelve el iterador del set — lo que haya en el set, en ese orden, pasa a la lista.

---

## 12. Patrón `Map<Clave, List<Valor>>` para agrupar

Cuando necesitas agrupar objetos por un campo:

```java
TreeMap<LocalDate, List<Mascota>> map = new TreeMap<>();

for (Mascota m : mascotas) {
    LocalDate fecha = m.getFechaDesaparicion();

    if (!map.containsKey(fecha)) {
        List<Mascota> lista = new ArrayList<>();
        lista.add(m);
        map.put(fecha, lista);       // clave nueva → crear lista e insertar
    } else {
        map.get(fecha).add(m);       // clave existente → obtener lista y añadir
    }
}
```

`TreeMap` ordena las claves automáticamente — con `LocalDate` queda cronológico sin hacer nada más. Para recorrerlo:

```java
for (Map.Entry<LocalDate, List<Mascota>> entry : map.entrySet()) {
    System.out.println(entry.getKey());
    for (Mascota m : entry.getValue())
        System.out.println("  " + m);
}
```

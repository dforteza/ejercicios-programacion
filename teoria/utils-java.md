# Utils Java

## Índice
1. [Conversión entre String y primitivo](#1-conversión-entre-string-y-primitivo)
2. [Math.random()](#2-mathrandom)
3. [Scanner: nextInt() y el \n sobrante](#3-scanner-nextint-y-el-n-sobrante)
4. [final vs static final](#4-final-vs-static-final)
5. [Fechas y horas — java.time](#5-fechas-y-horas--javatime)
6. [equals y hashCode](#6-equals-y-hashcode)
7. [Strings — format y StringBuilder](#7-strings--format-y-stringbuilder)
8. [Atributo static como autoincrementador de ID](#8-atributo-static-como-autoincrementador-de-id)

---

## 1. Conversión entre String y primitivo

Son operaciones inversas:

| Dirección | Método | Ejemplo | Resultado |
|---|---|---|---|
| primitivo → String | `String.valueOf()` | `String.valueOf(42)` | `"42"` |
| String → primitivo | `TipoWrapper.parseTipo()` | `Integer.parseInt("42")` | `42` |

---

### String.valueOf() — primitivo → String

Método estático de `String` que convierte cualquier primitivo a su representación en texto.

| Ejemplo | Resultado |
|---|---|
| `String.valueOf(123)` | `"123"` |
| `String.valueOf(3.14)` | `"3.14"` |
| `String.valueOf(true)` | `"true"` |
| `String.valueOf('a')` | `"a"` |

> Alternativas: `Integer.toString(n)` o `"" + n`, pero `valueOf` es la más limpia.

---

### parseX() — String → primitivo

Cada clase wrapper tiene su versión. Convierte texto a su tipo primitivo correspondiente.

| Método | Resultado |
|---|---|
| `Integer.parseInt("42")` | `int` 42 |
| `Double.parseDouble("3.14")` | `double` 3.14 |
| `Boolean.parseBoolean("true")` | `boolean` true |
| `Long.parseLong("999")` | `long` 999 |

**Cuándo se usa:** cuando recibes datos como texto (entrada de usuario, ficheros, argumentos) y necesitas operar con ellos numéricamente.

> Lanza `NumberFormatException` si el String no es convertible (`"abc"` → error).

---

## 2. Math.random()

### ¿Qué es?
Método estático de la clase `Math` que genera un número aleatorio `double` entre `0.0` (incluido) y `1.0` (excluido).

### Usos

| Uso | Ejemplo | Resultado |
|-----|---------|-----------|
| Número entre 0 y 99 | `(int)(Math.random() * 100)` | 0 – 99 |
| Número entre 1 y 100 | `(int)(Math.random() * 100) + 1` | 1 – 100 |
| Número entre 1 y 6 (dado) | `(int)(Math.random() * 6) + 1` | 1 – 6 |
| Número entre min y max | `(int)(Math.random() * (max - min + 1)) + min` | min – max |

> Fórmula general: `(int)(Math.random() * rango) + inicio`

### ¿Por qué el +1 en el rango?
`Math.random()` devuelve entre `0.0` y `0.999...`. Al multiplicar por `n` y truncar con cast a `int`, obtienes exactamente `n` valores posibles: `0, 1, 2, ... n-1`. El techo nunca se alcanza.

Por eso la fórmula usa `max - min + 1` como rango: el `+1` compensa ese techo inalcanzable y hace que el máximo sea inclusivo.

**Ejemplo** (entre 2 y 5): `(int)(Math.random() * 4) + 2`
- `* 4` → `0.0` a `3.999...` → enteros `0, 1, 2, 3`
- `+ 2` → `2, 3, 4, 5` ✓

---

## 3. Scanner: nextInt() y el \n sobrante

### ¿Por qué hay que limpiar el buffer?

Al escribir un número y pulsar Enter, el buffer contiene el número **y** el `\n` del Enter.

`nextInt()` lee solo el número y se para — el `\n` queda sin consumir:

```
Tú escribes:  5 + Enter
Buffer:       5 \n

nextInt()  →  lee "5",  deja: \n
```

Si a continuación llamas a `nextLine()` (para leer un String), se encuentra ese `\n` y devuelve cadena vacía sin esperar input del usuario.

### Solución

Añadir un `sc.nextLine()` después del `nextInt()` para tragarse el `\n` sobrante:

```java
n = sc.nextInt();
sc.nextLine(); // limpia el \n
nombre = sc.nextLine(); // ahora sí espera input
```

### Cuándo hace falta

Solo cuando mezclas `nextInt()` con `nextLine()`. Si encadenas varios `nextInt()` seguidos no es necesario, porque `nextInt()` salta whitespace (incluido `\n`) automáticamente.

---

## 4. final vs static final

**Clave:** `static` controla **cuántas copias** hay. `final` controla **si puede cambiar**.

| | `final` | `static final` |
|---|---|---|
| Pertenece a | Cada objeto (una copia por instancia) | La clase (una sola copia compartida) |
| Se asigna en | El constructor | La declaración |
| Uso típico | Atributo inmutable de un objeto concreto | Constante global |

### `static final` — pertenece a la clase

Una sola copia compartida por todos los objetos. Se accede por la clase, no por el objeto.

```java
public class Circulo
{
    public static final double PI = 3.14159;  // todos los círculos usan el mismo PI
}

System.out.println(Circulo.PI);  // se accede por la clase
```

### `final` — pertenece al objeto, pero no cambia

Cada objeto tiene su propio valor, asignado en el constructor, que ya no puede modificarse.

```java
public class Empleado
{
    private final String dni;   // cada empleado tiene su DNI único e inmutable
    private String nombre;      // el nombre sí puede cambiar

    public Empleado(String dni, String nombre)
    {
        this.dni = dni;
        this.nombre = nombre;
    }
}
```

### Norma para decidir

```
¿Es el mismo valor para todos los objetos?
    SÍ → static final   (constante de clase)
    NO → final          (atributo inmutable por objeto)

¿Puede cambiar después de crearse el objeto?
    NO → añadir final
    SÍ → sin final
```

---

## 5. Fechas y horas — `java.time`

### Los 3 tipos

| Tipo | Representa | Ejemplo |
|---|---|---|
| `LocalDate` | Solo fecha | `2026-05-24` |
| `LocalTime` | Solo hora | `10:30` |
| `LocalDateTime` | Fecha y hora | `2026-05-24T10:30` |

Ninguno almacena zona horaria. Para eso existe `ZonedDateTime`, pero es un caso avanzado.

---

### Crear instancias

**`now()`** — captura el momento actual del sistema

```java
LocalDate.now()         // fecha de hoy
LocalTime.now()         // hora actual
LocalDateTime.now()     // fecha y hora actuales
```

**`of(...)`** — valor concreto que tú defines

```java
LocalDate.of(2026, 12, 25)             // año, mes, día
LocalTime.of(10, 30)                   // hora, minuto
LocalTime.of(10, 30, 45)              // hora, minuto, segundo
LocalDateTime.of(2026, 12, 25, 10, 30)
```

**`parse(...)`** — desde un String con formato ISO por defecto

```java
LocalDate.parse("2026-12-25")          // formato yyyy-MM-dd
LocalTime.parse("10:30:00")            // formato HH:mm:ss
```

> Si el String no tiene el formato correcto lanza `DateTimeParseException`.

---

### Leer partes - Getters

```java
LocalDate d = LocalDate.of(2026, 5, 24);

d.getYear()          // → 2026
d.getMonthValue()    // → 5   (número)
d.getMonth()         // → MAY (enum Month)
d.getDayOfMonth()    // → 24
d.getDayOfWeek()     // → SUNDAY (enum DayOfWeek)
```

```java
LocalTime t = LocalTime.of(10, 30, 45);

t.getHour()          // → 10
t.getMinute()        // → 30
t.getSecond()        // → 45
```

---

### Comparar

Los mismos métodos funcionan igual en `LocalDate`, `LocalTime` y `LocalDateTime`.

```java
LocalDate a = LocalDate.of(2026, 1, 1);
LocalDate b = LocalDate.of(2026, 6, 1);

a.isBefore(b)        // → true   (a es anterior a b)
a.isAfter(b)         // → false  (a no es posterior a b)
a.equals(b)          // → false  (no son el mismo día)
a.compareTo(b)       // → negativo si a < b, 0 si iguales, positivo si a > b
```

> `isBefore` e `isAfter` son estrictos — si dos valores son iguales, ambos devuelven `false`.

---

### Operar — aritmética

```java
LocalDate hoy = LocalDate.of(2026, 5, 24);

hoy.plusDays(7)       // → 2026-05-31
hoy.plusMonths(1)     // → 2026-06-24
hoy.plusYears(1)      // → 2027-05-24
hoy.minusDays(10)     // → 2026-05-14
```

```java
LocalTime t = LocalTime.of(10, 30);

t.plusHours(2)        // → 12:30
t.minusMinutes(15)    // → 10:15
```

**Importante:** estas operaciones no modifican el objeto original. Devuelven uno nuevo:

```java
LocalDate hoy = LocalDate.now();
hoy.plusDays(7);                     // no hace nada útil
LocalDate semana = hoy.plusDays(7);  // correcto
```

Esto es porque las clases de `java.time` son **inmutables** — una vez creadas, no cambian.

---

### Formatear para mostrar

**Opción 1 — con getters y `String.format`**

Control total sobre el formato:

```java
LocalDate d = LocalDate.of(2026, 5, 24);

String.format("%02d/%02d/%04d", d.getDayOfMonth(), d.getMonthValue(), d.getYear())
// → "24/05/2026"
```

**Opción 2 — con `DateTimeFormatter`** ← preferida en examen

```java
LocalDate fecha = LocalDate.of(2026, 6, 2);
LocalDateTime fechaHora = LocalDateTime.of(2026, 6, 2, 14, 30);

DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
DateTimeFormatter fmt3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

System.out.println(fecha.format(fmt1));      // → 02/06/2026
System.out.println(fecha.format(fmt2));      // → 02 de junio de 2026
System.out.println(fechaHora.format(fmt3));  // → 02/06/2026 14:30

// Parsear: String → objeto
LocalDate parseada = LocalDate.parse("25/12/2026", fmt1);
```

| Letra | Significa | Ejemplo |
|---|---|---|
| `dd` | día con 2 dígitos | `02` |
| `MM` | mes numérico | `06` |
| `MMMM` | mes en texto | `junio` |
| `yyyy` | año con 4 dígitos | `2026` |
| `HH` | hora (0-23) | `14` |
| `mm` | minuto | `30` |
| `ss` | segundo | `00` |

> Texto literal dentro del patrón va entre comillas simples: `'de'`.

> Para forzar español en `MMMM`: `DateTimeFormatter.ofPattern("...", new Locale("es"))`

---

## 6. equals y hashCode

Ver guía completa con ejemplos: [teoria/ut8/equals-y-hashcode.md](..\teoria\ut8\equals-y-hashcode.md)

---

## 7. Strings — format y StringBuilder

### String.format

Construye un String con formato sin imprimir. Mismo sistema de especificadores que `printf`.

```java
String s = String.format("Nombre: %s, Edad: %d, Saldo: %.2f", "Ana", 25, 1500.50);
// → "Nombre: Ana, Edad: 25, Saldo: 1500,50"
```

| Especificador | Tipo | Ejemplo |
|---|---|---|
| `%s` | String | `"Ana"` |
| `%d` | entero | `25` |
| `%f` | decimal | `1500.500000` |
| `%.2f` | decimal 2 decimales | `1500.50` |
| `%02d` | entero con ceros a la izquierda | `05` |
| `%n` | salto de línea | — |

> Diferencia con `printf`: `printf` imprime directamente, `String.format` devuelve el String para usarlo donde quieras.

---

### StringBuilder

- `String` ES INMUTABLE — cada concatenación con `+` crea un objeto nuevo en memoria.
- `StringBuilder` ES MUTABLE: construye el texto en el mismo objeto, sin crear copias.

**Cuándo usarlo:** cuando concatenas en un bucle o lees un fichero carácter a carácter.

```java
StringBuilder sb = new StringBuilder();

sb.append("Hola");        // añade al final       → "Hola"
sb.append(", ");          // añade al final       → "Hola, "
sb.append("mundo");       // añade al final       → "Hola, mundo"
sb.insert(5, "Java ");    // inserta en posición  → "Hola, Java mundo"

String resultado = sb.toString();  // convierte a String
```

**Uso típico en ficheros** — leer un fichero completo carácter a carácter en una sola pasada:

```java
StringBuilder sb = new StringBuilder();
int c;
while ((c = reader.read()) != -1)
{
    sb.append((char) c);
}

String contenido = sb.toString();
```

| Método | Qué hace |
|---|---|
| `append(x)` | Añade `x` al final — acepta String, char, int, double... |
| `insert(pos, x)` | Inserta `x` en la posición `pos` |
| `toString()` | Devuelve el contenido como `String` |
| `length()` | Longitud actual del contenido |
| `delete(ini, fin)` | Elimina caracteres entre `ini` y `fin` (fin excluido) |

---

## 8. Atributo static como autoincrementador de ID

Cuando un ejercicio pide que cada objeto tenga un `id` único y autogenerado, usa un atributo `static` en la propia clase como contador:

```java
public class Paseo
{
    private static int secuencia = 0;  // una sola copia compartida por todos los objetos
    private int id;

    public Paseo(String nombre, ...) {
        this.id = ++secuencia;  // se incrementa antes de asignar: primer objeto → id 1
        ...
    }
}
```

**Flujo al crear objetos:**
```
new Paseo("Toby",  ...)  →  secuencia = 1  →  id = 1
new Paseo("Laika", ...)  →  secuencia = 2  →  id = 2
new Paseo("Tala",  ...)  →  secuencia = 3  →  id = 3
```

**Por qué funciona:** `static` hace que la variable pertenezca a la clase, no a cada objeto. Todos los objetos leen y modifican el mismo contador.

**`++secuencia` vs `secuencia++`:**
- `++secuencia` → incrementa primero, asigna después → el primer id es 1
- `secuencia++` → asigna primero, incrementa después → el primer id sería 0

---

## 9. Polimorfismo de referencia

Una referencia de tipo padre puede apuntar a cualquier objeto hijo:

```
Vehiculo
  ├── Camion
  ├── Furgoneta
  └── Motocicleta
```

```java
Vehiculo v = new Camion(...);     // ✓ Camion ES Vehiculo
Vehiculo v = new Furgoneta(...);  // ✓ Furgoneta ES Vehiculo
Camion c   = new Vehiculo(...);   // ✗ Vehiculo NO es necesariamente Camion
```

**¿Por qué es útil?**

```java
public void alta(Vehiculo v)  // acepta Camion, Furgoneta y Motocicleta
```

Con `Vehiculo` en la firma admites cualquier subclase. Lo mismo con colecciones:

```java
List<Mascota> lista = new ArrayList<>();   // ✓
List<Mascota> lista = new LinkedList<>();  // ✓
```

**Al llamar métodos**, Java ejecuta siempre el del objeto real, no el del tipo de la referencia:

```java
Vehiculo v = new Camion(...);
v.toString();  // ejecuta toString() de Camion, no de Vehiculo
```

---

## 10. Widening conversion

Java convierte automáticamente de menor a mayor precisión:

```
byte → short → int → long → float → double
                              ↑
                            char →
```

```java
int    i = 100;
long   l = i;      // automático ✓
float  f = l;      // automático ✓
double d = f;      // automático ✓
```

En sentido contrario necesitas cast explícito y puedes perder datos:

```java
double d = 9.99;
int    i = (int) d;  // → 9, pierde decimales
```

# Utils Java

## Índice
1. [Conversión entre String y primitivo](#1-conversión-entre-string-y-primitivo)
2. [Math.random()](#2-mathrandom)
3. [Scanner: nextInt() y el \n sobrante](#3-scanner-nextint-y-el-n-sobrante)
4. [final vs static final](#4-final-vs-static-final)
5. [Fechas y horas — java.time](#5-fechas-y-horas--javatime)
6. [equals y hashCode](#6-equals-y-hashcode)

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

**Opción 2 — con `DateTimeFormatter`**

Más limpio cuando el formato es estándar:

```java
DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
d.format(fmt)    // → "24/05/2026"
```

| Letra | Significa | Ejemplo |
|---|---|---|
| `dd` | día con 2 dígitos | `05` |
| `MM` | mes con 2 dígitos | `05` |
| `yyyy` | año con 4 dígitos | `2026` |
| `HH` | hora (0-23) | `10` |
| `mm` | minuto | `30` |

---

## 6. equals y hashCode

### ¿Para qué sirven?

El `equals` natural (el de `Object`) compara **referencias** — dos objetos son iguales solo si son el mismo objeto en memoria (igual que `==`).

Si quieres que dos objetos distintos en memoria sean considerados iguales por su **contenido**, tienes que sobreescribir `equals`.

### Contrato Java

Si sobreescribes `equals`, **siempre** debes sobreescribir `hashCode`. El contrato dice:
> Si dos objetos son iguales por `equals`, deben tener el mismo `hashCode`.

Si lo rompes, las colecciones basadas en hash (`HashMap`, `HashSet`) fallarán silenciosamente.

### Plantilla de equals

```java
@Override
public boolean equals(Object obj)
{
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;

    MiClase other = (MiClase) obj;

    return this.getCampo1().equals(other.getCampo1())
        && this.getCampo2().equals(other.getCampo2());
}
```

### Plantilla de hashCode

```java
@Override
public int hashCode()
{
    return Objects.hash(campo1, campo2);
}
```

`Objects.hash(...)` genera un número a partir de los campos — dos objetos con los mismos valores dan el mismo número.

### Cuándo necesitas cada cosa

| Colección | Qué necesita |
|-----------|-------------|
| `TreeMap` / `TreeSet` | `Comparable` (o `Comparator`) |
| `HashMap` / `HashSet` / `LinkedHashMap` / `LinkedHashSet` | `equals` + `hashCode` |
| Ambas | Todo |

# equals y hashCode

## ¿Para qué sirven?

- El `equals` natural (el de `Object`) compara **REFERENCIAS** — DOS OBJETOS SON IGUALES SI SON EL MISMO OBJETO EN MEMORIA.

- Si quieres que dos objetos distintos en memoria sean considerados IGUALES POR SU **CONTENIDO**, tienes que SOBREESCRIBIR `equals`.

---

## Contrato Java - EQUALS Y HASCODE DE LA MANOOOOO

Si sobreescribes `equals`, **siempre** debes sobreescribir `hashCode`. El contrato dice:
> Si dos objetos son iguales por `equals`, deben tener el mismo `hashCode`.

Si lo rompes, las colecciones basadas en hash (`HashMap`, `HashSet`) fallarán silenciosamente.

---

## Plantilla de equals

```java
@Override
public boolean equals(Object obj)
{
    // R - N - G
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;

    // Comàrar contenido
    MiClase other = (MiClase) obj;

    return this.getCampo1().equals(other.getCampo1())
        && this.getCampo2().equals(other.getCampo2());
}
```
---

## Plantilla de hashCode

```java
@Override
public int hashCode()
{
    return Objects.hash(campo1, campo2);
}
```

`Objects.hash(...)` genera un número a partir de los campos
---

## Qué pasa si NO sobreescribes

Sin sobreescribir, `equals` compara referencias — el contenido es irrelevante.

```java
Gato g1 = new Gato("Michi", 3);
Gato g2 = new Gato("Michi", 3);  // mismos campos, objeto distinto en memoria

g1.equals(g2)  // → false  (distinta referencia)
g1 == g2       // → false
```

```java
Gato g3 = g1;  // misma referencia

g1.equals(g3)  // → true
g1 == g3       // → true
```

### Efecto en HashSet

```java
HashSet<Gato> set = new HashSet<>();
set.add(new Gato("Michi", 3));
set.add(new Gato("Michi", 3));  // mismo contenido, distinto objeto

set.size()  // → 2  (los trata como distintos)
```

Con `equals` + `hashCode` sobreescritos, `size()` devolvería `1`.

### Efecto en HashMap como clave

```java
HashMap<Gato, String> map = new HashMap<>();
Gato g1 = new Gato("Michi", 3);
map.put(g1, "gato gordo");

Gato g2 = new Gato("Michi", 3);  // mismo contenido, distinto objeto
map.get(g2)  // → null  (no lo encuentra)
map.get(g1)  // → "gato gordo"  (solo funciona con la misma referencia)
```

Con `equals` + `hashCode` sobreescritos, `map.get(g2)` devolvería `"gato gordo"`.

---

## Cuándo necesita cada colección

| Colección | Necesita | Motivo |
|---|---|---|
| `TreeSet` / `TreeMap` | `Comparable` o `Comparator` | Ordena elementos; sin él lanza `ClassCastException` en runtime |
| `HashSet` / `HashMap` | `equals` + `hashCode` | Los usa para buscar y detectar duplicados |
| `LinkedHashSet` / `LinkedHashMap` | `equals` + `hashCode` | Son Hash por debajo; el `Linked` solo añade orden de inserción |

> Si no sobreescribes en las colecciones Hash, no explota — pero compara por referencia y no detectará duplicados ni encontrará claves por contenido.

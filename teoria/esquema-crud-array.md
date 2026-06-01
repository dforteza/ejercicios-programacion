# CRUD básico con arrays — Esquema genérico

## Estructura de clases

| Clase        | Qué contiene                                              |
|--------------|-----------------------------------------------------------|
| `Entidad`    | Atributos del objeto + getters/setters + `toString()`     |
| `Repositorio`| `vector[]` (array de entidades) + `n` (elementos activos) |
| `Main`       | Menú + `switch` + llamadas al repositorio                 |

---

## Main

```
1. Crear Repositorio(tamaño)
2. do-while
     a. Mostrar menú → leer opción
     b. switch(opción) → llamar método del repositorio
     c. Mostrar resultado (boolean exito)
   while opción != salir
```

---

## Alta

```
1. n < vector.length ?  NO → return false
2. Pedir datos (Scanner)
3. vector[n] = new Entidad(datos)
4. n++
5. return true
```

---

## Baja

```
1. Pedir identificador
2. Buscar posición i  →  NO encontrado: return false
3. Desplazar: vector[i] = vector[i+1], vector[i+1] = vector[i+2], ...
4. n--
5. return true
```

> Sin el desplazamiento el array queda con un hueco nulo en medio.

---

## Modificar

```
1. Pedir identificador
2. Buscar posición i  →  NO encontrado: return false
3. Pedir nuevo valor
4. vector[i].setCampo(nuevoValor)
5. return true
```

---

## Incrementar campo numérico

```
1. Pedir identificador
2. Buscar posición i  →  NO encontrado: return false
3. Pedir cantidad
4. vector[i].addCampo(cantidad)   // campo += cantidad dentro del objeto
5. return true
```

---

## Listar (toString)

```
1. n == 0 ? → "vacío"
2. for i = 0 → n-1
     resultado += vector[i].toString()
     total += vector[i].getCampo()
3. resultado += "Total: " + total
4. return resultado
```

---

## Patrón de búsqueda (reutilizable)

```java
boolean exito = false;
for (int i = 0; i < n; i++) {
    if (vector[i].getIdentificador().equals(valorBuscado)) {
        // operar sobre vector[i]
        exito = true;
        break;
    }
}
return exito;
```


## 9. Apuntes prácticos de los ejercicios

### Clase `File` — patrones de los ejercicios 1-4

**Validación antes de operar:**
```java
if (f.exists() && f.isFile())       // fichero
if (f.exists() && f.isDirectory())  // directorio
```

**Ciclo de vida de un fichero:**
```java
f.createNewFile()  // devuelve false si ya existía — úsalo como condición
f.renameTo(dest)
f.delete()
```

**Recursividad sobre directorios** — dos patrones:
```java
// Acumular (tamaño total)
public static long calcularTamanio(File dir) {
    long total = 0;
    for (File f : dir.listFiles()) {
        if (f.isFile())      total += f.length();
        if (f.isDirectory()) total += calcularTamanio(f);
    }
    return total;
}

// Maximizar (profundidad máxima)
public static int calcularProfundidad(File dir) {
    int max = 0;
    for (File f : dir.listFiles()) {
        if (f.isDirectory()) {
            int p = 1 + calcularProfundidad(f);
            if (p > max) max = p;
        }
    }
    return max;
}
```

---

### Leer un fichero entero en memoria con `StringBuilder` (ejercicios 7, 8)

```java
StringBuilder sb = new StringBuilder();
String linea;
while ((linea = br.readLine()) != null)
    sb.append(linea).append("\n");
String texto = sb.toString();
```

Útil cuando necesitas procesar el texto completo de una pasada. `StringBuilder` modifica el mismo objeto en memoria — sin crear strings nuevos en cada iteración como haría `String +=`.

**Modificar texto en sitio:**
```java
sb.setCharAt(i, nuevoChar);  // reemplaza un carácter
sb.insert(i, " ");            // inserta desplazando lo que hay detrás
```

---

### Leer palabra a palabra con `Scanner` (ejercicio 9)

```java
Scanner sc = new Scanner(new File("fichero.txt"));
while (sc.hasNext())
    String palabra = sc.next();  // separa por espacios por defecto
```

Combinar dos ficheros hasta agotar ambos:
```java
while (scA.hasNext() || scB.hasNext()) {
    if (scA.hasNext()) bw.write(scA.next() + " ");
    if (scB.hasNext()) bw.write(scB.next() + " ");
}
```

---

### Fichero + colección + clase propia (ejercicios 6 y 10)

El patrón más completo:
1. Leer fichero línea a línea con `BufferedReader`
2. `split(";")` para partir cada línea en campos — **sin límite de partes**, nunca `split(";", 2)`
3. Crear objeto (`Contacto`, `Alumno`) con esos campos
4. Añadir a `ArrayList`
5. Operar sobre la colección (ordenar, calcular, mostrar)
6. Escribir la colección de vuelta al fichero al salir

**Ordenar la colección — dos formas:**
```java
// La clase implementa Comparable y define compareTo()
Collections.sort(lista);

// Con lambda sin tocar la clase
lista.sort((a, b) -> a.getNombre().compareTo(b.getNombre()));
```

---

### Manejo de excepciones

```java
// Opción 1 — delegar (válido para ejercicios)
public static void main(String[] args) throws Exception

// Opción 2 — capturar con traza completa
try { ... }
catch (Exception e) { e.printStackTrace(); }

// Multi-catch para distinguir tipos
catch (FileNotFoundException | IOException e) { ... }
```

`catch (Exception e)` captura cualquier excepción. El `catch` vacío es el peor caso — silencia errores sin avisar.

---

### Detalles importantes

- **`FileWriter` borra el fichero al abrirse.** Siempre: leer primero, abrir `FileWriter` al final.
- **Modo append:** `new FileWriter("fichero.txt", true)` añade sin borrar.
- **Cerrar en orden inverso** al de apertura: si abriste `FileReader` y luego `BufferedReader`, cierra `BufferedReader` primero.
- **Ruta relativa:** el directorio de trabajo es la carpeta del proyecto, no el workspace raíz. Si el programa no encuentra el fichero, verificar desde dónde ejecuta Java.

---

### Cifrado César (ejercicio 8)

```java
// Desplazar una letra con wrap al final del alfabeto
char cifrada = (char) ('a' + (c - 'a' + desplazamiento) % 26);

// Desencriptar = cifrar con el desplazamiento inverso
cifrar(texto, 26 - desplazamiento);
```

---

### Persistir una colección de objetos — 3 formas

#### Texto (`FileWriter` + `BufferedWriter` / `FileReader` + `BufferedReader`)

```java
// Escribir
for (Prenda p : prendas)
{
    bw.write(p.toStringPersist());
    bw.newLine();
}

// Leer
String linea;
while ((linea = br.readLine()) != null)
{
    String[] datos = linea.split(";");
    if (datos[0].equals("TIPO_A"))
        lista.add(new TipoA(datos[1], datos[2], ...));
    else
        lista.add(new TipoB(datos[1], datos[2], ...));
}
```

- `toStringPersist()` — método propio con separador fijo (`;`). Incluir el tipo como `datos[0]` si hay herencia.
- `LocalDate` → guardar con `.toString()`, leer con `LocalDate.parse(datos[n])`.
- `boolean` → guardar como `"true"`/`"false"`, leer con `Boolean.parseBoolean(datos[n])`.

---

#### Data (`FileOutputStream + DataOutputStream` / `FileInputStream + DataInputStream`)

```java
// Escribir
for (Objeto o : lista)
{
    dos.writeUTF(o.getCampo1());
    dos.writeUTF(o.getCampo2());
    // mismo orden siempre
}

// Leer
while (true)
{
    try
    {
        String campo1 = dis.readUTF();
        String campo2 = dis.readUTF();
        lista.add(new Objeto(campo1, campo2));
    }
    catch (EOFException e)
    {
        break;
    }
}
```

- Campo a campo, en el **mismo orden** que se escribió.
- `while(true)` + `catch(EOFException)` es el patrón correcto — no usar `available() > 0`.

---

#### Binario (`FileOutputStream + ObjectOutputStream` / `FileInputStream + ObjectInputStream`)

```java
// Escribir
oos.writeObject(lista);   // serializa la lista entera de golpe

// Leer
lista = (List<Objeto>) ois.readObject();   // deserializa la lista entera
```

- La clase debe implementar `Serializable`. Las subclases lo heredan.
- Es la forma más simple — no hay separadores ni parseo manual.

# Ficheros de texto (caracteres) en Java

Los ficheros de texto guardan datos como caracteres legibles. Java usa dos familias de clases para trabajar con ellos, que se combinan en capas:

- **`FileWriter` / `FileReader`** → abren el fichero, operan carácter a carácter
- **`BufferedWriter` / `BufferedReader`** → envuelven a los anteriores, añaden buffer y lectura por líneas
- **`PrintWriter`** → envuelve `FileWriter`, añade escritura formateada (`println`, `printf`)
- **`Scanner`** → puede apuntar a un `File`, añade lectura por tokens y tipos

---

## 1. FileWriter y FileReader

La capa base. Solo escriben y leen carácter a carácter — sin buffer, sin líneas, sin tipos.

### FileWriter — escritura

```java
FileWriter fw = new FileWriter("datos.txt");         // sobreescribe si existe
FileWriter fw = new FileWriter("datos.txt", true);   // true = append, añade al final

fw.write("Hola mundo");   // escribe un String
fw.write('\n');            // escribe un carácter suelto
fw.close();                // SIEMPRE cerrar — vuelca datos al disco
```

| Método | Qué hace |
|---|---|
| `write(String s)` | escribe una cadena |
| `write(int c)` | escribe un carácter (como int) |
| `close()` | cierra y vuelca al disco |

### FileReader — lectura

```java
FileReader fr = new FileReader("datos.txt");

int c;
while ((c = fr.read()) != -1)   // -1 = fin de fichero
    System.out.print((char) c); // cast a char obligatorio

fr.close();
```

| Método | Qué hace |
|---|---|
| `int read()` | lee un carácter como int, devuelve -1 al final |
| `close()` | cierra el stream |

> `read()` devuelve `int`, no `char`. Si quieres mostrarlo como letra necesitas el cast `(char) c`.

---

## 2. BufferedWriter y BufferedReader

Envuelven a `FileWriter`/`FileReader` y añaden un buffer en memoria. Menos accesos a disco y, en el caso del reader, lectura de líneas completas con `readLine()`.

### BufferedWriter — escritura con buffer

```java
FileWriter fw     = new FileWriter("datos.txt");
BufferedWriter bw = new BufferedWriter(fw);

bw.write("Primera línea");
bw.newLine();                 // salto de línea portable (\r\n en Windows, \n en Unix)
bw.write("Segunda línea");
bw.newLine();

bw.close();   // cierra BufferedWriter Y el FileWriter interno
fw.close();
```

| Método | Qué hace |
|---|---|
| `write(String s)` | escribe cadena en el buffer |
| `newLine()` | salto de línea según el SO |
| `flush()` | vuelca buffer a disco sin cerrar |
| `close()` | vuelca + cierra |

### BufferedReader — lectura con buffer

La gran ventaja es `readLine()` — lee una línea entera de golpe y devuelve `null` al llegar al final.

```java
FileReader fr     = new FileReader("datos.txt");
BufferedReader br = new BufferedReader(fr);

String linea;
while ((linea = br.readLine()) != null)   // null = fin de fichero
    System.out.println(linea);

br.close();
fr.close();
```

| Método | Qué hace |
|---|---|
| `String readLine()` | lee línea entera sin el `\n`, devuelve `null` al final |
| `int read()` | lee un carácter |
| `close()` | cierra stream y buffer |

> En Windows los saltos de línea son `\r\n`. `readLine()` los elimina automáticamente. Si lees con `FileReader` carácter a carácter y ves caracteres raros, es el `\r` — usa `.trim()` para quitarlo.

---

## 3. PrintWriter - ESCRITURA FORMATEADA

Envuelve `FileWriter` y AÑADE ESCRITURA DIRECTA DE CUALQUIER TIPO DE DATO (`int`, `double`, `boolean`...) y formateo con `printf`. Es la opción más cómoda para escribir.

```java
FileWriter fw  = new FileWriter("datos.txt");
PrintWriter pw = new PrintWriter(fw);

pw.println("Hola mundo");    // String con salto de línea
pw.println(42);              // int directamente
pw.println(3.14);            // double directamente
pw.printf("%.2f%n", 9.85);  // formato igual que System.out.printf

pw.close();
fw.close();
```

| Método | Qué hace |
|---|---|
| `print(x)` | escribe x sin salto de línea |
| `println(x)` | escribe x con salto de línea, ACEPTA CUALQUIER TIPO |
| `printf(formato, args)` | escritura formateada |
| `close()` | cierra el stream |

---

## 4. Scanner sobre fichero - LECTURA TOKENIZADA

`Scanner` no solo lee del teclado — acepta un `File` directamente. Lee por tokens (palabras separadas por espacios) o por líneas, y detecta el tipo del token.

```java
Scanner sc = new Scanner(new File("datos.txt"));

// Línea a línea
while (sc.hasNextLine())
    System.out.println(sc.nextLine());

// Token a token (palabra a palabra)
while (sc.hasNext())
    System.out.println(sc.next());

// Por tipo
while (sc.hasNextDouble())
    double n = sc.nextDouble();

sc.close();
```

| Método | Qué hace |
|---|---|
| `hasNextLine()` / `nextLine()` | comprueba / lee línea entera |
| `hasNext()` / `next()` | comprueba / lee siguiente token (palabra) |
| `hasNextInt()` / `nextInt()` | comprueba / lee siguiente int |
| `hasNextDouble()` / `nextDouble()` | comprueba / lee siguiente double |
| `close()` | cierra el stream |

---

## 5. ¿Cuándo usar cada clase?

| Necesidad | Clase |
|---|---|
| Escribir texto simple | `FileWriter` |
| Escribir con tipos o formato | `PrintWriter(new FileWriter(...))` |
| Escribir con buffer (ficheros grandes) | `BufferedWriter(new FileWriter(...))` |
| Leer línea a línea | `BufferedReader(new FileReader(...))` |
| Leer por tokens o por tipo | `Scanner(new File(...))` |
| Leer carácter a carácter | `FileReader` |

---

## 6. Resumen de capas

```
ESCRITURA
FileWriter("fichero.txt")              → base, carácter a carácter
  └── BufferedWriter                   → añade buffer y newLine()
  └── PrintWriter                      → añade println() y printf()

LECTURA
FileReader("fichero.txt")              → base, carácter a carácter  (fin = -1)
  └── BufferedReader                   → añade readLine()            (fin = null)
Scanner(new File("fichero.txt"))       → tokens y tipos              (fin = !hasNext())
```

---

## 7. Detalles importantes

**`FileWriter` borra el fichero al abrirse.** Si abres escritura antes de leer, pierdes el contenido. Orden correcto: leer primero, abrir `FileWriter` al final.

**Cerrar en orden inverso** al de apertura. Si abriste `FileReader` y luego `BufferedReader`, cierra `BufferedReader` primero.

**Patrón fichero + colección + clase propia:**
```
1. BufferedReader → readLine() → split(";") → new Objeto(campos) → lista.add()
2. Operar sobre la lista (ordenar, calcular, filtrar)
3. FileWriter → escribir lista al fichero al terminar
```

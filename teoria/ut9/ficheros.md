# UT9 — Ficheros en Java

---

## Índice

1. [Introducción](#1-introducción)
2. [Streams](#2-streams)
3. [Ficheros de texto: FileWriter y FileReader](#3-ficheros-de-texto-filewriter-y-filereader)
4. [Ficheros de texto: BufferedReader y BufferedWriter](#4-ficheros-de-texto-bufferedreader-y-bufferedwriter)
5. [La clase File](#5-la-clase-file)
6. [Ficheros binarios: FileInputStream y FileOutputStream](#6-ficheros-binarios-fileinputstream-y-fileoutputstream)
7. [Serialización](#7-serialización)
8. [Clases extra para los ejercicios: PrintWriter, Scanner, DataStreams](#8-clases-extra-para-los-ejercicios)

---

## 1. Introducción

**¿Por qué necesitamos ficheros?**

Toda la información que maneja un programa Java vive en la memoria RAM: variables, objetos, arrays... La RAM es **volátil**: cuando el programa termina, esa información desaparece.

Para que la información persista entre ejecuciones usamos **ficheros externos** (en disco duro, pendrive...). Java accede a ellos mediante **streams** (flujos de datos).

**Operaciones posibles sobre un fichero:**
- Creación
- Consulta / Recuperación
- Modificación / Actualización
- Borrado

**Tipos de acceso:**
- **Secuencial:** para llegar al registro n hay que leer los n-1 anteriores.
- **Directo:** se accede directamente a un registro por clave.

Java implementa todo esto en el paquete **`java.io`** → hay que importarlo: `import java.io.*;`

---

## 2. Streams

Un **stream** es un **PUENTE ENTRE EL PROGRAMA Y EL DISPOSITIVO DE ALMACENAMIENTO**. Es por donde fluye la información:

```
PROGRAMA  →  Stream  →  DISCO    (escritura / salida)
PROGRAMA  ←  Stream  ←  DISCO    (lectura  / entrada)
```

En Java existen 2 FAMILIAS de streams:

| Familia | Qué transporta | Clases base | Para qué |
|---|---|---|---|
| **Flujo de bytes** | bytes (0-255) | `InputStream` / `OutputStream` | ficheros binarios, imágenes, audio... |
| **Flujo de caracteres** | chars Unicode | `Reader` / `Writer` | ficheros de texto |

Regla práctica:
- texto → flujo de caracteres.
- Objetos/binarios → flujo de bytes.

---

## 3. Ficheros de texto: FileWriter y FileReader

> Son las clases base para leer y escribir ficheros de texto. Operan **carácter a carácter** y trabajan con Unicode automáticamente.

### FileWriter — escritura

```java
// Crea el fichero (o lo sobreescribe si ya existe)
FileWriter fw = new FileWriter("datos.txt");

// Append = true → añade al final sin borrar lo existente
FileWriter fw = new FileWriter("datos.txt", true);

fw.write("Hola mundo");   // escribe una cadena
fw.write('\n');            // escribe un carácter
fw.close();                // SIEMPRE cerrar al terminar
```

**Lanza `IOException`** → hay que envolver en try-catch.

**Métodos clave:**

| Método | Qué hace |
|---|---|
| `write(String s)` | escribe una cadena |
| `write(int c)` | escribe un carácter (como int) |
| `close()` | cierra el stream y libera recursos |

### FileReader — lectura

```java
FileReader fr = new FileReader("datos.txt");  // lanza FileNotFoundException

int c;
while ((c = fr.read()) != -1)   // -1 = fin de fichero
{
    System.out.print((char) c);
}
fr.close();
```

> **Nota importante:** `read()` devuelve un `int` con el código Unicode del carácter. Hay que hacer cast a `char` para mostrarlo como letra.

**Métodos clave:**

| Método | Qué hace |
|---|---|
| `int read()` | lee un carácter, devuelve -1 al llegar al final |
| `int skip(long n)` | salta n caracteres |
| `close()` | cierra el stream |

> **Regla de oro:** después de escribir un fichero hay que cerrarlo (`close()`) antes de abrirlo para lectura. Si no, los datos pueden no haberse volcado al disco.

---

## 4. Ficheros de texto: BufferedReader y BufferedWriter

### ¿Por qué usar un buffer?

Con `FileReader` cada `read()` puede acceder al disco directamente — muy lento con ficheros grandes. Un **buffer** es una **MEMORIA INTERMEDIA (RAM) que se llena de golpe con un bloque del fichero**; luego el programa lee del buffer, no del disco.

El buffer se rellena en RAM. Si hay un problema con el disco duro durante la lectura, la lectura no se interrumpe porque ya está en memoria.

### BufferedReader — lectura con buffer

```java
FileReader fr       = new FileReader("datos.txt");
BufferedReader br   = new BufferedReader(fr);

// También con tamaño de buffer personalizado:
// BufferedReader br = new BufferedReader(fr, 8192);

String linea;
while ((linea = br.readLine()) != null)  // null = fin de fichero
{
    System.out.println(linea);
}

br.close();  // cierra también el FileReader interno
```

**Métodos clave:**

| Método | Qué hace |
|---|---|
| `String readLine()` | lee la línea entera (sin el `\n`), devuelve `null` al final |
| `int read()` | lee un carácter |
| `close()` | cierra stream y buffer |

`readLine()` es la gran ventaja: en lugar de ir carácter a carácter, lees una línea completa de golpe. Lanza `IOException`.

### BufferedWriter — escritura con buffer

Los datos se acumulan en el buffer y se escriben al disco cuando el buffer se llena o cuando se llama a `close()`/`flush()`.

```java
FileWriter fw        = new FileWriter("datos.txt");
BufferedWriter bw    = new BufferedWriter(fw);

bw.write("Primera línea");
bw.newLine();               // salto de línea portable (\r\n en Windows, \n en Unix)
bw.write("Segunda línea");
bw.newLine();

bw.flush();   // opcional: vuelca el buffer sin cerrar
bw.close();   // vuelca + cierra (cierra también el FileWriter)
```

**Métodos clave:**

| Método | Qué hace |
|---|---|
| `write(String s)` | escribe cadena en el buffer |
| `newLine()` | escribe salto de línea según el SO |
| `flush()` | vuelca buffer a disco sin cerrar |
| `close()` | vuelca buffer, cierra y libera |

---

## 5. La clase File

**La clase `File` representa una RUTA dentro del sistema de ficheros.**

**Se puede usar tanto con rutas que existen como con rutas que aún no existen**. Sirve para ficheros, carpetas y directorios. Hay que instanciarla como cualquier clase.

```java
import java.io.File;

File f = new File(String ruta);
```

### Rutas absolutas vs relativas

**Ruta absoluta:** parte desde la raíz del sistema de ficheros.
- Windows: `C:/Usuarios/Diego/datos.txt` (Java acepta `/` en Windows)
- Unix: `/home/diego/datos.txt`

**Ruta relativa:** parte desde el **directorio de trabajo** de la aplicación (la carpeta del proyecto en VS Code). No incluye la raíz.

```java
File f1 = new File("datos.txt");              // carpeta actual del proyecto
File f2 = new File("recursos/datos.txt");     // subcarpeta
File f3 = new File("../otro/datos.txt");      // carpeta padre, luego subcarpeta
```

> **En los ejercicios usaremos siempre rutas relativas.** Son portables: el programa funciona en cualquier ordenador siempre que el fichero esté en la misma ruta relativa al proyecto.

**Separador portable:** Java tiene `File.separator` que usa automáticamente el separador del SO actual (`\` en Windows, `/` en Unix):

```java
String ruta = "C:" + File.separator + "Users" + File.separator + "Diego" + File.separator + "datos.txt";
```

Con rutas relativas y la barra `/` esto no es necesario — Java lo gestiona.

### Métodos de la clase File

#### Obtención de ruta

| Método | Devuelve | Qué hace |
|---|---|---|
| `getName()` | `String` | nombre del fichero/carpeta (sin la ruta) |
| `getParent()` | `String` | ruta de la carpeta padre |
| `getAbsolutePath()` | `String` | ruta absoluta completa |
| `getPath()` | `String` | ruta tal como se especificó al crear el objeto |

#### Comprobaciones de estado

| Método | Devuelve | Qué hace |
|---|---|---|º
| `exists()` | `boolean` | `true` si la ruta existe en disco |
| `isFile()` | `boolean` | `true` si existe y es un fichero |
| `isDirectory()` | `boolean` | `true` si existe y es una carpeta |

#### Propiedades del fichero

| Método | Devuelve | Qué hace |
|---|---|---|
| `length()` | `long` | tamaño en bytes (solo válido para ficheros) |
| `lastModified()` | `long` | milisegundos desde 01/01/1970 hasta última modificación |

Para mostrar `lastModified()` como fecha legible:
```java
long ms = f.lastModified();
Date fecha = new Date(ms);   // import java.util.Date
System.out.println(fecha);
```

#### Gestión de ficheros y carpetas

| Método | Devuelve | Qué hace |
|---|---|---|
| `createNewFile()` | `boolean` | crea el fichero vacío; `true` si se creó (lanza `IOException`) |
| `mkdir()` | `boolean` | crea la carpeta; `true` si se creó |
| `mkdirs()` | `boolean` | crea la carpeta y todas las intermedias necesarias |
| `delete()` | `boolean` | borra el fichero o carpeta vacía |
| `renameTo(File dest)` | `boolean` | mueve/renombra el fichero o carpeta |

> `delete()` sobre una carpeta solo funciona si está **vacía**.
> `renameTo()` no solo renombra — también mueve. El objeto `File` original es el origen, el argumento es el destino.

#### Listado de archivos

| Método | Devuelve | Qué hace |
|---|---|---|
| `listFiles()` | `File[]` | array con los elementos de la carpeta (ficheros y subcarpetas) |
| `list()` | `String[]` | igual pero devuelve solo los nombres |

> El orden del array es **aleatorio** (no alfabético ni por tipo). Si la carpeta está vacía devuelve `null`.

### Ejemplo: listar contenido de una carpeta

```java
File carpeta = new File("recursos");
File[] contenido = carpeta.listFiles();

for (int i = 0; i < contenido.length; i++)
{
    File f = contenido[i];
    if (f.isDirectory())
    {
        System.out.println("[DIR]  " + f.getName());
    }
    else
    {
        System.out.println("[ARCH] " + f.getName());
    }
}
```

### Ejemplo: crear y borrar

```java
// Crear un fichero
File nuevo = new File("prueba.txt");
try
{
    if (nuevo.createNewFile())
    {
        System.out.println("Fichero creado");
    }
    else
    {
        System.out.println("Ya existía");
    }
}
catch (IOException e)
{
    e.printStackTrace();
}

// Crear una carpeta
File carpeta = new File("miscarpetas/nueva");
carpeta.mkdirs();   // mkdirs crea también "miscarpetas" si no existe

// Borrar
nuevo.delete();

// Mover / renombrar
File origen  = new File("viejo.txt");
File destino = new File("nuevo_nombre.txt");
origen.renameTo(destino);
```

---

## 6. Ficheros binarios: FileInputStream y FileOutputStream

Todo fichero en disco, sea texto, imagen, audio o vídeo, es en realidad una **sucesión de bytes** (valores entre 0 y 255). Los streams de bytes permiten leer y escribir esos bytes directamente.

### FileInputStream — lectura de bytes

```java
FileInputStream fis = new FileInputStream("imagen.jpg");

int byteLeido;
while ((byteLeido = fis.read()) != -1)   // -1 = fin de fichero
{
    System.out.println(byteLeido);
}

fis.close();
```

`fis.read()` lee un byte y lo devuelve como `int`. Devuelve `-1` al terminar. Lanza `IOException`.

### FileOutputStream — escritura de bytes

```java
FileOutputStream fos = new FileOutputStream("copia.jpg");

fos.write(byteLeido);   // escribe un byte (int entre 0-255)

fos.close();
```

### Ejemplo completo: copiar un fichero binario

```java
try
{
    FileInputStream  entrada  = new FileInputStream("original.jpg");
    FileOutputStream salida   = new FileOutputStream("copia.jpg");

    int b;
    while ((b = entrada.read()) != -1)
    {
        salida.write(b);
    }

    entrada.close();
    salida.close();
}
catch (IOException e)
{
    e.printStackTrace();
}
```

> Para ficheros grandes esto es lento (un acceso a disco por byte). En la práctica se añade `BufferedInputStream`/`BufferedOutputStream` como capa intermedia, igual que con los de texto.

---

## 7. Serialización

- **Serializar** es convertir un objeto Java completo en una secuencia de bytes para guardarlo en disco o enviarlo por red.
- **Deserializar** es el proceso inverso: reconstruir el objeto desde esos bytes.

### Requisito: implementar `Serializable`

La clase del objeto **debe** implementar `java.io.Serializable`. Esta interfaz no tiene métodos ni campos — es solo una marca que le dice a la JVM que el objeto puede ser serializado.

```java
import java.io.Serializable;

class Empleado implements Serializable
{
    String nombre;
    double salario;

    public Empleado(String nombre, double salario)
    {
        this.nombre  = nombre;
        this.salario = salario;
    }
}
```

Si la clase tiene atributos que son objetos de otras clases, esas clases también deben implementar `Serializable`.

### Serializar (guardar) — ObjectOutputStream

```java
Empleado[] plantilla = new Empleado[2];
plantilla[0] = new Empleado("Ana", 1800);
plantilla[1] = new Empleado("Pepe", 2100);

try
{
    ObjectOutputStream flujoSalida = new ObjectOutputStream(
        new FileOutputStream("plantilla.dat")
    );

    flujoSalida.writeObject(plantilla);   // serializa el array entero
    flujoSalida.close();
}
catch (FileNotFoundException e)
{
    e.printStackTrace();
}
catch (IOException e)
{
    e.printStackTrace();
}
```

### Deserializar (recuperar) — ObjectInputStream

```java
try
{
    ObjectInputStream flujoEntrada = new ObjectInputStream(
        new FileInputStream("plantilla.dat")
    );

    Empleado[] recuperado = (Empleado[]) flujoEntrada.readObject();   // casting obligatorio
    flujoEntrada.close();

    for (Empleado e : recuperado)
    {
        System.out.println(e.nombre + " — " + e.salario);
    }
}
catch (Exception e)
{
    e.printStackTrace();
}
```

`readObject()` devuelve `Object` — siempre hay que hacer cast al tipo real.

### Métodos principales

| Clase | Método | Qué hace |
|---|---|---|
| `ObjectOutputStream` | `writeObject(Object obj)` | serializa y escribe el objeto |
| `ObjectInputStream` | `readObject()` | lee y devuelve el objeto (como `Object`) |

Para leer todos los objetos de un fichero hasta el final, captura `EOFException`:

```java
try
{
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("datos.dat"));

    while (true)
    {
        try
        {
            Empleado e = (Empleado) ois.readObject();
            System.out.println(e);
        }
        catch (EOFException eof)
        {
            break;
        }
    }

    ois.close();
}
catch (Exception e)
{
    e.printStackTrace();
}
```

---

## 8. Clases extra para los ejercicios

Estas clases no están en el PDF pero son necesarias para los ejercicios de texto.

### PrintWriter — escritura formateada

Más cómodo que `FileWriter` porque acepta cualquier tipo de dato directamente.

```java
// Escritura normal (sobreescribe)
PrintWriter pw = new PrintWriter(new FileWriter("datos.txt"));

// Append
PrintWriter pw = new PrintWriter(new FileWriter("datos.txt", true));

pw.print("sin salto");
pw.println("con salto");      // añade \n
pw.println(42);               // acepta int, double, boolean...
pw.close();
```

### Scanner — lectura tokenizada

Lee el fichero dividido en tokens (palabras/números separados por espacios y saltos de línea).

```java
Scanner scn = new Scanner(new File("datos.txt"));

// Línea a línea:
while (scn.hasNextLine())
{
    String linea = scn.nextLine();
    System.out.println(linea);
}

// Token a token:
while (scn.hasNext())
{
    String token = scn.next();
}

// Tokens tipados:
while (scn.hasNextInt())
{
    int n = scn.nextInt();
}

scn.close();
```

Lanza `FileNotFoundException`.

### DataOutputStream / DataInputStream — tipos primitivos en binario

Para guardar `int`, `double`, `String`... en formato binario (compacto, no legible por humanos).

```java
// Escritura
DataOutputStream out = new DataOutputStream(new FileOutputStream("datos.dat"));
out.writeUTF("Juan");       // String UTF-8
out.writeInt(42);           // 4 bytes
out.writeDouble(9.85);      // 8 bytes
out.close();

// Lectura — mismo orden y tipo que en la escritura
DataInputStream in = new DataInputStream(new FileInputStream("datos.dat"));
String nombre = in.readUTF();
int edad       = in.readInt();
double nota    = in.readDouble();
in.close();
```

> **Crítico:** leer siempre en el mismo orden y con el mismo tipo con que se escribió. El fichero no tiene etiquetas; si el orden no coincide, los datos serán basura.

---

## Resumen rápido — ¿qué clase uso?

```
FICHEROS DE TEXTO
├── Escritura básica       → FileWriter(ruta)
├── Escritura formateada   → PrintWriter(new FileWriter(ruta))
├── Escritura con buffer   → BufferedWriter(new FileWriter(ruta))
└── Lectura
    ├── Línea a línea      → BufferedReader(new FileReader(ruta))  →  readLine()
    └── Por tokens         → Scanner(new File(ruta))               →  next(), nextInt()...

FICHEROS BINARIOS
├── Byte a byte            → FileInputStream / FileOutputStream    →  read() / write()
└── Tipos primitivos       → DataInputStream / DataOutputStream    →  readInt/writeInt...

SERIALIZACIÓN (objetos completos)
├── Guardar                → ObjectOutputStream(new FileOutputStream(ruta))  →  writeObject()
└── Recuperar              → ObjectInputStream(new FileInputStream(ruta))    →  (Tipo) readObject()
    └── La clase debe implementar Serializable

SISTEMA DE FICHEROS (sin leer/escribir datos)
└── File(ruta)  →  exists, isFile, isDirectory, mkdir, delete, renameTo, listFiles...
```

## Excepciones importantes

| Excepción | Cuándo ocurre |
|---|---|
| `FileNotFoundException` | el fichero no existe al abrirlo |
| `IOException` | error de E/S general (incluye la anterior) |
| `EOFException` | fin de fichero al deserializar con ObjectInputStream |
| `ClassNotFoundException` | al deserializar si la clase no se encuentra |

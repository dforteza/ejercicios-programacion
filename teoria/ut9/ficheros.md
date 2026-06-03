# UT9 — Ficheros en Java

---

## Índice

1. [Introducción](#1-introducción)
2. [Streams y familias](#2-streams-y-familias)
3. [La clase File](#3-la-clase-file)

> Para las clases de lectura y escritura ver:
> - `ficheros_caracteres.md` → FileWriter, FileReader, BufferedWriter, BufferedReader, PrintWriter, Scanner
> - `ficheros_binarios.md` → DataOutputStream, DataInputStream, ObjectOutputStream, ObjectInputStream

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

## 2. Streams y familias

Un **stream** es un **puente entre el programa y el dispositivo de almacenamiento**. Es por donde fluye la información:

```
PROGRAMA  →  Stream  →  DISCO    (escritura / salida)
PROGRAMA  ←  Stream  ←  DISCO    (lectura  / entrada)
```

En Java existen **2 familias** de streams:

| Familia | Qué transporta | Clases base | Para qué |
|---|---|---|---|
| **Flujo de bytes** | bytes (0-255) | `InputStream` / `OutputStream` | ficheros binarios, imágenes, audio... |
| **Flujo de caracteres** | chars Unicode | `Reader` / `Writer` | ficheros de texto |

Regla práctica:
- Texto → flujo de **caracteres** → `FileReader` / `FileWriter` y sus derivados
- Objetos / binarios → flujo de **bytes** → `FileInputStream` / `FileOutputStream` y sus derivados

Las clases concretas se construyen en **capas** — cada capa envuelve a la anterior y añade funcionalidad:

```
TEXTO (caracteres)
FileWriter / FileReader                → base, carácter a carácter
  └── BufferedWriter / BufferedReader  → añade buffer y readLine()
  └── PrintWriter                      → añade println() y printf()
  └── Scanner(new File(...))           → lectura por tokens y tipos

BINARIO (bytes)
FileOutputStream / FileInputStream    → base, byte a byte
  └── DataOutputStream / DataInputStream    → añade writeInt, readUTF...
  └── ObjectOutputStream / ObjectInputStream → añade writeObject / readObject
```

---

## 3. La clase File

`File` representa una **ruta** dentro del sistema de ficheros. No es el contenido del fichero — es solo la dirección. Se puede crear el objeto aunque el fichero no exista todavía.

```java
import java.io.File;

File f = new File("ruta/al/fichero.txt");
```

### Rutas absolutas vs relativas

**Ruta absoluta** — parte desde la raíz del sistema:
```java
File f = new File("C:/Users/Diego/datos.txt");  // Windows
File f = new File("/home/diego/datos.txt");      // Unix
```

**Ruta relativa** — parte desde la carpeta del proyecto (directorio de trabajo):
```java
File f1 = new File("datos.txt");           // carpeta raíz del proyecto
File f2 = new File("recursos/datos.txt"); // subcarpeta
```

> En los ejercicios siempre rutas relativas. El directorio de trabajo en VS Code es la carpeta del proyecto que tienes abierto, no el workspace raíz.

---

### Métodos de comprobación

| Método | Devuelve | Qué hace |
|---|---|---|
| `exists()` | `boolean` | `true` si la ruta existe en disco |
| `isFile()` | `boolean` | `true` si existe y es un fichero |
| `isDirectory()` | `boolean` | `true` si existe y es una carpeta |

Patrón de validación antes de operar:
```java
if (f.exists() && f.isFile())       { /* es un fichero válido */ }
if (f.exists() && f.isDirectory())  { /* es un directorio válido */ }
```

---

### Métodos de información

| Método | Devuelve | Qué hace |
|---|---|---|
| `getName()` | `String` | nombre del fichero/carpeta sin la ruta |
| `getAbsolutePath()` | `String` | ruta absoluta completa |
| `length()` | `long` | tamaño en bytes (solo ficheros) |
| `lastModified()` | `long` | milisegundos desde 01/01/1970 |

---

### Métodos de gestión

| Método | Devuelve | Qué hace |
|---|---|---|
| `createNewFile()` | `boolean` | crea fichero vacío; `false` si ya existía |
| `mkdir()` | `boolean` | crea la carpeta |
| `mkdirs()` | `boolean` | crea la carpeta y todas las intermedias |
| `delete()` | `boolean` | borra fichero o carpeta vacía |
| `renameTo(File dest)` | `boolean` | renombra o mueve |
| `listFiles()` | `File[]` | contenido de la carpeta (ficheros y subcarpetas) |

> `createNewFile()` lanza `IOException` — hay que envolver en try-catch.
> `listFiles()` devuelve `null` si la carpeta está vacía — comprueba antes de iterar.
> `delete()` sobre carpeta solo funciona si está vacía.

---

### Ejemplo: crear y gestionar

```java
File f = new File("datos.txt");

if (f.createNewFile())
    System.out.println("Creado");
else
    System.out.println("Ya existía");

f.renameTo(new File("datos_backup.txt"));  // renombrar
f.delete();                                 // borrar
```

### Ejemplo: recorrer un directorio

```java
File dir = new File("recursos");

if (dir.exists() && dir.isDirectory())
{
    for (File f : dir.listFiles())
    {
        if (f.isDirectory())
            System.out.println("[DIR]  " + f.getName());
        else
            System.out.println("[ARCH] " + f.getName() + " (" + f.length() + " bytes)");
    }
}
```

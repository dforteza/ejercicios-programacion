# Ficheros binarios en Java

Un fichero binario guarda los datos en formato binario (bytes), no como texto legible. La ventaja es que ocupa menos espacio y es más eficiente para guardar números y objetos.

En Java hay **dos formas de trabajar con ficheros binarios** dependiendo de lo que quieras guardar:

- **Con `DataOutputStream` / `DataInputStream`** → para GUARDAR CAMPOS SUELTOS (un String, un int, un double...)
- **Con `ObjectOutputStream` / `ObjectInputStream`** → para GUARDAR OBJETOS COMPLETOS O COLECCIONES

---

## 1. DataOutputStream y DataInputStream

### ¿Cuándo usarlos?

Cuando quieres guardar registros campo a campo. Por ejemplo, para cada jugador guardas su nombre y su puntuación por separado. El fichero queda como una secuencia de valores uno detrás de otro.

### Cómo funciona por dentro

El fichero almacena los valores en bruto sin separadores ni etiquetas. Si guardas `"Ana"` y `1500`, el fichero contiene los bytes de ese String seguidos de los 4 bytes del entero. No hay nada que indique dónde termina un campo y empieza el siguiente — el programa sabe cómo leerlo porque siempre lee en el mismo orden que escribió.

---

### DataOutputStream — escritura

`DataOutputStream` se construye envolviendo un `FileOutputStream`. El `FileOutputStream` abre el fichero, y `DataOutputStream` añade los métodos para escribir tipos concretos.

```java
FileOutputStream fos = new FileOutputStream("datos.dat");
DataOutputStream dos = new DataOutputStream(fos);

dos.writeUTF("Ana");      // escribe el String "Ana"
dos.writeInt(1500);       // escribe el número 1500 en 4 bytes
dos.writeDouble(9.85);    // escribe el decimal en 8 bytes
dos.writeBoolean(true);   // escribe el booleano en 1 byte

dos.close();
fos.close();
```

Si quieres añadir al final sin borrar lo que ya hay, abre el `FileOutputStream` con append:

```java
FileOutputStream fos = new FileOutputStream("datos.dat", true);  // true = append
DataOutputStream dos = new DataOutputStream(fos);
```

**Métodos de escritura:**

| Método | Qué guarda |
|---|---|
| `writeUTF(String s)` | un String en formato binario |
| `writeInt(int n)` | un entero en 4 bytes |
| `writeDouble(double d)` | un decimal en 8 bytes |
| `writeBoolean(boolean b)` | un booleano en 1 byte |
| `close()` | cierra el stream y vuelca los datos al disco |

---

### DataInputStream — lectura

`DataInputStream` se construye envolviendo un `FileInputStream`. Lee los valores en el mismo orden y con el mismo tipo que se escribieron — si no respetas el orden, los datos serán basura.

**EL PROBLEMA ES QUE NO HAY FORMA DIRECTA DE SABER DONDE TERMINA EL FICHERO.**

Por eso el patrón es un bucle infinito `while(true)` que intenta leer, y cuando no hay más datos el método lanza una `EOFException` (End Of File). Ese es el momento de salir del bucle.

```java
FileInputStream fis = new FileInputStream("datos.dat");
DataInputStream dis = new DataInputStream(fis);

while (true)
{
    try
    {
        String nombre     = dis.readUTF();   // lee el String
        int    puntuacion = dis.readInt();   // lee el int
        System.out.println(nombre + " — " + puntuacion);
    }
    catch (EOFException eof)
    {
        break;  // no hay más datos, salir del bucle
    }
}

dis.close();
fis.close();
```

Fíjate que hay dos niveles de try-catch: el `catch(EOFException)` está dentro del bucle para capturar solo el fin de fichero. El `catch(Exception)` exterior capturaría cualquier otro error (fichero no encontrado, etc.).

**Métodos de lectura:**

| Método | Qué lee |
|---|---|
| `readUTF()` | un String |
| `readInt()` | un entero |
| `readDouble()` | un decimal |
| `readBoolean()` | un booleano |
| `close()` | cierra el stream |

---

### Patrón completo con Data — ejemplo jugador

```java
// ESCRIBIR
FileOutputStream fos = new FileOutputStream("puntuaciones.dat", true);
DataOutputStream dos = new DataOutputStream(fos);
dos.writeUTF("Ana");
dos.writeInt(1500);
dos.close();
fos.close();

// LEER
FileInputStream fis = new FileInputStream("puntuaciones.dat");
DataInputStream dis = new DataInputStream(fis);
while (true)
{
    try
    {
        String nombre     = dis.readUTF();
        int    puntuacion = dis.readInt();
        System.out.println(nombre + " — " + puntuacion);
    }
    catch (EOFException eof) { break; }
}
dis.close();
fis.close();
```

---

## 2. ObjectOutputStream y ObjectInputStream

### ¿Cuándo usarlos?

Cuando quieres guardar un objeto completo o una colección entera de golpe, sin tener que escribir campo a campo. Java convierte el objeto entero a bytes automáticamente — esto se llama **serialización**.

### Requisito: LA CLASE DEBE IMPLEMENTAR `Serializable`

Para que Java pueda serializar un objeto, su clase debe implementar la interfaz `Serializable`. Esta interfaz no tiene métodos — es solo una marca que le dice a Java que ese objeto puede convertirse a bytes.

```java
import java.io.Serializable;

public class Producto implements Serializable
{
    private int    codigo;
    private String nombre;
    private double precio;

    // constructor, getters, setters...
}
```

Si la clase tiene atributos que son objetos de otras clases, esas clases también deben implementar `Serializable`.

---

### ObjectOutputStream — serialización (guardar)

```java
List<Producto> lista = new ArrayList<>();
lista.add(new Producto(1, "Teclado", 49.99));
lista.add(new Producto(2, "Raton", 25.00));

FileOutputStream fos = new FileOutputStream("productos.dat");
ObjectOutputStream oos = new ObjectOutputStream(fos);

oos.writeObject(lista);   // guarda la lista entera de golpe

oos.close();
fos.close();
```

**Métodos:**

| Método | Qué hace |
|---|---|
| `writeObject(Object o)` | serializa el objeto y lo escribe en el fichero |
| `close()` | cierra el stream |

---

### ObjectInputStream — deserialización (recuperar)

`readObject()` devuelve siempre **`Object` — hay que hacer cast al tipo real**. Si el tipo no coincide lanza `ClassCastException`.

```java
FileInputStream fis = new FileInputStream("productos.dat");
ObjectInputStream ois = new ObjectInputStream(fis);

List<Producto> lista = (List<Producto>) ois.readObject();  // cast obligatorio

ois.close();
fis.close();
```

**Métodos:**

| Método | Qué hace |
|---|---|
| `readObject()` | lee y devuelve el objeto como `Object` (cast obligatorio) |
| `close()` | cierra el stream |

---

### Patrón completo con Object — ejemplo productos

```java
// GUARDAR
FileOutputStream fos = new FileOutputStream("productos.dat");
ObjectOutputStream oos = new ObjectOutputStream(fos);
oos.writeObject(lista);
oos.close();
fos.close();

// RECUPERAR
FileInputStream fis = new FileInputStream("productos.dat");
ObjectInputStream ois = new ObjectInputStream(fis);
List<Producto> lista = (List<Producto>) ois.readObject();
ois.close();
fis.close();
```

---

## 3. Patrón actualizar — cómo modificar un registro

En un fichero binario secuencial no puedes modificar un registro en mitad del fichero directamente. La única forma es:

```
1. Leer todos los registros del fichero a un ArrayList en memoria
2. Buscar y modificar el registro que corresponde en la lista
3. Reescribir el fichero entero desde cero con los datos actualizados
```

Este patrón es igual tanto con Data como con Object:

```java
// 1 - Leer todos a memoria
List<Jugador> lista = leerTodos();

// 2 - Modificar en memoria
for (Jugador j : lista)
    if (j.getNombre().equals(nombre))
        j.setPuntuacion(nuevaPuntuacion);

// 3 - Reescribir sin append (sobreescribe)
FileOutputStream fos = new FileOutputStream("datos.dat");  // sin true
DataOutputStream dos = new DataOutputStream(fos);
for (Jugador j : lista)
{
    dos.writeUTF(j.getNombre());
    dos.writeInt(j.getPuntuacion());
}
dos.close();
fos.close();
```

---

## 4. ¿Cuándo usar Data y cuándo usar Object?

| Criterio | Data | Object |
|---|---|---|
| Qué guardas | Campos sueltos (String, int...) | Objetos completos o colecciones |
| Requiere Serializable | No | Sí |
| Flexibilidad | Debes respetar el orden al leer | Java lo gestiona automáticamente |
| Cuándo usarlo | El enunciado dice DataOutputStream | El enunciado dice ObjectOutputStream o pide serialización |

---

## 5. Resumen de capas

```
ESCRITURA
FileOutputStream("fichero.dat")          → abre el fichero en modo bytes
  └── DataOutputStream                   → añade writeUTF, writeInt...
  └── ObjectOutputStream                 → añade writeObject()

LECTURA
FileInputStream("fichero.dat")           → abre el fichero en modo bytes
  └── DataInputStream                    → añade readUTF, readInt...  (EOF = EOFException)
  └── ObjectInputStream                  → añade readObject()         (cast obligatorio)
```

---

## 6. Excepciones relevantes

| Excepción | Cuándo ocurre |
|---|---|
| `FileNotFoundException` | el fichero no existe al intentar abrirlo |
| `IOException` | error genérico de entrada/salida |
| `EOFException` | fin de fichero al leer con DataInputStream |
| `ClassNotFoundException` | al deserializar con ObjectInputStream si la clase no se encuentra |

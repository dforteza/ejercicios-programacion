# Herencia y excepciones en Java

## Índice
1. [Herencia](#1-herencia)
2. [Sobreescritura de métodos](#2-sobreescritura-de-métodos)
3. [Polimorfismo](#3-polimorfismo)
4. [Clase Object](#4-clase-object)
5. [Clases abstractas](#5-clases-abstractas)
6. [Interfaces](#6-interfaces)
7. [Tipos enumerados (enum)](#7-tipos-enumerados-enum)
8. [Comparar objetos: equals y hashCode](#8-comparar-objetos-equals-y-hashcode)

---

## 1. Herencia

**Definición general:**
> CAPACIDAD DE UNA CLASE
```
Una CLASE HIJA HEREDA
- atributos 
- métodos no privados

de su clase padre.  
Una clase solo puede extender **una** clase padre, pero puede haber múltiples niveles de herencia.

```
### Palabras reservadas

| Palabra | Uso |
|---|---|
| `extends` | Indica de qué clase hereda la hija |
| `protected` | Visibilidad solo accesible desde clases hijas |
| `super` | Referencia a la clase padre. Llamar a su constructor debe ser la **primera línea** |

```java
public class Estudiante extends Persona 
{
    private int creditos;
    
    public Estudiante(String nombre, int edad) 
    {
        super(nombre, edad);   // llama al constructor de Persona
        this.creditos = 60;
    }
}
```

---

## 2. Sobreescritura de métodos

2 tipos:

- **Completa**: la hija implementa el método desde cero.
- **Parcial**: la hija usa `super.metodo()` para reutilizar la lógica del padre y añade la suya.

```java
// Persona (sobreescritura completa de Object)
public String toString()
{
    return "Nombre:" + nombre + " Edad:" + edad;
}

// Estudiante (sobreescritura parcial de Persona)
public String toString()
{
    return super.toString() + " Cred:" + creditos;
}
```

---

## 3. Polimorfismo

**Definición general:**

> CAPACIDAD DE UN METODO

de comportarse de forma diferente según:
- objeto
- contexto <-> atributos

que lo invocan

Tiene dos variantes:

| Variante | Definición | Se resuelve en |
|---|---|---|
| **Sobrecarga** | Mismo nombre de método, distintos parámetros en la misma clase. Java decide cuál ejecutar según los tipos y cantidad de parámetros. | Compilación |
| **Sobrescritura** | La clase hija REDEFINE un método del padre. El mismo método se comporta diferente según el objeto que lo ejecuta. | Ejecución |

> La sobrescritura es el POLIMORFISMO REAL de POO

> La sobrecarga es un MECANISMO DE CONVENIENCIA del lenguaje.

### Sobrecarga

```java
void suma(int a, int b)       { ... }
void suma(double a, double b)  { ... }
void suma(int a, int b, int c) { ... }
// Java elige cuál ejecutar según los parámetros que le pasas
```

### Sobrescritura

```java
// Misma llamada, distinto comportamiento según el objeto real
ArrayList<Animal> animales = new ArrayList<>();

animales.add(new Animal());
animales.add(new Perro());
animales.add(new Pitbull());

for (Animal a : animales)
    a.hacerRuido(); // cada objeto ejecuta su propia versión del método
```

---

## 4. Clase Object

>Toda clase en Java hereda *(implícitamente <-> Java lo hace por ti)* de `Object`. 


Sus métodos más importantes:

| Método | Qué hace |
|---|---|
| `toString()` | Representación en String del objeto |
| `equals(Object obj)` | Compara por contenido (si se sobreescribe) |
| `hashCode()` | Valor numérico único del objeto |
| `getClass()` | Devuelve la clase del objeto |
| `clone()` | Copia del objeto |

---

## 5. Clases abstractas

**Definición general:**

> Clase que **( no se puede instanciar <-> aplicar operador new )** directamente — solo heredar.  
Define la estructura general; las hijas implementan los detalles.

- *REQUISITO* -> Debe tener al menos un **( método abstracto <-> sin cuerpo )** .
- Las clases hijas están **obligadas** a implementar todos sus métodos abstractos.
- Las hijas pueden llamar a su constructor con `super()`.

```java
public abstract class Figura2D 
{
    private int numeroLados;
    
    public Figura2D(int numeroLados)
    { 
        this.numeroLados = numeroLados;
    }
    
    public abstract double area();  // sin cuerpo — cada hija lo define
}

public class Triangulo extends Figura2D
{
    public Triangulo(...) 
    {
        super(3); 
        ...
    }
    
    public double area() { /* fórmula del triángulo */ }
}
```

---

## 6. Interfaces

Java no permite herencia múltiple de clases. Las **interfaces** son la alternativa.  
> Son un **contrato**: definen **qué debe hacer una clase**, no cómo.

- Todos los métodos son **abstractos** (públicos por defecto).
- Todos los atributos son *`static final`* (constantes).
- Una clase puede implementar **múltiples** interfaces.
- Una interfaz puede extender otra interfaz con `extends`.

```java
public interface Forma
{
    double area();
    void dibujar();
}

public class Rectangulo implements Forma 
{
    @Override public double area() { return ancho * largo; }
    @Override public void dibujar() { System.out.println("Rectángulo"); }
}

// Implementación múltiple
public class Cuadrado implements Forma, Color { ... }
```

### Interfaz vs Clase abstracta

| | Clase abstracta | Interfaz |
|---|---|---|
| Atributos | Cualquier tipo | Solo constantes (`static final`) |
| Métodos | Puede tener implementados | Solo abstractos (salvo `default`) |
| Herencia | Solo una | Múltiples |
| Instanciar | No | No |

---

## 7. Tipos enumerados (enum)

> Define un *conjunto fijo* de *valores constantes*.  

> No se pueden crear instancias con `new`.

(VALOR) => IMPLICA:
1. atributo PRIVATE
2. constructor
3. GETTER de atr

```java
public enum DiaSemana { LUNES, MARTES, MIÉRCOLES, JUEVES, VIERNES, SÁBADO, DOMINGO }

// Con campos y métodos
public enum Mes 
{
    ENERO(31),
    FEBRERO(28),
    MARZO(31); // ...
    
    private final int dias;
    
    Mes(int dias)
    {
        this.dias = dias;
    }
    
    public int getDias() { return dias; }
}
```

```java
// Uso
Mes mesActual = Mes.ENERO;
System.out.println(mesActual.getDias()); // 31

// Recorrer todos los valores
for (DiaSemana d : DiaSemana.values())
    System.out.println(d);

// Convertir String → enum
Mes m = Mes.valueOf("ABRIL"); // lanza excepción si el valor no existe
```

---

## 8. Comparar objetos: equals y hashCode

Por defecto `==` compara **referencias** (si apuntan a la misma dirección de memoria), no contenido.  
Para comparar por contenido hay que sobrescribir `equals()`.

### equals()

```java
public boolean equals(Object obj) 
{
    if (obj == null) return false;
    if (obj == this) return true;
    if (getClass() != obj.getClass()) return false;
    Punto2D otro = (Punto2D) obj;

    return x == otro.x && y == otro.y;
}
```

> Sin sobrescribir: `p1.equals(p2)` → `false` aunque tengan los mismos valores.  
> Con sobrescribir: → `true` y `lista.remove(new Punto2D(0,0))` funciona correctamente.

### hashCode()

Valor numérico que identifica el objeto en colecciones (`HashMap`, `HashSet`).  
**Regla:** si `equals()` devuelve `true` entre dos objetos, su `hashCode()` debe ser igual.

```java
public int hashCode() 
{
    final int prime = 31;
    int result = 1;
    result = prime * result + x;
    result = prime * result + y;

    return result;
}
```

> Para `double` usar `Double.compare()` en lugar de `==` por problemas de precisión en punto flotante.

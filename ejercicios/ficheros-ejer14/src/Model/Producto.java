package Model;

import java.io.Serializable;
import java.util.Scanner;

public class Producto implements Serializable
{
    private int     id;
    private String  nombre;
    private float   precio;
    private int     cantidad;

    private static int secuencia = 0;

    public Producto(String nombre, float precio, int cantidad)
    {
        this.id = ++secuencia;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    
    public Producto(Scanner sc) 
    {
        this.id = ++secuencia;
        System.out.print("Nombre:");
        this.nombre = sc.nextLine();

        System.out.print("Precio:");
        this.precio = sc.nextFloat();
        sc.nextLine();
        
        System.out.print("Cantidad:");
        this.cantidad = sc.nextInt();
        sc.nextLine();
    }


    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public float getPrecio() {
        return precio;
    }
    public int getCantidad() {
        return cantidad;
    }

    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString()
    {
        return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad + "]";
    }

    
}

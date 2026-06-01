package Principal;

public class Producto 
{
    String  nombre;
    int     cantidad;
    public Producto(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return "Producto [nombre=" + nombre + ", cantidad=" + cantidad + "]";
    }
    
}

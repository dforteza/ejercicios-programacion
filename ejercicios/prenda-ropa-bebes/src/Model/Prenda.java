package Model;

import java.io.Serializable;

public class Prenda implements Serializable
{
    private String nombre;
    private String talla;
    private String color;
    private String composicion;
    private String descripcion;

    public Prenda(String nombre, String talla, String color, String composicion, String descripcion) {
        this.nombre = nombre;
        this.talla = talla;
        this.color = color;
        this.composicion = composicion;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTalla() {
        return talla;
    }

    public String getColor() {
        return color;
    }

    public String getComposicion() {
        return composicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean contieneTexto(String literal)
    {
        return nombre.contains(literal)
            || talla.contains(literal)
            || color.contains(literal)
            || composicion.contains(literal)
            || descripcion.contains(literal);
    }

    public String toStringPersist()
    {
        return "PRENDA;" + nombre + ";" + talla + ";" + color + ";" + composicion + ";" + descripcion;
    }

    @Override
    public String toString() 
    {
        String res = "";

        res += this instanceof PrendaDeportiva ? "Prenda Deportiva: " : "Prenda: ";

        res += nombre + " - " + talla + " - " + color + " - " + composicion
                + " - " + descripcion;

        return (res);
    }

    
    
}

package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Mascota implements Comparable<Mascota>, Serializable
{
    private String      nombre;
    private String      especie;
    private String      descripcion;    
    private LocalDate   fechaDesaparicion;
    private String      tlf;
    
    private final DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public Mascota(String nombre, String especie, String descripcion, LocalDate fechaDesaparicion, String tlf) {
        this.nombre = nombre;
        this.especie = especie;
        this.descripcion = descripcion;
        this.fechaDesaparicion = fechaDesaparicion;
        this.tlf = tlf;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaDesaparicion() {
        return fechaDesaparicion;
    }

    public void setFechaDesaparicion(LocalDate fechaDesaparicion) {
        this.fechaDesaparicion = fechaDesaparicion;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((tlf == null) ? 0 : tlf.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Mascota))
            return false;

        Mascota other = (Mascota) obj;

        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (tlf == null) {
            if (other.tlf != null)
                return false;
        } else if (!tlf.equals(other.tlf))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return nombre + " - " + especie + " - " + descripcion + " - " + fechaDesaparicion.format(fmt1) + " - " + tlf;
    }

    @Override
    public int compareTo(Mascota o) 
    {
        return (nombre.compareTo(o.getNombre()));
    }

    

    
}

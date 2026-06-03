public class Alumno implements Comparable<Alumno>
{
    String nombre;
    Double media;
    public Alumno(String nombre, Double media) 
    {
        this.nombre = nombre;
        this.media = media;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getMedia() {
        return media;
    }
    public void setMedia(Double media) {
        this.media = media;
    }
    @Override
    public String toString() {
        return "Alumno [nombre=" + nombre + ", media=" + media + "]";
    }

    @Override
    public int compareTo(Alumno o) 
    {
        return this.getNombre().compareTo(o.getNombre());
    }

    
    
}

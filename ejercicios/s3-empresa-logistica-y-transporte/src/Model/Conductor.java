package Model;

public class Conductor 
{
    private int id;
    private String nombre;


    private static int secuencia = 0;


    public Conductor(String nombre) {
        this.id = ++secuencia;
        this.nombre = nombre;
    }


    public int getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }


    public static int getSecuencia() {
        return secuencia;
    }


    @Override
    public String toString() 
    {
        return (nombre);
    }

    
    
    
}

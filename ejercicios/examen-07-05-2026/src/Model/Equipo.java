package Model;

import java.io.Serializable;
import java.util.Scanner;

public class Equipo implements Comparable<Equipo>, Serializable
{
    private int     codigo;
    private String  nombre;
    private int     puntuacion;

    private static int secuencia = 0;

    
    public Equipo(String nombre, int puntuacion) 
    {
        this.codigo = ++secuencia;
        this.nombre = nombre;
        this.puntuacion = puntuacion;
    }


    public Equipo(Scanner sc) 
    {
        this.codigo = ++secuencia;

        System.out.print("> Nombre:");
        this.nombre = sc.nextLine();
        System.out.print("> Puntuacion:");
        this.puntuacion = sc.nextInt();
        sc.nextLine();
    }

    public boolean contiene(String name)
    {
        if (nombre.contains(name))
            return (true);
        return (false);
    }

    public int getCodigo() {
        return codigo;
    }


    public String getNombre() {
        return nombre;
    }


    public int getPuntuacion() {
        return puntuacion;
    }


    public static int getSecuencia() {
        return secuencia;
    }

    public static void setSecuencia(int valor) {
        secuencia = valor;
    }

    @Override
    public int compareTo(Equipo o) {
        return (Integer.compare(o.getPuntuacion(), puntuacion));
    }


    @Override
    public String toString() 
    {
        String res = "";

        res += "Equipo: " + codigo + "," + nombre + "," + puntuacion ;
        
        return (res);
    }


    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + puntuacion;
        return result;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Equipo))
            return false;
        Equipo other = (Equipo) obj;

        return (this.nombre.equals(other.getNombre()));
    }


    
    
    
    
}

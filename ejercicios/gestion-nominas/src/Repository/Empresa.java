package Repository;

import Model.Trabajador;

public class Empresa 
{
    Trabajador[]    arr;
    Integer         n;

    String          cif;
    String          nombre;

    public Empresa(String cif, String nombre, Integer nMax) 
    {
        this.arr = new Trabajador[nMax];
        this.n = 0;

        this.cif = cif;
        this.nombre = nombre;
    }

    public Boolean alta(Trabajador t)
    {
        if (n >= arr.length)
            return (false);

        arr[n] = t;
        n++;

        System.out.println("Trabajador "+t.getNombre()+ " añadido");

        return (true);
    }

    @Override
    public String toString() 
    {
        String res = "";

        res += "EMPRESA"+ nombre + " - CIF" + cif+ "\n";
        res += "=================================\n ";
        for (int i = 0; i < n; i++)
        {
            res += arr[i].toString();
            res += "\n";
        }

        return (res);
    }

    

    
}

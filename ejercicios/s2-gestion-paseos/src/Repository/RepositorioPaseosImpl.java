package Repository;

import java.util.Scanner;

import Model.Paseo;

public class RepositorioPaseosImpl implements RepositorioPaseos
{
    private Paseo[] arr;
    private int n;


    public RepositorioPaseosImpl(int nMax) 
    {
        this.arr = new Paseo[nMax];
        this.n = 0;
    }


    @Override
    public boolean altaPaseo(Scanner sc)
    {
        if (n >= arr.length)
        {
            System.out.println("Error - no hay espacio");        
            return (false);
        }
        this.arr[n] = new Paseo(sc);
        n++;

        return (true);
    }

    public void altaPaseo(Paseo p)
    {
        if (n >= arr.length)
        {
            System.out.println("Error - no hay espacio");
            return ;
        }

        this.arr[n] = p;
        n++;

    }


    @Override
    public boolean bajaPaseo(int id) 
    {
        boolean flag = false; 
        for (int i = 0; i < n; i++)
        {
            if (id == arr[i].getId())
            {
                for (int j = i; j < n - 1; j++)
                    arr[j] = arr[j + 1];
                flag = true;
                n--;
                break ;
            }
        }

        return (flag); 
    }


    @Override
    public Paseo buscarPaseo(String nombrePerro) 
    {
        for (int i = 0; i < n; i++)
        {
            if (nombrePerro.equals(arr[i].getNombrePerro()))
                return (arr[i]);
        }

        return (null);
    }


    @Override
    public void mostrarPaseos() 
    {
        System.out.println("====== LISTA DE PASEOS ======");
        for (int i = 0; i < n; i++)
        {
            System.out.println(arr[i]);
        }        
    }


    

    
}

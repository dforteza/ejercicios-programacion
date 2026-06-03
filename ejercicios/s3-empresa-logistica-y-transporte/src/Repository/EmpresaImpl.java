package Repository;

import java.time.LocalDate;

import Model.Camion;
import Model.Furgoneta;
import Model.Motocicleta;
import Model.Vehiculo;

public class EmpresaImpl implements Empresa
{
    String  cif;
    String  denominacion;
    
    Vehiculo[] vehiculos;
    int     n = 0;
    
    public EmpresaImpl(String cif, String denominacion, int nMax) 
    {
        this.cif = cif;
        this.denominacion = denominacion;

        this.vehiculos = new Vehiculo[nMax];
    }

    @Override
    public boolean altaVehiculo(Vehiculo v)
    {
        if (n >= vehiculos.length)
        {
            System.out.println("Error - capacidad maxima");
            return (false);
        }
        vehiculos[n++] = v;
        
        return (true);
    }

    @Override
    public void imprimirCoste()
    {
        for (int i = 0; i < n; i++)
            System.out.println(vehiculos[i].getModelo() + " - " + vehiculos[i].calcularCosteMantenimiento());
    }

    

    @Override
    public void imprimirITV()
    {
        int cont = 0;
        LocalDate hoy = LocalDate.now();

        for (int i = 0; i < n; i++)
        {
            if (vehiculos[i] instanceof Camion)
            {
                if (hoy.isAfter(vehiculos[i].getFechaMatricula().plusYears(2)))
                {
                    System.out.println(vehiculos[i].getModelo() + " - " + vehiculos[i].getFechaMatricula());
                    cont++;
                }
            }
            else if (vehiculos[i] instanceof Furgoneta)
            {
                if (hoy.isAfter(vehiculos[i].getFechaMatricula().plusYears(4)))
                {
                    System.out.println(vehiculos[i].getModelo() + " - " + vehiculos[i].getFechaMatricula());
                    cont++;
                }
            }
            else if (vehiculos[i] instanceof Motocicleta)
            {
                if (hoy.isAfter(vehiculos[i].getFechaMatricula().plusYears(6)))
                {
                    System.out.println(vehiculos[i].getModelo() + " - " + vehiculos[i].getFechaMatricula());
                    cont++;
                }
            }
        }

        if (cont == 0)
            System.out.println("NO HAY VEHICULOS PENDIENTES DE ITV");
    }

    @Override
    public String toString() 
    {
        String res = "";

        res +=  "EmpresaImpl [cif=" + cif + ", denominacion=" + denominacion + "]\n";
        
        if (n == 0)
            res += "No hay vehiculos disp\n";
        else
        {
            res += "--- LISTA DE VEHICULOS ---\n";
            for (int i = 0; i < n; i++)
            {
                res += vehiculos[i] + "\n";
            }
        }
        return (res);    
    }

    

    

    
    
}

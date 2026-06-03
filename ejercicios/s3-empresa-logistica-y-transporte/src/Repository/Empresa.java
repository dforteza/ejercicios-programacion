package Repository;

import Model.Vehiculo;

public interface Empresa 
{
    public boolean  altaVehiculo(Vehiculo v);
    public void     imprimirCoste();
    public void     imprimirITV();
}

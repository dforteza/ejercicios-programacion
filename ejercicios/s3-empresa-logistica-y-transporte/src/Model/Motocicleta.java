package Model;

import java.time.LocalDate;

public class Motocicleta extends Vehiculo
{
    private int cilindrada;

    public Motocicleta(String matricula, String modelo, LocalDate fechaMatricula, Conductor conductor, int cilindrada)
    {
        super(matricula, modelo, fechaMatricula, conductor);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public double calcularCosteMantenimiento()
    {
        return (cilindrada > 500) ? 120 : 60;
    }

    @Override
    public String toString()
    {
        String res = "";

        res += super.toString();
        res += cilindrada + " cc. -> ";
        res += getConductor();
        return (res);
    }
    
    
}

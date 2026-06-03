package Model;

import java.time.LocalDate;

public class Furgoneta extends Vehiculo
{
    private int     capacidad;
    private boolean adaptada;
    public Furgoneta(String matricula, String modelo, LocalDate fechaMatricula, Conductor conductor, int capacidad,
            boolean adaptada) {
        super(matricula, modelo, fechaMatricula, conductor);
        this.capacidad = capacidad;
        this.adaptada = adaptada;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public boolean isAdaptada() {
        return adaptada;
    }

    public double calcularCosteMantenimiento()
    {
        double coste = 70 + (capacidad * 10);
        if (adaptada)
            coste *= 1.15;
        return coste;
    }

    @Override
    public String toString()
    {
        String res = "";

        res += super.toString();
        res += capacidad + " pax. ";
        res += (adaptada == true) ? "(Si) " : "(No) ";
        res += "Adaptado -> ";
        res += getConductor();
        return (res);
    }
    
}

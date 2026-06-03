package Model;

import java.time.LocalDate;

public class Camion extends Vehiculo
{
    private Float volumen;
    private Float peso;

    public Camion(String matricula, String modelo, LocalDate fechaMatricula, Conductor conductor,
            Float volumen, Float peso)
    {
        super(matricula, modelo, fechaMatricula, conductor);
        this.volumen = volumen;
        this.peso = peso;
    }
    
    @Override
    public double calcularCosteMantenimiento()
    {
        Double res = 0.0;

        res = 100 + (peso * 0.05) + (volumen * 0.01);
        
        return (res);
    }

    public Float getVolumen() {
        return volumen;
    }

    public Float getPeso() {
        return peso;
    }

    @Override
    public String toString()
    {
        String res = "";

        res += super.toString();
        res += volumen + " litros. ";
        res += peso + " kh. -> ";
        res += getConductor();
        return (res);
    }

    
    
    
}

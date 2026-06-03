package Model;

import java.time.LocalDate;

public abstract class Vehiculo 
{
    private String      matricula;
    private String      modelo;
    private LocalDate   fechaMatricula;
    private Conductor   conductor;


    public Vehiculo(String matricula, String modelo, LocalDate fechaMatricula, Conductor conductor)
    {
        this.matricula = matricula;
        this.modelo = modelo;
        this.fechaMatricula = fechaMatricula;
        this.conductor = conductor;
    }

    public abstract double calcularCosteMantenimiento();

    public String getMatricula() {
        return matricula;
    }


    public String getModelo() {
        return modelo;
    }


    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }


    public Conductor getConductor() {
        return conductor;
    }


    @Override
    public String toString()
    {
        String res = "";

        res += matricula + " - ";
        res += modelo + " ";
        res += fechaMatricula + " - ";
        
        return (res);
    }

    
    
    
}

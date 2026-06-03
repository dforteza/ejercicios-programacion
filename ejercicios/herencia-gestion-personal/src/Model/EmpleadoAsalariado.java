package Model;

import java.time.LocalDate;

public class EmpleadoAsalariado extends Empleado
{
    private Double sueldo_base;
    private Double porcentaje;

    public EmpleadoAsalariado() {
    }

    public EmpleadoAsalariado(String dni, String nombre, String apellidos, LocalDate fechaAlta, Double sueldo_base) 
    {
        super(dni, nombre, apellidos, fechaAlta);
        this.sueldo_base = sueldo_base;
        this.porcentaje = calcularPorcentaje(this.sueldo_base, getFechaAlta().getYear());
    }

    private Double calcularPorcentaje(Double sb, int year)
    {
        int anios_alta = LocalDate.now().getYear() - year;

        Double porcentaje = 0.0;

        if (anios_alta < 2)
            porcentaje = 0.0;
        else if (anios_alta >= 2 && anios_alta <= 3)
            porcentaje = sb * 0.07;
        else if (anios_alta >= 4 && anios_alta <= 8)
            porcentaje = sb * 0.11;
        else if (anios_alta >= 9 && anios_alta <= 15)
            porcentaje = sb * 0.17;
        
        return (porcentaje);
    }


    @Override
    public Double calcularSalario() 
    {
        return getSueldo_base() + getPorcentaje();
    }


    public Double getSueldo_base() {
        return sueldo_base;
    }


    public void setSueldo_base(Double sueldo_base) {
        this.sueldo_base = sueldo_base;

        if (getFechaAlta() != null)
            setPorcentaje();
    }


    public Double getPorcentaje() {
        return porcentaje;
    }


    public void setPorcentaje() 
    {
        this.porcentaje = calcularPorcentaje(this.sueldo_base, getFechaAlta().getYear());
    }

    @Override
    public String toString() 
    {
        String resultado;

        resultado = super.toString() + ", sueldo base: " +sueldo_base + ", porcentaje: " +porcentaje;

        return (resultado);
    }


    
    



    
}

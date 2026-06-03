package Model;

import java.time.LocalDate;

public class EmpleadoComision extends Empleado
{
    private final Double    sueldo_fijo = 950.0;
    private Integer         n_clientes;
    private Double          comision;

    public EmpleadoComision() {
    }

    public EmpleadoComision(String dni, String nombre, String apellidos, LocalDate fechaAlta, Integer n_clientes, Double comision) 
    {
        super(dni, nombre, apellidos, fechaAlta);
        this.n_clientes = n_clientes;
        this.comision = comision;
    }


    @Override
    public Double calcularSalario() 
    {
        Double salario;

        salario = getN_clientes() * getComision();
        
        if (salario < getSueldo_fijo())
            salario = sueldo_fijo;

        return (salario);
    }

    public Double getSueldo_fijo() {
        return sueldo_fijo;
    }

    public Integer getN_clientes() {
        return n_clientes;
    }

    public Double getComision() {
        return comision;
    }

    public void setN_clientes(Integer n_clientes) {
        this.n_clientes = n_clientes;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    @Override
    public String toString() 
    {
        String resultado;

        resultado =  super.toString() + ", numero de clientes captados: " + n_clientes + ", comision: " + comision;

        return (resultado);
    }

    
    
    


    
}

package Model;

import java.time.LocalDate;

public abstract class Empleado 
{
    private String      dni;
    private String      nombre;
    private String      apellidos;
    private LocalDate   fechaAlta;

    
    public Empleado() {
    }

    public Empleado(String dni, String nombre, String apellidos, LocalDate fechaAlta) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaAlta = fechaAlta;
    }

    public abstract Double calcularSalario();

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    

    @Override
    public String toString() 
    {
        String resultado = "";

        resultado += "Empleado: dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaAlta=" + fechaAlta
                +", salario=" +calcularSalario();
        return (resultado);
    
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


    

    
}
package Model;

public abstract class Trabajador 
{
    protected String    dni;
    protected String    nombre;
    protected Double    salario_base;

    public Trabajador(String dni, String nombre, Double salario_base) {
        this.dni = dni;
        this.nombre = nombre;
        this.salario_base = salario_base;
    }

    public abstract Double calcularSalarioFinal();


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSalario_base() {
        return salario_base;
    }

    public void setSalario_base(Double salario_base) {
        this.salario_base = salario_base;
    }

    @Override
    public String toString() 
    {
        String resultado = "";
        
        resultado += "{dni=" + dni + ", nombre=" + nombre + ", salarioB=" + salario_base
                + ", salarioF=" + calcularSalarioFinal() + "}";

        return (resultado);
    }

    
   
    
}

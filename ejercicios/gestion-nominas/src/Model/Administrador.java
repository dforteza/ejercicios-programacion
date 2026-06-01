package Model;

public class Administrador extends Gestion
{
    private final Double complemento;

    public Administrador(String dni, String nombre, Double salario_base, Integer antiguedad) 
    {
        super(dni, nombre, salario_base, antiguedad);
        this.complemento = 20.0 * getAntiguedad();
    }

    @Override
    public Double calcularSalarioFinal()
    {
        return (complemento + salario_base);
    }

    @Override
    public String toString()
    {
        return "Administrativo: " + super.toString();
    }

    

    
}

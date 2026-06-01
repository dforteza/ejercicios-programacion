package Model;

public class Auxiliar extends Gestion
{
    private final Double complemento = 100.0;

    public Auxiliar(String dni, String nombre, Double salario_base, Integer antiguedad)
    {
        super(dni, nombre, salario_base, antiguedad);
    }

    @Override
    public Double calcularSalarioFinal()
    {
        return salario_base + complemento;
    }

    @Override
    public String toString()
    {
        return "Auxiliar: " + super.toString();
    }
}

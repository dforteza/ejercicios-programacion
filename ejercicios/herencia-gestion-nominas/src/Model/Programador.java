package Model;

public class Programador extends Informatico
{
    private final Double complemento = 0.15;

    public Programador(String dni, String nombre, Double salario_base, String titulacion) {
        super(dni, nombre, salario_base, titulacion);
    }

    @Override
    public Double calcularSalarioFinal()
    {
        return ((complemento + 1) * getSalario_base());
    }

    @Override
    public String toString()
    {
        return "Programador: " + super.toString();
    }
}
package Model;

public class Analista extends Informatico
{
    private final Double complemento = 0.3;

    public Analista(String dni, String nombre, Double salario_base, String titulacion) {
        super(dni, nombre, salario_base, titulacion);
    }

    @Override
    public Double calcularSalarioFinal()
    {
        return ((this.complemento + 1) * getSalario_base());
    }

    @Override
    public String toString()
    {
        return "Analista: " + super.toString();
    }
}

package Model;

public abstract class Gestion extends Trabajador
{
    private Integer antiguedad;

    public Gestion(String dni, String nombre, Double salario_base, Integer antiguedad) {
        super(dni, nombre, salario_base);
        this.antiguedad = antiguedad;
    }
    
    public abstract Double calcularSalarioFinal();

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    
}

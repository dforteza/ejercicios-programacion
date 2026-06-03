package Model;

public abstract class Informatico extends Trabajador
{
    private String titulacion;

    public Informatico(String dni, String nombre, Double salario_base, String titulacion) {
        super(dni, nombre, salario_base);
        this.titulacion = titulacion;
    }

    public abstract Double calcularSalarioFinal();
    
    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }
}

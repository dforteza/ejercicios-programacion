package Model;

import java.time.LocalDate;

public class Perro extends Mascota
{
    private String  raza;
    private boolean agresivo;

    public Perro(String nombre, String especie, String descripcion, LocalDate fechaDesaparicion, String tlf,
            String raza, boolean agresivo) {
        super(nombre, especie, descripcion, fechaDesaparicion, tlf);
        this.raza = raza;
        this.agresivo = agresivo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public boolean isAgresivo() {
        return agresivo;
    }

    public void setAgresivo(boolean agresivo) {
        this.agresivo = agresivo;
    }

    @Override
    public String toString()
    {
        return super.toString() + " - " + raza + " - " + (agresivo ? "Agresivo" : "No agresivo");
    }

    
    
    
}

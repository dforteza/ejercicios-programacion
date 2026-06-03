public class Jugador
{
    private String nombre;
    private int puntuacion;

    public Jugador(String nombre, int puntuacion)
    {
        this.nombre     = nombre;
        this.puntuacion = puntuacion;
    }

    public String getNombre()      { return nombre; }
    public int    getPuntuacion()  { return puntuacion; }
    public void   setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }

    @Override
    public String toString()
    {
        return nombre + " — " + puntuacion + " pts";
    }
}

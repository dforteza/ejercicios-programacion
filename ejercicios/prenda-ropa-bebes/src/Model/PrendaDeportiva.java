package Model;

public class PrendaDeportiva extends Prenda
{
    private String  deporte;
    private String  marca;
    private boolean personalizada;

    public PrendaDeportiva(String nombre, String talla, String color, String composicion, String descripcion,
            String deporte, String marca, boolean personalizada) 
    {
        super(nombre, talla, color, composicion, descripcion);
        this.deporte = deporte;
        this.marca = marca;
        this.personalizada = personalizada;
    }

    public String getDeporte() {
        return deporte;
    }

    public String getMarca() {
        return marca;
    }

    public boolean isPersonalizada() {
        return personalizada;
    }

    

    @Override
    public boolean contieneTexto(String literal)
    {
        return super.contieneTexto(literal) 
        || deporte.contains(literal) 
        || marca.contains(literal);
    }

    @Override
    public String toStringPersist()
    {
        return "DEPORTIVA;" + getNombre() + ";" + getTalla() + ";" + getColor() + ";"
            + getComposicion() + ";" + getDescripcion() + ";" + deporte + ";" + marca + ";" + personalizada;
    }

    @Override
    public String toString() 
    {
        return super.toString() + " - " + deporte + " - " + marca + " - " + personalizada;
    }
}

public class Elemento implements Comparable<Elemento>
{
	private String nombre;
	private Double precio;
	private int cantidad;
	
	public Elemento()
	{
	}

	public Elemento(String nombre, Double precio, int cantidad) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public String toString()
	{
		String resultado="";
		resultado += nombre+" "+precio+"€ "+cantidad+" UD.";
		return resultado;
	}

    @Override
    public int compareTo(Elemento o) 
	{
        return (this.getNombre().compareTo(o.getNombre()));
    }

	
	
}

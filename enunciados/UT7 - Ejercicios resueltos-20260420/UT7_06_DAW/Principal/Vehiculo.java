package Principal;

public class Vehiculo 
{
	private String codigo;

	public Vehiculo(String codigo) 
	{
		super();
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}


	public String toString()
	{
		String resultado="";
		resultado = this.codigo;
		return resultado;
	}
}

package Principal;

public class Funcionario 
{
	private int placa;
	private String nombre;

	public Funcionario(int placa,String nombre) 
	{
		super();
		this.placa = placa;
		this.nombre = nombre;
	}
	
	public String toString()
	{
		String resultado="";
		resultado = this.placa+" "+this.nombre;
		return resultado;
	}
}

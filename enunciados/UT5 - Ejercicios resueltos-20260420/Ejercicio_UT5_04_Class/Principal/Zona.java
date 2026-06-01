package Principal;

public class Zona 
{
	private int entradasPorVender;

	public Zona(int entradasPorVender) 
	{
		this.entradasPorVender = entradasPorVender;
	}

	public int getEntradasPorVender() 
	{
		return entradasPorVender;
	}
	
	public boolean comprar (int n)
	{
		boolean exito = false;
		if (n <= entradasPorVender)
		{
			this.entradasPorVender -= n;
			exito=true;
		}
		return exito;
	}
	
	public String toString()
	{
		String resultado="";
		resultado += "Entradas disponibles: "+this.entradasPorVender;
		return resultado;
	}
	
}

package Principal;

public enum Prioridad 
{
	NORMAL(0),ALTA(10),URGENTE(20),EXTREMA(100);
	
	private int suplemento;
	
	private Prioridad(int suplemento)
	{
		this.suplemento = suplemento;
	}
	
	public int getSuplemento()
	{
		return this.suplemento;
	}

}

package Principal;

import java.time.LocalTime;

public class Turno 
{
	private String denominacion;
	private LocalTime desde;
	private LocalTime hasta;
	
	public Turno(String denominacion, LocalTime desde, LocalTime hasta) 
	{
		super();
		this.denominacion = denominacion;
		this.desde = desde;
		this.hasta = hasta;
	}
	
	public String toString()
	{
		String resultado ="";
		resultado += this.denominacion;
		resultado += String.format(" %02d:%02d -",desde.getHour(),desde.getMinute());
		resultado += String.format(" %02d:%02d",hasta.getHour(),hasta.getMinute());
		return resultado;
	}
}

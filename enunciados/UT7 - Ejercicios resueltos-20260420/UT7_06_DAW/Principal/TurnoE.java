package Principal;

import java.time.LocalTime;

public enum TurnoE 
{
	MADRUGADA (LocalTime.of(0, 0),LocalTime.of(7,59)),
	MAÑANA(LocalTime.of(8, 0),LocalTime.of(15,59)),
	TARDE(LocalTime.of(16,0),LocalTime.of(23, 59));
	
	private LocalTime desde;
	private LocalTime hasta;
	
	private TurnoE (LocalTime desde,LocalTime hasta)
	{
		this.desde = desde;
		this.hasta = hasta;
	}
	
	public LocalTime getDesde() {
		return desde;
	}

	public LocalTime getHasta() {
		return hasta;
	}

	public String toString()
	{
		String resultado ="";
		resultado += this.name();
		resultado += String.format(" %02d:%02d -",desde.getHour(),desde.getMinute());
		resultado += String.format(" %02d:%02d",hasta.getHour(),hasta.getMinute());
		return resultado;
	}
	
}

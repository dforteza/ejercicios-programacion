package model;

import java.time.LocalTime;

// Enum con datos: cada constante lleva su propio rango horario.
// Ventaja frente a una clase: los valores son fijos y conocidos,
// no tiene sentido crear turnos arbitrarios en tiempo de ejecución.
public enum TurnoE
{
	MADRUGADA (LocalTime.of(0,  0), LocalTime.of(7,  59)),
	MAÑANA    (LocalTime.of(8,  0), LocalTime.of(15, 59)),
	TARDE     (LocalTime.of(16, 0), LocalTime.of(23, 59));

	private final LocalTime desde;
	private final LocalTime hasta;

	// El constructor de un enum es siempre private.
	// Se llama una vez por constante al arrancar el programa.
	private TurnoE(LocalTime desde, LocalTime hasta)
	{
		this.desde = desde;
		this.hasta = hasta;
	}

	public LocalTime getDesde()
	{
		return desde;
	}

	public LocalTime getHasta()
	{
		return hasta;
	}

	public String toString()
	{
		String resultado = this.name();
		resultado += String.format(" %02d:%02d -", desde.getHour(), desde.getMinute());
		resultado += String.format(" %02d:%02d",   hasta.getHour(), hasta.getMinute());

		return resultado;
	}
}

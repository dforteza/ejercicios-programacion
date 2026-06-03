package model;

import java.time.LocalDate;

// Entidad central. Representa una asignación concreta:
// un vehículo, dos agentes y un turno para un día concreto.
// Actúa como tabla de enlace entre las demás entidades.
public class Servicio
{
	private LocalDate    fecha;
	private Vehiculo     vehiculo;
	private Funcionario  funcionario[];
	private TurnoE       turno;

	public Servicio(LocalDate fecha, Vehiculo vehiculo, Funcionario f1, Funcionario f2, TurnoE turno)
	{
		this.fecha       = fecha;
		this.vehiculo    = vehiculo;
		// Tamaño fijo 2: el enunciado garantiza siempre exactamente dos agentes por servicio.
		// No necesita contador n porque no hay altas ni bajas.
		this.funcionario = new Funcionario[2];
		this.funcionario[0] = f1;
		this.funcionario[1] = f2;
		this.turno       = turno;
	}

	public LocalDate getFecha()
	{
		return fecha;
	}

	public TurnoE getTurno()
	{
		return turno;
	}

	public Vehiculo getVehiculo()
	{
		return vehiculo;
	}

	public String toString()
	{
		// %02d: mínimo 2 dígitos, rellena con 0 si hace falta (ej: 5 → "05")
		String resultado = String.format("%02d/%02d/%04d ",
				fecha.getDayOfMonth(),
				fecha.getMonthValue(),
				fecha.getYear());

		resultado += this.turno + " ";
		resultado += this.vehiculo + " ";

		for (int i = 0; i < funcionario.length; i++)
			resultado += this.funcionario[i] + " ";

		return resultado;
	}
}

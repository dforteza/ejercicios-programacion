package Principal;

import java.time.LocalDate;

public class Servicio 
{
	private LocalDate fecha;
	private Vehiculo vehiculo;
	private Funcionario funcionario[];
	private TurnoE turno;
	
	public Servicio(LocalDate fecha, Vehiculo vehiculo, Funcionario f1,Funcionario f2, TurnoE turno)
	{
		super();
		this.fecha = fecha;
		this.vehiculo = vehiculo;
		this.funcionario = new Funcionario[2];
		this.funcionario[0] = f1;
		this.funcionario[1] = f2;
		this.turno = turno;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public TurnoE getTurno() {
		return turno;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public String toString()
	{
		String resultado ="";
		resultado += String.format("%02d/%02d/%04d ",
				fecha.getDayOfMonth(),
				fecha.getMonthValue(),
				fecha.getYear());
		resultado += this.turno+" ";
		resultado += this.vehiculo+" ";
		for (int i=0;i<funcionario.length;i++)
			resultado += this.funcionario[i]+" ";
		return resultado;
	}
}

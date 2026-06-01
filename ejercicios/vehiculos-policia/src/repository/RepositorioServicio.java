package repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import model.Funcionario;
import model.Servicio;
import model.TurnoE;
import model.Vehiculo;

// Gestiona la colección de servicios.
// Genera los datos aleatoriamente al construirse y expone una búsqueda por fecha, hora y vehículo.
public class RepositorioServicio
{
	private Servicio    vector[];
	private Vehiculo    v[];
	private Funcionario f[];

	public RepositorioServicio(int nMaximoServicios, Vehiculo v[], Funcionario f[])
	{
		this.vector = new Servicio[nMaximoServicios];
		this.v      = v;
		this.f      = f;
		altaAleatoria();
	}

	private void altaAleatoria()
	{
		Random r = new Random();
		int n, m, o, p;

		for (int i = 0; i < vector.length; i++)
		{
			n = Math.abs(r.nextInt());
			m = Math.abs(r.nextInt());
			o = Math.abs(r.nextInt());
			p = Math.abs(r.nextInt());

			// Math.abs evita índices negativos.
			// % longitud garantiza que el índice siempre es válido dentro del array.
			// TurnoE.values() devuelve un array con todas las constantes del enum.
			vector[i] = new Servicio(
					LocalDate.now(),
					v[n % v.length],
					f[m % f.length],
					f[p % f.length],
					TurnoE.values()[o % TurnoE.values().length]);
		}
	}

	// Busca el servicio activo para un vehículo en una fecha y hora concretas.
	// Devuelve null si no encuentra ninguno.
	public Servicio buscarXFechaHora(String codigo, LocalDate fecha, LocalTime hora)
	{
		Servicio s    = null;
		TurnoE   turno;

		for (int i = 0; i < vector.length; i++)
		{
			// Primer filtro: fecha y vehículo
			if (fecha.equals(vector[i].getFecha()) &&
				codigo.equals(vector[i].getVehiculo().getCodigo()))
			{
				turno = vector[i].getTurno();

				// Segundo filtro: la hora cae dentro del rango del turno.
				// isAfter e isBefore son estrictos — hora exactamente igual a desde/hasta no cuenta.
				if (hora.isAfter(turno.getDesde()) &&
					hora.isBefore(turno.getHasta()))
				{
					s = vector[i];
					break;
				}
			}
		}

		return s;
	}

	public String toString()
	{
		String resultado = "SERVICIOS\n";
		resultado += "=========\n";

		for (int i = 0; i < vector.length; i++)
			resultado += vector[i] + "\n";

		return resultado;
	}
}

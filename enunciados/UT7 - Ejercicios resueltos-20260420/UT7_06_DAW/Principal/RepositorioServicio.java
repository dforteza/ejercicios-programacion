package Principal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class RepositorioServicio 
{
	private Servicio vector[];
	
	private Vehiculo v[];
	private Funcionario f[];
	
	public RepositorioServicio (int nMaximoServicios,Vehiculo v[],Funcionario f[])
	{
		this.vector = new Servicio[nMaximoServicios];
		this.v = v;
		this.f = f;
		altaAleatoria();
	}
	
	public void altaAleatoria ()
	{
		Random r = new Random();
		int n,m,o,p;
		
		for (int i=0;i<vector.length;i++)
		{
			n = Math.abs(r.nextInt());
			m = Math.abs(r.nextInt());
			o = Math.abs(r.nextInt());
			p = Math.abs(r.nextInt());
			vector[i] = new Servicio (
					LocalDate.now(),
					v[n%v.length],
					f[m%f.length],
					f[(p)%f.length],
					TurnoE.values()[o%TurnoE.values().length]);
		}
	}
	
	public Servicio buscarXFechaHora(String codigo, LocalDate fecha,LocalTime hora)
	{
		Servicio s = null;
		TurnoE turno;
		for (int i=0;i<vector.length;i++)
			if (fecha.equals(vector[i].getFecha()) && 
					codigo.equals(vector[i].getVehiculo().getCodigo()))
			{
				turno = vector[i].getTurno();
				if (hora.isAfter(turno.getDesde()) && 
						hora.isBefore(turno.getHasta()))
						{
							s = vector[i];
							break;
						}
			}
		return s;
	}
	
	public String toString()
	{
		String resultado ="";
		resultado += "SERVICIOS\n";
		resultado += "=========\n";
		for (int i=0;i<vector.length;i++)
			resultado += vector[i]+"\n";
		return resultado;
	}
}

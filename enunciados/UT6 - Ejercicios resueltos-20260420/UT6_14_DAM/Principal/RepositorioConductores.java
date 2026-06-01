package Principal;

import java.util.Scanner;

// Repositorio de Conductores basado en un vector.
public class RepositorioConductores 
{
	private Conductor vector[];
	private int nConductores;
	
	// Constructor del respositorio
	public RepositorioConductores (int nMaximoConductores)
	{
		this.vector = new Conductor[nMaximoConductores];
		this.nConductores = 0;
	}
	
	// Alta de conductores
	public boolean alta(Conductor c)
	{
		boolean exito=false;
		if (this.nConductores<vector.length)
		{
			vector[nConductores] = c;
			nConductores++;
			exito = true;
		}
		return exito;
	}
	
	public boolean alta(Scanner sc)
	{
		boolean exito = false;
		if (this.nConductores<vector.length)
			exito = alta(new Conductor(sc));
		return exito;
	}
	
	// Añadir km a un conductor
	
	public boolean incrementarKmConductor (Scanner sc)
	{
		boolean exito = false;
		String nombre;
		int km;
		System.out.println("Introduzca nombre del conductor");
		nombre = sc.nextLine();
		for (int i=0;i<nConductores;i++)
			if (nombre.equals(vector[i].getNombre()))
			{
				System.out.println(vector[i]);
				System.out.println("Km para añadir:");
				km = sc.nextInt(); 
				sc.nextLine();
				vector[i].addKm(km);
				System.out.println(vector[i]);
				exito = true;
				break;
			}
		if (exito==false)
			System.out.println("El conductor no existe");
		
		return exito;
	}
	
	
	// Bajas de conductor
	
	public boolean baja (Scanner sc)
	{
		boolean exito = false;
		String nombre;
		System.out.println("Introduzca conductor a eliminar");
		nombre = sc.nextLine();
		for (int i=0;i<nConductores;i++)
			if (nombre.equals(vector[i].getNombre()))
			{
				for (int j=i;j<nConductores-1;j++)
					vector[j] = vector[j+1];
				nConductores--;
				exito = true;
				break; // i--;
			}
		return exito;
	}
	
	
	public boolean modificarConductor(Scanner sc)
	{
		boolean exito = false;
		String nombre;
		
		System.out.println("Introduzca el nombre del conductor");
		nombre = sc.nextLine();
		for (int i=0;i<nConductores;i++)
			if (nombre.equals(vector[i].getNombre()))
			{
				vector[i].modificarConductor(sc);
				System.out.println(vector[i]);
				exito = true;
			}
		return exito;
	}
	public String toString()
	{
		String resultado="";
		int totalKm = 0;
		if (nConductores==0)
			resultado += "No hay conductores";
		else
		{
			resultado += "LISTADO DE CONDUCTORES\n";
			resultado += "======================\n";
			for (int i=0;i<nConductores;i++)
			{
				resultado += vector[i]+"\n";
				totalKm += vector[i].getKm();
			}
			resultado += "Total de km: "+totalKm;
		}
		return resultado;
	}
	
}

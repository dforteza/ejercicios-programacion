package Principal;

import java.util.Scanner;

// Repositorio de Conductores basado en un vector.
public class Repositorio 
{
	private Conductor	vector[];
	private int			n;
	
	// Constructor del respositorio
	public Repositorio (int nMax)
	{
		this.vector = new Conductor[nMax];
		this.n = 0;
	}
	
	// Alta de conductores
	public boolean alta(Conductor c)
	{
		boolean flag = false;

		// 1- Condicion espacio
		if (this.n < vector.length)
		{
			// 2- Crear Objeto
			vector[n] = c;
			n++;

			flag = true;
		}

		return (flag);
	}
	
	public boolean alta(Scanner sc)
	{
		boolean exito = false;
		if (this.n<vector.length)
			exito = alta(new Conductor(sc));

		return exito;
	}
	
	// Añadir km a un conductor
	public boolean incrementarKmConductor (Scanner sc)
	{
		boolean exito = false;
		String	nombre;
		int		km;

		System.out.println("Introduzca nombre del conductor");
		nombre = sc.nextLine();

		for (int i=0;i<n;i++)
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

	
	public boolean baja (Scanner sc)
	{
		// * boolean flag
		Boolean flag = false;

		// 1- identificador
		System.out.println("> Campo: ");
		String campo = sc.nextLine();

		// 2 - buscar
		for (int i = 0; i < n; i++)
		{
			if (vector[i].getNombre().equals(campo))
			{
				// 3- deplazar
				vector[i] = vector[i + 1];
				// 4- actualiazr campos
				n--;
				flag = true;
				break ;
			}
		}

		return (flag);
	}
	
	public boolean modificarConductor(Scanner sc)
	{
		// *boolean flag
		Boolean flag = false;

		// 1- identificador
		System.out.println("> Campo: ");
		String campo = sc.nextLine();

		// 2- buscar
		for (int i = 0; i < n; i++)
		{
			if (vector[i].getNombre().equals(campo))
			{
				// 3- update
				vector[i].modificarConductor(sc);
				// 4- actualizar campos}
				flag = true;
				break ;
			}
		}
		
		return (flag);
	}

	public String toString()
	{
		String	resultado = "";
		int		totalKm = 0;
		
		if (n == 0)
			resultado += "Array vacio";
		else
		{
			resultado += "LISTADO\n";
			resultado += "======================\n";
			// 1- recorrer
			for (int i = 0;i < n;i++)
			{
				// 2- mostrar
				resultado += vector[i] + "\n";
				totalKm += vector[i].getKm();
			}
			resultado += "Total de km: "+totalKm;
		}
		return (resultado);
	}
	
}

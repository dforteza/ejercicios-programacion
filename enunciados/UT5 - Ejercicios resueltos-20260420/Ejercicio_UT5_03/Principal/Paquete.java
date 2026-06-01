package Principal;

import java.util.Scanner;

public class Paquete 
{
	private String referencia;
	private double peso;
	private String dni;
	private Prioridad prioridad;
	
	
	
	public Paquete (Scanner sc)
	{
		boolean exito = true;
		System.out.println("Introduzca referencia del paquete");
		this.referencia = sc.nextLine();
		System.out.println("Introduzca peso del paquete (Kg):");
		this.peso = sc.nextDouble();
		sc.nextLine(); // Borramos buffer
		System.out.println("Introduzca dni");
		this.dni = sc.nextLine();
		System.out.println("Introduzca Prioridad ");
		do
		{
			exito = true;
			for (Prioridad p: Prioridad.values())
				System.out.println(p);
			try 
			{
				this.prioridad = Prioridad.valueOf(sc.nextLine().toUpperCase());
			}
			catch (IllegalArgumentException e)
			{
				System.out.println("No existe esa prioridad");
				exito = false;
			}
		} while (!exito);
	}
	public Paquete (String r,double p,String dni,Prioridad prio)
	{
		this.referencia = r;
		this.peso = p;
		this.dni = dni;
		this.prioridad = prio;
	}
	
	public void setPrioridad(Prioridad prioridad)
	{
		this.prioridad = prioridad;
	}
	
	public void peso (double peso)
	{
		this.peso = peso;
	}
	
	public Prioridad getPrioridad()
	{
		return this.prioridad;
	}
	
	public double getPeso()
	{
		return this.peso;
	}

	@Override
	public String toString() {
		return "Paquete [referencia=" + referencia + ", peso=" + peso + ", dni=" + dni + ", prioridad=" + prioridad
				+ "]";
	}
}

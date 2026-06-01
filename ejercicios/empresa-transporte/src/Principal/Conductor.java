package Principal;

import java.util.Scanner;

public class Conductor 
{
	private String	nombre;
	private int		km;
	
	public Conductor (String nombre)
	{
		this.nombre = nombre;
		this.km = 0;
	}
	
	public Conductor (Scanner sc)
	{
		System.out.println("Introduzca el nombre");
		this.nombre = sc.nextLine();

		this.km = 0;
	}

	// METODOS DE LOGICA DE NEGOCIO 
	
	public void modificarConductor(Scanner sc)
	{
		String nombre;
		System.out.println("Nombre: "+this.nombre);
		System.out.println("Nuevo nombre:");
		nombre = sc.nextLine();
		if (nombre.length()!=0)
			this.nombre = nombre;
	}
	
	public void addKm(int km)
	{
		this.km += km;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}
	
	public String toString()
	{
		String resultado ="";
		resultado += "Nombre: "+this.nombre;
		resultado += " km: "+String.format("%04d",this.km);
		return resultado;
	}
}

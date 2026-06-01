package Principal;

import java.util.Scanner;

public class Consultor extends Empleado 
{
	private double precioHora;
	private int numHoras;
	
	public Consultor(String nombre, String nss, double sueldoBase, 
			double precioHora, int numHoras) 
	{
		super(nombre, nss, sueldoBase);
		this.precioHora = precioHora;
		this.numHoras = numHoras;
	}

	public Consultor (Scanner sc)
	{
		super(sc);
		System.out.println("Introduzca precio por hora:");
		this.precioHora = Double.parseDouble(sc.nextLine());
		System.out.println("Introduzca número de horas");
		this.numHoras = Integer.parseInt(sc.nextLine());
	}
	
	public double getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(double precioHora) {
		this.precioHora = precioHora;
	}

	public int getNumHoras() {
		return numHoras;
	}

	public void setNumHoras(int numHoras) {
		this.numHoras = numHoras;
	}
	
	public String quienSoy()
	{
		return "Consultor: ";
	}
	
	public String toString()
	{
		String resultado="";
		resultado += super.toString();
		resultado += String.format(" Precio hora:%.2f - Número horas:%d",
				this.precioHora,this.numHoras);
		return resultado;
	}
	
}

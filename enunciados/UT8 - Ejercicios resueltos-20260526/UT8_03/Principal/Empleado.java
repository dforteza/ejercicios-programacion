package Principal;

import java.util.Scanner;

public class Empleado implements Comparable<Empleado>
{
	private String nombre;
	private String nss;
	private double sueldoBase;
	
	public Empleado(String nombre, String nss, double sueldoBase) {
		super();
		this.nombre = nombre;
		this.nss = nss;
		this.sueldoBase = sueldoBase;
	}
	
	public Empleado (Scanner sc)
	{
		System.out.println("Introduzca Nombre:");
		this.nombre = sc.nextLine();
		System.out.println("Introduzca NSS:");
		this.nss = sc.nextLine();
		System.out.println("Introduzca sueldo base");
		this.sueldoBase =Double.parseDouble(sc.nextLine());
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public double getSueldoBase() {
		return sueldoBase;
	}

	public void setSueldoBase(double sueldoBase) {
		this.sueldoBase = sueldoBase;
	}
	
	public String quienSoy()
	{
		return "Empleado: ";
	}
	
	public String toString()
	{
		String resultado="";
		resultado += this.nss+" "+this.nombre;
		resultado += String.format(" %.2f",this.sueldoBase);
		return resultado;
	}

	@Override
	public int compareTo(Empleado o) 
	{
		
		return this.nombre.compareTo(o.nombre);
	}
}

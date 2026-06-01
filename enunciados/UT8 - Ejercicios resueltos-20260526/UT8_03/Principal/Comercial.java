package Principal;

import java.util.Scanner;

public class Comercial extends Empleado 
{
	private double comision;

	public Comercial(String nombre, String nss, double sueldoBase, double comision) {
		super(nombre, nss, sueldoBase);
		this.comision = comision;
	}

	public Comercial (Scanner sc)
	{
		super(sc);
		System.out.println("Introduzca comision:");
		this.comision = Double.parseDouble(sc.nextLine());
	}
	
	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}
	
	public String quienSoy()
	{
		return "Comercial:";
	}
	
	public String toString()
	{
		String resultado="";
		resultado += super.toString();
		resultado += String.format(" Comision: %.2f%%",this.comision);
		return resultado;
	}
	
}

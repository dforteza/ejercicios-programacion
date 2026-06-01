package Principal;

import java.util.Scanner;

public class Sucursal 
{
	private static int secuencia = 0; // Atributo común a todos los objetos
	private int numero;
	private String direccion;
	private String ciudad;
	
	public Sucursal (Scanner sc)
	{
		System.out.println("Introduzca dirección de la sucursal:");
		this.direccion = sc.nextLine();
		System.out.println("Introduzca ciudad de la sucursal:");
		this.ciudad = sc.nextLine();
		this.numero = secuencia++;
	}
	
	public Sucursal (String direccion,String ciudad)
	{
		this.numero = secuencia++;
		this.direccion = direccion;
		this.ciudad = ciudad;
	}
	
	public String getDireccion()
	{
		return this.direccion;
	}
	
	public String getCiudad()
	{
		return this.ciudad;
	}
	
	public double calcularPrecio(Paquete paquete,double precioKg)
	{
		double precio;
		precio = paquete.getPeso() * precioKg;
		precio += paquete.getPrioridad().getSuplemento();
		return precio;
	}
	
	public String toString()
	{
		String resultado="";
		resultado += "Numero: "+this.numero+" "+this.direccion+" "+this.ciudad;
		return resultado;
	}
}

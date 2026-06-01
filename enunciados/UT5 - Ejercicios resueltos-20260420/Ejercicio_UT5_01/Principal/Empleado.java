package Principal;

import java.util.Scanner;

public class Empleado 
{
	// Atributos
	private String nombreCompleto;
	private int permanencia;
	private float salario;
	private String clasificacion;
	
	
	public Empleado(Scanner sc)
	{
		System.out.println("Nombre:");
		this.nombreCompleto = sc.nextLine();
		System.out.println("Permanencia:");
		this.permanencia = sc.nextInt();
		System.out.println("Salario:");
		this.salario = sc.nextFloat();
		sc.nextLine();
		this.clasificacion = calcularClasificacion();
	}
	
	public Empleado()
	{
		this.nombreCompleto="Sin nombre";
		this.permanencia = 0;
		this.salario = 1000;
		this.clasificacion = calcularClasificacion();
	}
	
	public Empleado(String nombreCompleto,int permanencia,float salario)
	{
		this.nombreCompleto = nombreCompleto;
		this.permanencia = permanencia;
		this.salario = salario;
		this.clasificacion = calcularClasificacion(); 
	}
	
	public void setPermanencia(int permanencia)
	{
		this.permanencia = permanencia;
		this.clasificacion = calcularClasificacion();
	}
	
	public void aumentarSalario(float porcentaje)
	{
		this.salario += this.salario * porcentaje /100.0f;
	}
	
	public String getNombreCompleto()
	{
		return this.nombreCompleto;
	}
	
	public String calcularClasificacion ()
	{
		String clasificacion;
		if (this.permanencia<=3)
			clasificacion = "Principiante";
		else
			if (this.permanencia < 18)
				clasificacion = "Intermedio";
			else
				clasificacion = "Sénior";
		return clasificacion;
	}
	
	public String toString()
	{
		String resultado="";
		resultado += "Nombre del Empleado: "+this.nombreCompleto+"\n";
		resultado += "Permanencia: "+this.permanencia+"\n";
		resultado += "Salario: "+String.format ("%.2f\n",this.salario);
		resultado += "Clasificacion: "+this.clasificacion;
		return resultado;
	}
	
}

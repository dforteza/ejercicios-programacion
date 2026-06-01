package model;

// Entidad simple. Representa a un agente identificado por placa y nombre.
public class Funcionario
{
	private int placa;
	private String nombre;

	public Funcionario(int placa, String nombre)
	{
		this.placa = placa;
		this.nombre = nombre;
	}

	public String toString()
	{
		return this.placa + " " + this.nombre;
	}
}

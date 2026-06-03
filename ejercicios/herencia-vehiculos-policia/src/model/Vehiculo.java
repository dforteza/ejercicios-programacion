package model;

// Entidad simple. Identifica un vehículo policial por su código.
public class Vehiculo
{
	private String codigo;

	public Vehiculo(String codigo)
	{
		this.codigo = codigo;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public String toString()
	{
		return this.codigo;
	}
}

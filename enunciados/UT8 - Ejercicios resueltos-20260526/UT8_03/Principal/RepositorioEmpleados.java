package Principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioEmpleados 
{
	private List<Empleado> lista;
	
	public RepositorioEmpleados()
	{
		lista = new ArrayList<Empleado>();
	}
	
	public boolean alta(Empleado e)
	{
		return lista.add(e);
	}

	public boolean eliminarNSS(String nss)
	{
		for (Empleado e : lista)
			if (nss.equals(e.getNss()))
				return lista.remove(e);
		return false;
	}
	
	public Empleado buscarNSS(String nss)
	{
		for (Empleado e : lista)
			if (nss.equals(e.getNss()))
				return e;
		return null;
	}
	
	public void ordenar()
	{
		Collections.sort(lista); 
	}
	
	public String toString()
	{
		String resultado="";
		resultado += "LISTADO DE EMPLEADOS\n";
		resultado += "====================\n";
		for (Empleado e : lista) // for (int i=0;i<lista.size();i++) 
		{
			resultado += e.quienSoy(); // Para mostrar el tipo
			// resultado += e.getClass().getCanonicalName(); Alternativa
			resultado += e+"\n"; //  resultado += lista.get(i)+"\n";
		}
		return resultado;
	}
}

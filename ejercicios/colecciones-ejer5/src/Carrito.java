import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Carrito 
{
	private List<Elemento> lista;
	
	public Carrito ()
	{
		lista = new ArrayList<>();
	}
	
	public boolean alta(Elemento e)
	{
		return lista.add(e);
	}
	
	public boolean eliminar(Elemento e)
	{
		return lista.remove(e);
	}
	
	public Elemento buscarByNombre(String nombre)
	{
		for (Elemento e:lista)
			if (nombre.equals(e.getNombre()))
				return e;
		return null;
	}
	
	public void ordenar() // Orden natural
	{
		lista.sort(null);
		//Collections.sort(lista);
	}
	public void ordenarDecreciente()
	{
		Collections.sort(lista,Comparator.reverseOrder());
		
		//lista.sort(null);
		//Collections.reverse(lista);
	}
	public void ordenarByCantidad()
	{
		lista.sort(Comparator.comparing(Elemento::getCantidad));
	}

	public void ordenarByPrecio()
	{
		lista.sort(Comparator.comparing(Elemento::getPrecio));
	}

	
	
	public String toString()
	{
		String resultado="";
		resultado += "CARRITO DE LA COMPRA\n";
		resultado += "====================\n";
		for (Elemento e : lista) // for (int i=0;i<lista.size(),i++)
			resultado += e +"\n"; // resultado+= lista.get(i)+"\n";
		return resultado;
	}
}

import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		Carrito carro = new Carrito();
		String nombre;
		Elemento aux,e;
		
		System.out.println("Introduzca nombre (FIN)");
		nombre = sc.nextLine();
		while (!nombre.toUpperCase().equals("FIN")) 
		{
			e = carro.buscarByNombre(nombre);
			aux = new Elemento();
			aux.setNombre(nombre);
			System.out.println("Cantidad:");
			aux.setCantidad(Integer.parseInt(sc.nextLine()));
			if (e == null)
			{
				System.out.println("Precio:");
				aux.setPrecio(Double.parseDouble(sc.nextLine()));
				carro.alta(aux);
			}
			else
				e.setCantidad(e.getCantidad()+aux.getCantidad());
			
			
			System.out.println("Introduzca nombre (FIN)");
			nombre = sc.nextLine();
		}
		//carro.ordenar();
		//carro.ordenarDecreciente();
		carro.ordenarByCantidad();
		System.out.println(carro);
		sc.close();
	}

}

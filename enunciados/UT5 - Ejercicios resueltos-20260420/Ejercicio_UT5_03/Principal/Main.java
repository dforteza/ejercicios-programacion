package Principal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		
		Scanner sc = new Scanner(System.in);
		final double precioPorKg = 5;
		int opcion;
		double precio;
		Sucursal sucursal = null;
		Paquete paquete;
		
		do
		{
			opcion = menu(sc);
			switch (opcion)
			{
			case 1:
				sucursal = new Sucursal(sc);
				System.out.println("Sucursal creada");
				System.out.println(sucursal);
				break;
			case 2:
				if (sucursal == null)
					System.out.println("Debe crear primero una sucursal");
				else
				{
					paquete = new Paquete(sc);
					System.out.println("Paquete creado. Calculando precio...");
					precio = sucursal.calcularPrecio(paquete, precioPorKg);
					System.out.println("Precio de envío: "+precio);
					break;
				}
			}
		} while (opcion!=3);
		sc.close();
	}
	
	public static int menu(Scanner sc)
	{
		int opcion;
		do
		{
			System.out.println("1 - Crear sucursal");
			System.out.println("2 - Enviar paquete");
			System.out.println("3 - Salir");
			System.out.println("Opcion: ");
			try
			{
				opcion = sc.nextInt();
				sc.nextLine();
			}
			catch (InputMismatchException e)
			{
				opcion = 0;
				sc.nextLine();
			}
			if (opcion < 1 || opcion > 3)
				System.out.println("Opción incorrecta");
		} while (opcion < 1 || opcion > 3);
		return opcion;
	}
}

package Principal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main 
{
	private static  Zona principal,venta,vip;
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int opcion;
		String nombreZona;
		int numeroEntradas;
		boolean exito;
		
		principal = new Zona(1000);
		venta = new Zona(200);
		vip = new Zona(25);
		
		do
		{
			opcion = menu(sc);
			switch (opcion)
			{
			case 1:
				System.out.println("Zona principal "+principal);
				System.out.println("Zona venta "+venta);
				System.out.println("Zona VIP "+vip);
				break;
			case 2:
				System.out.println("Zona de compra: (Principal,Venta,VIP)");
				nombreZona = sc.nextLine().toUpperCase();
				System.out.println("Número de entradas:");
				numeroEntradas = sc.nextInt();
				sc.nextLine(); // Limpiar Buffer de teclado
				
				exito = comprar(nombreZona,numeroEntradas);
				
				if (exito)
					System.out.println("Compra realizada");
				else
					System.out.println("Compra no realizada");
				break;
			}
		} while (opcion!=3);
		sc.close();
	}
	
	public static boolean comprar(String nombreZona,int numeroEntradas)
	{
		boolean exito;
		if (nombreZona.equals("PRINCIPAL"))
			exito = principal.comprar(numeroEntradas);
		else
			if (nombreZona.equals("VENTA"))
				exito = venta.comprar(numeroEntradas);
			else
				if (nombreZona.equals("VIP"))
					exito = vip.comprar(numeroEntradas);
				else
				{
					System.out.println("Zona incorrecta");
					exito =false;
				}

		return exito;
	}
	public static int menu (Scanner sc)
	{
		int opcion;
		
		do
		{
			System.out.println("1 - Mostrar número de entradas");
			System.out.println("2 - Vender entradas");
			System.out.println("3 - Salir");
			System.out.println("Opcion:");
			try
			{
				opcion = sc.nextInt();
				sc.nextLine(); // Limpiar Buffer
			}
			catch (InputMismatchException e)
			{
				opcion = 0;
				sc.nextLine();// Limpiar Buffer
			}
			if (opcion<1 || opcion>3)
				System.out.println("Opción incorrecta");
		} while (opcion<1 || opcion>3);
		return opcion;
	}

}

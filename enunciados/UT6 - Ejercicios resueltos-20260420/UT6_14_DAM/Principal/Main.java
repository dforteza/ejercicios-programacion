package Principal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int opcion;
		RepositorioConductores repositorio = new RepositorioConductores(4);
		boolean exito;
		
		do
		{
			opcion = menu(sc);
			switch (opcion)
			{
				case 1:
					exito = repositorio.alta(sc);
					if (exito==true)
						System.out.println("Se ha añadido un conductor");
					else
						System.out.println("El repositorio está lleno");
					break;
				case 2:
					exito = repositorio.incrementarKmConductor(sc);
					break;
				case 3:
					exito = repositorio.baja(sc);
					if (exito==true)
						System.out.println("Se ha dado de baja correctamente");
					else
						System.out.println("El conductor no existe");
					break;
				case 4:
					exito = repositorio.modificarConductor(sc);
					if (exito==false)
						System.out.println("El conductor no existe");
					break;
					
				case 5:
					System.out.println(repositorio);
					break;
				case 6: 
					break;
				default:
					System.out.println("Opciones válidas 1-6");
			}
		} while (opcion!=6);
		sc.close();
	}
	
	public static int menu(Scanner sc)
	{
		int opcion;
		do
		{
			System.out.println("1 - Alta de conductor");
			System.out.println("2 - Añadir km a un conductor");
			System.out.println("3 - Baja de conductor");
			System.out.println("4 - Modificar nombre de conductor");
			System.out.println("5 - Listar información conductores");
			System.out.println("6 - Salir");
			System.out.println("Opción:");
			try
			{
				opcion = sc.nextInt();
			}
			catch (InputMismatchException e)
			{
				opcion = 0;
			}
			sc.nextLine();
			if (opcion<1 || opcion>6)
				System.out.println("Opción incorrecta");
		} while (opcion<1 || opcion>6);
		return opcion;
	}
}

package Principal;

import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int opcion;
		String respuesta;
		boolean exito;
		Empleado e;
		
		RepositorioEmpleados repo = new RepositorioEmpleados();
		do
		{
			opcion = menu(sc);
			switch (opcion)
			{
			case 1:
				System.out.println("Desea dar de alta un consultor o un comercial:");
				respuesta = sc.nextLine();
				if (respuesta.equals("consultor"))
					exito = repo.alta(new Consultor(sc));
				else
					exito = repo.alta(new Comercial(sc));
				if (exito)
					System.out.println("El empleado ha sido dado de alta");
				else
					System.out.println("El empleado no ha podido añadirse");
				break;
			case 2:
				System.out.println("Introduzca NSS del empleado a eliminar");
				respuesta = sc.nextLine();
				exito = repo.eliminarNSS(respuesta);
				if (exito)
					System.out.println("Empleado eliminado");
				else
					System.out.println("El empleado no existe");
				break;
			case 3:
				System.out.println("Introduzca NSS del empleado a consultar");
				respuesta = sc.nextLine();
				e = repo.buscarNSS(respuesta);
				if (e!=null)
					System.out.println(e);
				else
					System.out.println("El empleado no existe");
				break;
			case 4:
				repo.ordenar();
				break;
			case 5:
				System.out.println(repo);
				break;
			case 6:
			default:
			}
		} while (opcion!=6);
		sc.close();
	}
	
	public static int menu(Scanner sc)
	{
		int opcion;
		do
		{
			System.out.println("MENU DE OPCIONES");
			System.out.println("================");
			System.out.println("1 - Añadir empleado");
			System.out.println("2 - Eliminar un empleado");
			System.out.println("3 - Encontrar un empleado por NSS");
			System.out.println("4 - Ordenar por nombre");
			System.out.println("5 - Listar todos los empleados");
			System.out.println("6 - Salir");
			System.out.println("Opción:");
			
			try {opcion = Integer.parseInt(sc.nextLine());} 
			catch (NumberFormatException e){opcion = 0;}
			
			if (opcion<1 || opcion>6)
				System.out.println("Opción no válida");
		} while(opcion<1 || opcion>6);
		return opcion;
	}

}

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import model.Funcionario;
import model.Servicio;
import model.Vehiculo;
import repository.RepositorioServicio;

public class Main
{
	public static void main(String[] args)
	{
		// Datos maestros: vehículos y funcionarios disponibles en la comisaría
		Vehiculo v[] =
		{
			new Vehiculo("A34"),
			new Vehiculo("A33"),
			new Vehiculo("B65"),
			new Vehiculo("C45"),
			new Vehiculo("D78")
		};

		Funcionario f[] =
		{
			new Funcionario(12, "Ana"),
			new Funcionario(12, "Pepe"),
			new Funcionario(12, "Jaime"),
			new Funcionario(12, "Javier"),
			new Funcionario(12, "Pocholo"),
			new Funcionario(12, "Trump"),
			new Funcionario(12, "Shrek")
		};

		// Al construir el repositorio se generan 15 servicios aleatorios internamente
		RepositorioServicio repositorio = new RepositorioServicio(15, v, f);
		System.out.println(repositorio);

		Scanner sc = new Scanner(System.in);

		System.out.println("Indique la fecha (aa mm dd):");
		LocalDate fecha = LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt());

		System.out.println("Indique la hora (hh mm):");
		LocalTime hora = LocalTime.of(sc.nextInt(), sc.nextInt());

		// Limpia el \n que deja el último nextInt() antes de leer el String
		sc.nextLine();

		System.out.println("Introduzca id del vehiculo:");
		String codigo = sc.nextLine();

		Servicio servicio = repositorio.buscarXFechaHora(codigo, fecha, hora);

		if (servicio == null)
			System.out.println("Ese servicio no existe");
		else
			System.out.println(servicio);

		sc.close();
	}
}

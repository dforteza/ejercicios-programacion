package Principal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		Vehiculo v[] = {
				new Vehiculo("A34"), 
				new Vehiculo("A33"),
				new Vehiculo("B65"),
				new Vehiculo("C45"),
				new Vehiculo("D78")};
		
		Funcionario f[] = {
				new Funcionario(12,"Ana"),
				new Funcionario(12,"Pepe"),
				new Funcionario(12,"Jaime"),
				new Funcionario(12,"Javier"),
				new Funcionario(12,"Pocholo"),
				new Funcionario(12,"Trump"),
				new Funcionario(12,"Shrek")};
		Servicio servicio;
		LocalDate fecha;
		LocalTime hora;
		String codigo;
		
		RepositorioServicio s = new RepositorioServicio(15,v,f);
		System.out.println(s);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Indique la fecha aa/mm/dd:");
		fecha = LocalDate.of(
				sc.nextInt(),
				sc.nextInt(),
				sc.nextInt());
		System.out.println("Indique la hora hh-mm");
		hora = LocalTime.of(
				sc.nextInt(),
				sc.nextInt());
		sc.nextLine(); // Vaciar el buffer
		System.out.println("Introduzca id del vehiculo:");
		codigo = sc.nextLine();
		servicio = s.buscarXFechaHora (codigo,fecha,hora);
		if (servicio==null)
			System.out.println("Ese servicio no existe");
		else
			System.out.println(servicio);
		sc.close();
	}

}

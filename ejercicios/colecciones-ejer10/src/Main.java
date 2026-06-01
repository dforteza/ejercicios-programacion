import java.time.LocalTime;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner (System.in);
		String dia,hora;

		TreeMap<TramoHorario,String> horario = new TreeMap<>();
		
		// ---------------- LUNES ----------------
		horario.put(new TramoHorario("Lunes","15:05"),"Programación");
		horario.put(new TramoHorario("Lunes","16:00"),"Programación");
		horario.put(new TramoHorario("Lunes","16:55"),"Programación");
		horario.put(new TramoHorario("Lunes","18:15"),"FOL");
		horario.put(new TramoHorario("Lunes","19:10"),"Sistemas");
		horario.put(new TramoHorario("Lunes","20:05"),"Sistemas");

		// ---------------- MARTES ----------------
		horario.put(new TramoHorario("Martes","15:05"),"Programación");
		horario.put(new TramoHorario("Martes","16:00"),"Programación");
		horario.put(new TramoHorario("Martes","16:55"),"Programación");
		horario.put(new TramoHorario("Martes","18:15"),"FOL");
		horario.put(new TramoHorario("Martes","19:10"),"Sistemas");
		horario.put(new TramoHorario("Martes","20:05"),"Sistemas");
		
		System.out.println("Introduzca día de la Semana");
		dia = sc.nextLine();
		System.out.println("Introduzca hora:minuto");
		hora = sc.nextLine();
		
		Map.Entry<TramoHorario,String> entrada = horario.floorEntry(new TramoHorario(dia,hora));
		if (entrada!=null)
		{
			TramoHorario tramo = entrada.getKey();
			LocalTime h = LocalTime.parse(hora);
			if (h.isBefore(tramo.getHoraFin()))
				System.out.println("Módulo "+entrada.getValue());
			else
				System.out.println("No hay clase a esa hora");
		}
		else
			System.out.println("No hay clase a esa hora o ese día");
		sc.close();
	}
}
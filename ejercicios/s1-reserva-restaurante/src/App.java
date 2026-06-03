import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

import repository.Reserva;

public class App 
{
    public static void main(String[] args) throws Exception 
    {

        Scanner sc = new Scanner(System.in);
        int opcion;

        // Fecha 23/01/2026, Hora: 21:15, Nombre; Niklaus Wirth, Teléfono: 555 555 555, 2 comensales
        Reserva reserva = new Reserva(
            LocalDate.of(2026, 01, 23),
            LocalTime.of(21, 15),
            "Niklaus Wirth",
            "555 555 555",
            2
        );

        do 
        {
            opcion = menu(sc);

            switch (opcion) 
            {
                case 1:
                    System.out.println("===== CREANDO RESERVA =====");
                    Reserva reserva2 = new Reserva(sc);
                    break;
                case 2:
                    System.out.println("===== ESTABLECIENDO COSTE =====");
                    Float coste = sc.nextFloat();
                    sc.nextLine();
                    reserva.setCoste(coste);
                    // reserva2.setCoste(coste);
                    break;
                case 3:
                    System.out.println("===== MOSTRAR DATOS =====");

                    System.out.println(reserva);
                    break;
                case 4:
                    System.out.println("===== COMPROBAR DIGITOS ORDENDADOS =====");
                    String s = sc.nextLine();
                    System.out.println(asc_string(s));
                    break;
                case 5:
                    System.out.println("Saliendo ...");
                    break;
            }
            
        } while (opcion != 5);


    }

    private static int menu(Scanner sc)
    {
        int opcion;

        do
        {
            System.out.println("\n1. Crear reserva desde teclado");
            System.out.println("2. Establecer coste de la reserva");
            System.out.println("3. Mostrar datos de la reserva");
            System.out.println("4. Comprobar dígitos ordenados");
            System.out.println("5. Salir");
            System.out.print("Opcion: ");

            try
            {
                opcion = sc.nextInt();

            } catch (InputMismatchException ime)
            {
                opcion = -1;
            }
            sc.nextLine();
        } while (opcion < 1 || opcion > 5);

        return (opcion);
    }

    private static boolean asc_string(String s)
    {
        if (s.length() == 1)
            return (true);

        if (!(s.charAt(0) < s.charAt(1)))
            return (false);
        
        return (asc_string(s.substring(1)));
    }
}

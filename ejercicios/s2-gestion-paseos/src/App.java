import java.time.LocalTime;
import java.util.Scanner;

import Model.Paseo;
import Repository.RepositorioPaseos;
import Repository.RepositorioPaseosImpl;

public class App {
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int opcion;

        RepositorioPaseos repo = new RepositorioPaseosImpl(10);

        repo.altaPaseo(new Paseo("Tala", LocalTime.of(10,00), 30F));
        repo.altaPaseo(new Paseo("Pepo", LocalTime.of(11,00), 30F));
        repo.altaPaseo(new Paseo("Rita", LocalTime.of(12,00), 30F));

        do
        {
            opcion = menu(sc);

            switch (opcion) 
            {
                case 1:
                    System.out.println("====== DAR DE ALTA PASEO =====");
                    if (repo.altaPaseo(sc))
                        System.out.println("Paseo aniadido");
                    else
                        System.out.println("Error");
                    break;
                case 2:
                    System.out.println("====== DAR DE BAJA PASEO =====");
                    System.out.println("ID a eliminar: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    if (repo.bajaPaseo(id))
                        System.out.println("Paseo eliminado");
                    else
                        System.out.println("Error");
                    break;
                case 3:
                    System.out.println("====== BUSCAR PASEO =====");
                    System.out.println("Introduzca nombre:");
                    String nombre = sc.nextLine();

                    System.out.println(repo.buscarPaseo(nombre));
                    break;
                case 4:
                    System.out.println("====== MOSTRAR PASEOS =====");
                    repo.mostrarPaseos();
                    break;
                case 5:
                    System.out.println("Saliendo ...");
                    break;
            
            }
            
        } while (opcion != 5);

        sc.close();
    }

    private static int menu(Scanner sc)
    {
        int opcion;

        do
        {
            System.out.println("\n1. Dar de alta un paseo");
            System.out.println("2. Dar de baja un paseo");
            System.out.println("3. Buscar paseo por nombre");
            System.out.println("4. Mostrar todos los paseos");
            System.out.println("5. Salir");
            System.out.print("Opcion: ");

            try
            {
                opcion = sc.nextInt();
            }
            catch (java.util.InputMismatchException e)
            {
                opcion = -1;
            }
            sc.nextLine();

        } while (opcion < 1 || opcion > 5);

        return opcion;
    }
}

package Principal;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeMap;

import Principal.repository.Tienda;
import Principal.repository.TiendaImpl;

public class App
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int     opcion;

        TreeMap<String, Double> catalogo = new TreeMap<>();

        catalogo.put("avena",      2.21);
        catalogo.put("garbanzos",  2.39);
        catalogo.put("tomate",     1.59);
        catalogo.put("jengibre",   3.13);
        catalogo.put("quinoa",     4.50);
        catalogo.put("guisantes",  1.60);

        Tienda tienda = new TiendaImpl(catalogo);

        do
        {
            opcion = menu(sc);
            switch (opcion)
            {
                case 1:
                    tienda.mostrarCatalogo();
                    break;
                case 2:
                    tienda.hacerPedido(sc);
                    break;
                case 3:
                    System.out.print("Código de descuento (Enter para omitir): ");
                    String codigo = sc.nextLine();
                    System.out.printf("TOTAL: %.2f$%n", tienda.calcularTotalConDescuento(codigo));
                    break;
                case 4:
                    System.out.println("Hasta luego.");
                    break;
            }
        } while (opcion != 4);

        sc.close();
    }

    private static int menu(Scanner sc)
    {
        int opcion = 0;

        System.out.println("\n1. Ver catálogo");
        System.out.println("2. Hacer pedido");
        System.out.println("3. Ver total");
        System.out.println("4. Salir");
        System.out.print("Opción: ");

        do
        {
            try
            {
                opcion = sc.nextInt();
            }
            catch (InputMismatchException e)
            {
                sc.nextLine();
                opcion = 0;
                System.out.print("Opción válida (1-4): ");
            }
        } while (opcion < 1 || opcion > 4);

        sc.nextLine();

        return (opcion);
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

public class app2
{
    public static void main(String[] args)
    {
        ArrayList<Integer> lista    = new ArrayList<>();
        HashSet<Integer>   distintos = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        Integer n;

        // --- Entrada de datos ---
        System.out.println("=== Introduzca números (0 para terminar) ===");

        do
        {
            System.out.print("> ");

            try
            {
                n = sc.nextInt();
            }
            catch (InputMismatchException e)
            {
                System.out.println("  Solo se admiten números enteros.");
                sc.nextLine();
                n = -1;
                continue;
            }

            sc.nextLine();
            lista.add(n);
            distintos.add(n);

        } while (n != 0);

        // --- Cálculos ---
        int suma  = 0;

        for (Integer i : lista)
            suma += i;

        double media = (double) suma / lista.size();

        // --- Resultados ---
        System.out.println("\n========== RESULTADOS ==========");
        System.out.println("Suma total : " + suma);
        System.out.printf("Media      : %.2f%n", media);

        System.out.print("Sobre media: ");

        for (Integer i : lista)
        {
            if (i > media)
                System.out.print(i + " ");
        }

        System.out.println();

        // Lista original ordenada
        lista.sort(null);
        System.out.print("Lista orig : ");

        for (Integer i : lista)
            System.out.print(i + " ");

        System.out.println();

        // --- Números distintos ---
        System.out.println("\n===== NÚMEROS SIN DUPLICADOS =====");

        // Opción 1 — TreeSet (se ordena al insertar)
        TreeSet<Integer> opcion1 = new TreeSet<>(distintos);
        System.out.print("TreeSet    : ");

        for (Integer i : opcion1)
            System.out.print(i + " ");

        System.out.println();

        // Opción 2 — ArrayList + Collections.sort
        ArrayList<Integer> opcion2 = new ArrayList<>(distintos);
        Collections.sort(opcion2);
        System.out.print("Sort       : ");

        for (Integer i : opcion2)
            System.out.print(i + " ");

        System.out.println();
        System.out.println("==================================");

        sc.close();
    }
}

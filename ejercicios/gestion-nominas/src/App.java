import java.util.Scanner;

import Model.Administrador;
import Model.Analista;
import Model.Auxiliar;
import Model.Programador;
import Repository.Empresa;

public class App {
    public static void main(String[] args) throws Exception 
    {
        Scanner sc = new Scanner(System.in);
        int     opcion;

        Empresa empresa = new Empresa("1234", "Delogica", 15);
        do 
        {
            opcion = menu(sc);

            switch (opcion) 
            {
                case 1:
                    System.out.println("DAR DE ALTA A TRABAJDOR");
                    empresa.alta(new Analista("204433963W", "Carles", 2800.0, "ML"));
                    empresa.alta(new Analista("204437956R", "Maria", 3000.0, "ML"));
                    empresa.alta(new Programador("204437963W", "Alba", 1200.0, "DAM"));
                    empresa.alta(new Programador("204437964W", "Marti", 1200.0, "DAM"));
                    empresa.alta(new Administrador("20443300A", "Nuria", 1300.0, 5));
                    empresa.alta(new Administrador("20443301A", "Pepe", 1400.0, 6));
                    empresa.alta(new Auxiliar("20443302A", "Jose", 1000.0, 6));
                    empresa.alta(new Auxiliar("20443303A", "Ana", 1100.0, 8));
                    break;
                case 2: 
                    System.out.println("LISTAR EMPLEADOS");
                    System.out.println(empresa);
                    break;
                case 3:
                    System.out.println("saliendo ...");
                    break;
            }
            
        } while (opcion != 3);

        sc.close();
    }

    private static int menu(Scanner sc)
    {
        int opcion;
        do 
        {
            System.out.println("MENU");
            System.out.println("1. Opcion 1");
            System.out.println("2. Opcion 2");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");

            try 
            {
                opcion = sc.nextInt();
            } catch (Exception e) 
            {
                opcion = 0;
            }
            sc.nextLine();
            if (opcion < 1 || opcion > 3)
                System.out.println("Error - opcion incorrecta");
        } while (opcion < 1 || opcion > 3);

        return (opcion);
    }

}

import java.util.InputMismatchException;
import java.util.Scanner;

public class App 
{
    public static void main(String[] args) throws Exception 
    {
        Scanner sc = new Scanner(System.in);
        int     opcion;

        CuentaBancaria cuenta = new CuentaBancaria("ES6621000418401234567891", "Diego");
        do 
        {
            System.out.println();

            opcion = menu(sc);

            System.out.println();
            switch (opcion) 
            {
                case 1:
                    System.out.println("DATOS de la cuenta");
                    System.out.println("==================");

                    System.out.println(cuenta.toString());
                    break;
                case 2:
                    System.out.println("IBAN de la cuenta");
                    System.out.println("==================");
                    
                    System.out.println(cuenta.getIban());
                    break;
                case 3:
                    System.out.println("TITULAR de la cuenta");
                    System.out.println("==================");

                    System.out.println(cuenta.getTitular());
                    break;
                case 4:
                    System.out.println("SALDO de la cuenta");
                    System.out.println("==================");

                    System.out.println(cuenta.getSaldo());
                    break;
                case 5:
                    System.out.println("INGRESO de dinero en la cuenta");
                    System.out.println("==================");

                    double ingreso = sc.nextDouble();

                    cuenta.ingresar(ingreso);
                    System.out.println(cuenta.toString());
                    break;
                case 6:
                    System.out.println("RETIRADA de dinero de la cuenta");
                    System.out.println("==================");

                    double retirada = sc.nextDouble();

                    cuenta.retirar(retirada);
                    System.out.println(cuenta.toString());
                    break;
                case 7:
                    System.out.println("MOVIMIENTOS de la cuenta");
                    System.out.println("==================");

                    for (int i = 0; i < cuenta.getNumeroMovimientos(); i++)
                    {
                        System.out.println("Movimiento "+i+" " +cuenta.getMovimientos()[i]);
                    }
                    System.out.println(cuenta.toString());
                    break;
                case 8:
                    System.out.println("SALIR");
                    System.out.println("==================");
                    break;
            }
            
        } while (opcion != 8);
    }

    private static int menu(Scanner sc)
    {
        int opcion;

        do 
        {
            System.out.println("MENU");
            System.out.println("====");
            System.out.println("1. Mostrar datos de la cuenta");
            System.out.println("2. Mostrar IBAN de la cuenta");
            System.out.println("3. Mostrar titular de la cuenta");
            System.out.println("4. Mostrar saldo de la cuenta");
            System.out.println("5. Ingresar dinero en la cuenta");
            System.out.println("6. Retirar dinero de la cuenta");
            System.out.println("7. Mostrar movimientos de la cuenta");
            System.out.println("8. Salir");
            try 
            {
                System.out.print("Opcion: ");
                opcion = sc.nextInt();
                sc.nextLine();

            } catch (InputMismatchException e) 
            {
                opcion = 0;
                sc.nextLine();
            }
            if (opcion < 1 ||opcion > 8)
                System.out.println("ERROR");
        } while (opcion < 1 || opcion > 8);

        return (opcion);
    }
}

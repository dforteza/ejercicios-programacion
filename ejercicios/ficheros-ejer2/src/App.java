import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception 
    {
        File datos       = new File("datos.txt");
        File informacion = new File("informacion.txt");
        Scanner sc = new Scanner(System.in);

        if (!datos.createNewFile())
        {
            datos.renameTo(informacion);
            System.out.println("datos.txt renombrado a informacion.txt");
        }
        else
        {
            System.out.println("datos.txt creado");
        }

        System.out.print("¿Eliminar informacion.txt? (s/n): ");
        String del = sc.nextLine();
        if (del.equals("s"))
        {
            if (informacion.delete())
                System.out.println("informacion.txt eliminado");
            else
                System.out.println("No se pudo eliminar (puede que no exista aún)");
        }


        sc.close();
    }
}

import java.io.File;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce la ruta del directorio: ");
        String ruta = sc.nextLine();

        File f = new File(ruta);

        if (!f.isDirectory())
        {
            System.out.println("Error - no has introducido un directorio válido");
        }
        else
        {
            File[] lisFiles = f.listFiles();

            int cont_dir = 0;
            int cont_arch = 0;

            for (int i = 0; i < lisFiles.length; i++)
            {
                if (lisFiles[i].isDirectory())
                {
                    System.out.println("[DIR]  - " + lisFiles[i].getName());
                    cont_dir++;
                }
                else if (lisFiles[i].isFile())
                {
                    System.out.println("[ARCH] - " + lisFiles[i].getName());
                    cont_arch++;
                }
            }

            System.out.println("\nArchivos:      " + cont_arch);
            System.out.println("Subdirectorios: " + cont_dir);
        }

        sc.close();
    }
}

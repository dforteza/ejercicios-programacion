import java.io.File;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce la ruta del directorio: ");
        String ruta = sc.nextLine();

        File dir = new File(ruta);

        if (!dir.isDirectory())
        {
            System.out.println("Ruta no valida");
        }
        else
        {
            long total = calcularTamanio(dir);
            System.out.println("Tamanio total: " + total + " bytes");

            int profundidad = calcularProfundidad(dir);
            System.out.println("Profundidad: " + profundidad);

        }

        sc.close();
    }

    public static long calcularTamanio(File directorio)
    {
        long total = 0;

        for (File f : directorio.listFiles())
        {
            if (f.isFile())
                total += f.length();
            else if (f.isDirectory())
                total += calcularTamanio(f);  // llamada recursiva
        }

        return (total);
    }

    public static int calcularProfundidad(File directorio)
    {
        int max = 0;

        for (File f : directorio.listFiles())
        {
          if (f.isDirectory())
          {
              int profundidad = 1 + calcularProfundidad(f);
              if (profundidad > max)
                  max = profundidad;
          }
        }

        return (max);
    }

}

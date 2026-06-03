import java.io.File;
import java.util.Scanner;

public class app2 
{

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduzca ruta: ");
        String ruta = sc.nextLine();

        File dir = new File(ruta);

        if (!dir.exists() && !dir.isDirectory())
            System.out.println("ruta invalida");
        else
        {
            Long length = calcularBytes(dir);

            String s = String.format("Longitud Bytes %d", length);
            System.out.println(s);

            int profundidad = calcularProfundidad(dir);

            String s2 = String.format("Profundidad %d",profundidad);
            System.out.println(s2);
        }

        sc.close();
    }

    private static Long calcularBytes(File dir)
    {
        File[] files = dir.listFiles();
        Long length = 0L;


        for (File f : files)
        {
            if (f.isFile())
            {
                System.out.println("Entro a "+ f);
                length += f.length();
            }
            else
                length += calcularBytes(f);

        }

        return (length);
    } 

    private static int calcularProfundidad(File dir)
    {
        File[] files = dir.listFiles();
        int profundidad;
        int profundidad_max = 0;

        for (File f : files)
        {
            if (f.isFile())
                profundidad_max = 1;
            else
            {
                profundidad = 1 + calcularProfundidad(f);
                if (profundidad > profundidad_max)
                    profundidad_max = profundidad;
            }
        }

        return (profundidad_max);

    }
    
}

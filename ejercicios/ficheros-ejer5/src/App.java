import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class App
{
    public static void main(String[] args) throws Exception
    {
        // --- Escritura ---
        FileWriter fw = new FileWriter("numeros.txt");
        PrintWriter pw = new PrintWriter(fw);

        for (int i = 0; i < 10; i++)
        {
            double numero = Math.random() * 100;
            pw.println(numero);
        }

        pw.close();
        fw.close();

        // --- Lectura y cálculos ---
        Scanner sc = new Scanner(new File("numeros.txt"));

        double max    = Double.MIN_VALUE;
        double min    = Double.MAX_VALUE;
        double suma   = 0;
        double[] nums = new double[10];
        int i = 0;

        while (sc.hasNextDouble())
        {
            double n = sc.nextDouble();
            nums[i++] = n;
            suma += n;
            if (n > max)
                max = n;
            if (n < min)
                min = n;
        }

        sc.close();

        double media = suma / 10;

        double masCercano = nums[0];
        for (double n : nums)
        {
            if (Math.abs(n - media) < Math.abs(masCercano - media))
                masCercano = n;
        }

        System.out.printf("Maximo:           %.2f%n", max);
        System.out.printf("Minimo:           %.2f%n", min);
        System.out.printf("Media:            %.2f%n", media);
        System.out.printf("Mas cercano:      %.2f%n", masCercano);
    }
}

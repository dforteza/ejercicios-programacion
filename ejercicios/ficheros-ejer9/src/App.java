import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class App
{
    public static void main(String[] args) throws Exception
    {
        Scanner scA = new Scanner(new File("a.txt"));
        Scanner scB = new Scanner(new File("b.txt"));

        FileWriter fw = new FileWriter("combinado.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        while (scA.hasNext() || scB.hasNext())
        {
            if (scA.hasNext())
                bw.write(scA.next() + " ");
            if (scB.hasNext())
                bw.write(scB.next() + " ");
        }

        bw.close();
        fw.close();
        scA.close();
        scB.close();

        System.out.println("Fichero combinado.txt generado.");
    }
}

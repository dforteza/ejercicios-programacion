import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class App
{
    static final int DESPLAZAMIENTO = 3;

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Encriptar");
        System.out.println("2. Desencriptar");
        System.out.print("Opcion: ");
        int opcion = sc.nextInt();
        sc.close();

        File entrada = new File("mensaje.txt");

        try
        {
            FileReader fr = new FileReader(entrada);
            BufferedReader br = new BufferedReader(fr);

            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null)
                sb.append(linea).append("\n");

            br.close();
            fr.close();

            String resultado;
            String ficheroSalida;

            if (opcion == 1)
            {
                resultado    = cifrar(sb.toString(), DESPLAZAMIENTO);
                ficheroSalida = "encriptado.txt";
            }
            else
            {
                resultado    = cifrar(sb.toString(), 26 - DESPLAZAMIENTO);
                ficheroSalida = "desencriptado.txt";
            }

            FileWriter fw = new FileWriter(ficheroSalida);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(resultado);
            bw.close();
            fw.close();

            System.out.println("Guardado en " + ficheroSalida);

        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String cifrar(String texto, int desplazamiento)
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < texto.length(); i++)
        {
            char c = texto.charAt(i);

            if (c >= 'a' && c <= 'z')
                sb.append((char) ('a' + (c - 'a' + desplazamiento) % 26));
            else if (c >= 'A' && c <= 'Z')
                sb.append((char) ('A' + (c - 'A' + desplazamiento) % 26));
            else
                sb.append(c);
        }

        return sb.toString();
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class App
{
    public static void main(String[] args) throws Exception
    {
        File f = new File("texto.txt");

        if (f.exists() && f.isFile())
        {
            // --- Lectura ---
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null)
                sb.append(linea).append("\n");

            br.close();
            fr.close();

            String texto = sb.toString();

            if (texto.isEmpty())
            {
                System.out.println("El fichero está vacío.");
                return;
            }

            // --- Corrección ---
            StringBuilder corregido = new StringBuilder(texto);

            for (int i = 0; i < corregido.length() - 2; i++)
            {
                char actual    = corregido.charAt(i);
                char siguiente = corregido.charAt(i + 1);

                // Punto seguido de espacio: poner mayúscula en la letra tras el espacio
                if (actual == '.' && siguiente == ' ')
                {
                    char letra = corregido.charAt(i + 2);
                    if (letra >= 'a' && letra <= 'z')
                        corregido.setCharAt(i + 2, Character.toUpperCase(letra));
                }

                // Coma sin espacio: insertar espacio tras la coma
                if (actual == ',' && siguiente != ' ')
                    corregido.insert(i + 1, ' ');
            }

            // --- Escritura ---
            FileWriter fw = new FileWriter("Corregido.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(corregido.toString());
            bw.close();
            fw.close();

            System.out.println("Fichero corregido guardado en Corregido.txt");
            System.out.println(corregido.toString());
        }
    }
}

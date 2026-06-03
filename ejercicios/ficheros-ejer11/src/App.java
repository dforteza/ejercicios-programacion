import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class App 
{
    public static void main(String[] args) throws Exception 
    {
        File f = new File("texto.txt");
        Map<String, Integer> map = new HashMap<>();

        if (!f.exists() || !f.isFile())
            System.out.println("Error - archivo incorrect");
        else
        {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            int cont_lineas = 0;
            int cont_char   = 0;
            int cont_palabras = 0;

            while ((linea = br.readLine()) != null)
            {
                cont_lineas++;
                cont_char += linea.length();
                cont_palabras += linea.split("\\s+").length;

                for (String p : linea.split("\\s+"))
                    map.put(p, map.getOrDefault(p, 0) + 1);
            }

            br.close();
            fr.close();

            System.out.println("Lineas:   " + cont_lineas);
            System.out.println("Palabras: " + cont_palabras);
            System.out.println("Caracteres: " + cont_char);


        }
    }
}

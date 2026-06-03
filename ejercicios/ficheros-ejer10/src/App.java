import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception 
    {
        List<Alumno> lista = new LinkedList<>();
        File f = new File("alumnos_notas.txt");

        try 
        {    
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);


            String linea;
            while ((linea = br.readLine()) != null)
            {
                String[] datos = linea.split(";");

                int i = 1;
                Double sum = 0.0;
                while (i < datos.length)
                {
                    sum += Double.parseDouble(datos[i]);
                    i++;
                }

                Double media = sum / (datos.length - 1);
                lista.add(new Alumno(datos[0], media));
            }

            br.close();
            fr.close();

            Collections.sort(lista);

            for (Alumno a : lista)
                System.out.printf("%-20s %.2f%n", a.getNombre(), a.getMedia());

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        


    }
}

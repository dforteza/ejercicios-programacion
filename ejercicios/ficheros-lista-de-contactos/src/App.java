import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        List<Contacto> lista = new ArrayList<>();
        File f = new File("contactos.txt");

        // --- Lectura ---
        if (f.exists())
        {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null)
            {
                String[] contacto = linea.split(";");
                if (contacto.length == 3)
                {
                    System.out.println("Contacto añadido");
                    lista.add(new Contacto(contacto[0], contacto[1], contacto[2]));
                }

            }


            fr.close();
            br.close();
        }

        // --- Menú ---
        int opcion;
        do
        {
            System.out.println("\n1. Añadir contacto");
            System.out.println("2. Mostrar contactos");
            System.out.println("3. Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion)
            {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Telefono: ");
                    String telefono = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    lista.add(new Contacto(nombre, telefono, email));
                    System.out.println("Contacto añadido.");
                    break;

                case 2:
                    if (lista.isEmpty())
                        System.out.println("No hay contactos.");
                    else
                        for (Contacto c : lista)
                            System.out.println(c.getNombre() + " | " + c.getTelefono() + " | " + c.getEmail());
                    break;

                case 3:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 3);

        // --- Escritura ---
        FileWriter fw = new FileWriter(f);
        for (Contacto c : lista)
            fw.write(c.toString() + "\n");
        fw.close();

        sc.close();
    }
}

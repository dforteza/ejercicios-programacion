import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App
{
    static final String FICHERO = "puntuaciones.dat";
    static ArrayList<Jugador> lista = new ArrayList<>();

    public static void main(String[] args)
    {
        // Crear el fichero si no existe
        try
        {
            File f = new File(FICHERO);
            f.createNewFile();
        }
        catch (Exception e) { e.printStackTrace(); }

        lista = (ArrayList<Jugador>) leerTodos();

        Scanner sc = new Scanner(System.in);
        int opcion;

        do
        {
            System.out.println("\n1. Registrar jugador");
            System.out.println("2. Mostrar puntuaciones");
            System.out.println("3. Actualizar puntuacion");
            System.out.println("4. Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion)
            {
                case 1: registrar(sc);   break;
                case 2: mostrar();       break;
                case 3: actualizar(sc);  break;
                case 4: guardarTodos(lista); System.out.println("Saliendo..."); break;
                default: System.out.println("Opcion no valida.");
            }

        } while (opcion != 4);

        sc.close();
    }

    public static void registrar(Scanner sc) 
    {
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();

        System.out.println("Puntuacion: ");
        int puntuacion = sc.nextInt();
        sc.nextLine();

        lista.add(new Jugador(nombre, puntuacion));
        System.out.println("Jugador aniadido");
    }

    public static void mostrar()               
    {
        if (lista.isEmpty())
            System.out.println("lista vacia");
        else
        {
            for (Jugador j : lista)
                System.out.println(j);
        }
        
    }

    public static void actualizar(Scanner sc)  {
        // campo
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();

        // recorrer
        for (Jugador j : lista)
        {
            if (nombre.equals(j.getNombre()))
            {
                System.out.println("Nueva puntucaion:");
                int puntuacion = sc.nextInt();
                sc.nextLine();
                j.setPuntuacion(puntuacion);
            }
        }
    }

    public static List<Jugador> leerTodos()
    {
        List<Jugador> resultado = new ArrayList<>();

        try
        {
            FileInputStream  fis = new FileInputStream(FICHERO);
            DataInputStream  dis = new DataInputStream(fis);

            while (true)
            {
                try
                {
                    String nombre     = dis.readUTF();
                    int    puntuacion = dis.readInt();
                    resultado.add(new Jugador(nombre, puntuacion));
                }
                catch (EOFException eof) { break; }  // fin de fichero
            }

            dis.close();
            fis.close();
        }
        catch (Exception e) { e.printStackTrace(); }

        return resultado;
    }

    public static void guardarTodos(List<Jugador> lista)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(FICHERO);  // sobreescribe
            DataOutputStream dos = new DataOutputStream(fos);

            for (Jugador j : lista)
            {
                dos.writeUTF(j.getNombre());
                dos.writeInt(j.getPuntuacion());
            }

            dos.close();
            fos.close();
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App2
{
    static final String FICHERO = "puntuaciones2.dat";

    public static void main(String[] args)
    {
        try { new File(FICHERO).createNewFile(); }
        catch (Exception e) { e.printStackTrace(); }

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
                case 1: registrar(sc);  break;
                case 2: mostrar();      break;
                case 3: actualizar(sc); break;
                case 4: System.out.println("Saliendo..."); break;
                default: System.out.println("Opcion no valida.");
            }

        } while (opcion != 4);

        sc.close();
    }

    public static void registrar(Scanner sc)
    {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Puntuacion: ");
        int puntuacion = sc.nextInt();
        sc.nextLine();

        try
        {
            // append = true para no borrar los jugadores anteriores
            FileOutputStream fos = new FileOutputStream(FICHERO, true);
            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeUTF(nombre);
            dos.writeInt(puntuacion);

            dos.close();
            fos.close();
            System.out.println("Jugador registrado.");
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    public static void mostrar()
    {
        try
        {
            FileInputStream fis = new FileInputStream(FICHERO);
            DataInputStream dis = new DataInputStream(fis);

            boolean hayJugadores = false;

            while (true)
            {
                try
                {
                    String nombre     = dis.readUTF();
                    int    puntuacion = dis.readInt();
                    System.out.printf("%-20s %d pts%n", nombre, puntuacion);
                    hayJugadores = true;
                }
                catch (EOFException eof) { break; }
            }

            if (!hayJugadores)
                System.out.println("No hay jugadores registrados.");

            dis.close();
            fis.close();
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    public static void actualizar(Scanner sc)
    {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        // 1 - leer todos a memoria
        List<Jugador> lista = new ArrayList<>();

        try
        {
            FileInputStream fis = new FileInputStream(FICHERO);
            DataInputStream dis = new DataInputStream(fis);

            while (true)
            {
                try
                {
                    String n = dis.readUTF();
                    int    p = dis.readInt();
                    lista.add(new Jugador(n, p));
                }
                catch (EOFException eof) { break; }
            }

            dis.close();
            fis.close();
        }
        catch (Exception e) { e.printStackTrace(); return; }

        // 2 - modificar en memoria
        boolean encontrado = false;
        for (Jugador j : lista)
        {
            if (j.getNombre().equals(nombre))
            {
                System.out.print("Nueva puntuacion: ");
                j.setPuntuacion(sc.nextInt());
                sc.nextLine();
                encontrado = true;
            }
        }

        if (!encontrado) { System.out.println("Jugador no encontrado."); return; }

        // 3 - reescribir el fichero entero
        try
        {
            FileOutputStream fos = new FileOutputStream(FICHERO); // sin append
            DataOutputStream dos = new DataOutputStream(fos);

            for (Jugador j : lista)
            {
                dos.writeUTF(j.getNombre());
                dos.writeInt(j.getPuntuacion());
            }

            dos.close();
            fos.close();
            System.out.println("Puntuacion actualizada.");
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}

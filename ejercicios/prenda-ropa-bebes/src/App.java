import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Model.Prenda;
import Model.PrendaDeportiva;
import Repository.PrendaRepositorioImpl;

public class App {
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int opcion;

        PrendaRepositorioImpl repo = new PrendaRepositorioImpl();

        File f = new File("prendas.txt");

        if (f.createNewFile())
            System.out.println("Fichero creado");
        else
            repo.leerDatos(f);

        do
        {
            opcion = menu(sc);

            switch (opcion)
            {
                case 1:
                    System.out.print("Tipo (1-Prenda / 2-Deportiva): ");
                    int tipo = Integer.parseInt(sc.nextLine());

                    System.out.print("Nombre: ");       String nombre      = sc.nextLine();
                    System.out.print("Talla: ");        String talla       = sc.nextLine();
                    System.out.print("Color: ");        String color       = sc.nextLine();
                    System.out.print("Composicion: ");  String composicion = sc.nextLine();
                    System.out.print("Descripcion: ");  String descripcion = sc.nextLine();

                    if (tipo == 1)
                    {
                        repo.alta(new Prenda(nombre, talla, color, composicion, descripcion));
                    }
                    else
                    {
                        System.out.print("Deporte: ");      String deporte      = sc.nextLine();
                        System.out.print("Marca: ");        String marca        = sc.nextLine();
                        System.out.print("Personalizada (true/false): "); boolean personalizada = Boolean.parseBoolean(sc.nextLine());

                        repo.alta(new PrendaDeportiva(nombre, talla, color, composicion, descripcion, deporte, marca, personalizada));
                    }
                    System.out.println("Prenda añadida.");
                    break;

                case 2:
                    System.out.print("Nombre a eliminar: ");
                    repo.deleteByNombre(sc.nextLine());
                    System.out.println("Prenda(s) eliminada(s).");
                    break;

                case 3:
                    repo.findAll();
                    break;

                case 4:
                    System.out.print("Literal a buscar: ");
                    List<Prenda> resultado = repo.find(sc.nextLine());
                    if (resultado.isEmpty())
                        System.out.println("No se encontraron prendas.");
                    else
                        resultado.forEach(System.out::println);
                    break;

                case 5:
                    Map<String, Integer> frecuencia = repo.frecuenciaPrenda();
                    if (frecuencia.isEmpty())
                        System.out.println("No hay prendas deportivas.");
                    else
                        frecuencia.forEach((deporte, cantidad) -> System.out.println(deporte + ": " + cantidad));
                    break;

                case 6:
                    repo.escribirDatos(f);
                    System.out.println("Saliendo...");
                    break;
            }

        } while (opcion != 6);

        sc.close();
    }

    static int menu(Scanner sc)
    {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Alta prenda");
        System.out.println("2. Baja por nombre");
        System.out.println("3. Listar todas");
        System.out.println("4. Buscar por literal");
        System.out.println("5. Frecuencia por deporte");
        System.out.println("6. Salir");
        System.out.print("Opcion: ");

        return Integer.parseInt(sc.nextLine());
    }
}

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import Model.Producto;
import Repository.ProductoRepository;
import Repository.ProductoRepositoryImpl;

public class App 
{
    public static void main(String[] args) throws Exception 
    {
        Scanner sc = new Scanner(System.in);
        int opcion;
        int id;

        File f = new File("fichero.bat");

        ProductoRepository repo = new ProductoRepositoryImpl();

        if (f.createNewFile())
            System.out.println("fichero creado");
        else
            repo.cargarDesdeFichero(f);
        do 
        {
            opcion = menu(sc);

            switch (opcion) 
            {
                case 1:
                    System.out.println("Agregar Producto");
                    
                    Producto p = new Producto(sc);
                    repo.save(p);
                    
                    break;
                case 2:
                    System.out.println("Listar Producto");
                    
                    repo.findAll();
                    
                    break;
                case 3:
                    System.out.println("Buscar un Producto");

                    System.out.print("ID: ");
                    id = sc.nextInt(); sc.nextLine();

                    repo.findById(id).ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Producto no encontrado")
                    );
                    break;
                case 4:
                    System.out.println("Actualizar stock");

                    
                    System.out.print("ID: ");
                    id = sc.nextInt(); sc.nextLine();

                    System.out.print("NUEVA CANTIDAD: ");
                    int cantidad = sc.nextInt(); sc.nextLine();

                    repo.update(id, cantidad);
                    break;
                case 5:
                    System.out.println("Eliminar un Producto");
            
                    System.out.print("ID: ");
                    id = sc.nextInt(); sc.nextLine();

                    repo.delete(id);

                    break;
                case 6:
                    System.out.println("Saliendo");
                    repo.guardarEnFichero(f);
                    break;
            }
            
        } while (opcion != 6);

        sc.close();
    }

    private static int menu(Scanner sc)
    {
        int opcion;

        do 
        {
            System.out.println("\n===== GESTIÓN DE PRODUCTOS =====");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Actualizar stock");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            
            try 
            {
                opcion = sc.nextInt();
                
            } catch (InputMismatchException e) 
            {
                opcion = -1;
                System.out.println("Opcion invalida o incorrecta");
            }
            sc.nextLine();            
        } while (opcion < 1 ||opcion > 6);
        
        return (opcion);
    }
}

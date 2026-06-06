import java.io.File;
import java.util.Scanner;

import Repository.Equipos;
import Repository.EquiposImpl;

public class App {
    public static void main(String[] args) throws Exception 
    {
        Scanner sc = new Scanner(System.in);
        int opcion;

        Equipos repo = new EquiposImpl();

        File f = new File("Clasificacion.bin");
        
        System.out.println("Leer archivo binario");
        
        if (f.createNewFile())
            System.out.println("Archivo creado - nada que leer");
        else
            repo.readBinary(f);

        do 
        {
            opcion = menu(sc);

            switch (opcion) 
            {
                case 1:
                    repo.save(sc);
                    
                    break;
                case 2:
                    System.out.println("> Codigo a elimianr: ");
                    repo.delete(sc.nextInt());
                    sc.nextLine();
                    
                    break;
                case 3:
                    System.out.println("> Nombre a buscar: ");
                    repo.findAllByName(sc.nextLine()).forEach(System.out::println);

                    break;
                case 4:
                    System.out.println("EQUIPOS ORDENADOS POR PUNTUACION DECRECIENTE");
                    repo.show();
                    
                    break;
                case 5:
                    System.out.println("EQUIPOS ORDENADOS ALFABETICAMENTE");
                    repo.ordenarAlph();
                    
                    break;
                case 6:
                    System.out.println("Importar desde fichero de texto:");
                    repo.leerArchivoTxt(new File("ClasificacionFutbol.txt"));
                    
                    break;
                case 7:
                    System.out.println("Saliendo ...");
                    repo.writeBinary(f);
                    break ;
            }
            
        } while (opcion != 7);

        
    }

    private static int menu(Scanner sc)
    {
        System.out.println("\n1-Añadir Equipo");
        System.out.println("2-Eliminar equipo por código");
        System.out.println("3-Buscar equipo por nombre aproximado");
        System.out.println("4-Listar clasificación ordenada por puntuación decreciente");
        System.out.println("5-Listar equipos por orden alfabético");
        System.out.println("6-Importar equipos desde fichero de texto");
        System.out.println("7-Salir");
        System.out.print("> ");
        int op = sc.nextInt();
        sc.nextLine();
        return op;
    }


}

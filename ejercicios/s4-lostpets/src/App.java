import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Model.Mascota;
import Model.Perro;
import Repository.LostPetRepository;
import Repository.LostPetRepositoryImpl;

public class App {
    public static void main(String[] args) throws Exception
    {
        LostPetRepository repositorio = new LostPetRepositoryImpl();

        repositorio.alta(new Mascota("Luna","Gato","Gata blanca con manchas negras",LocalDate.of(2024, 3, 10), "600123123"));
        repositorio.alta(new Mascota("Sol","Gato","Gato negro con manchas blancas",LocalDate.of(2024, 3, 10), "600123123"));
        repositorio.alta(new Mascota("Tierra","Canario","Canario amarillo y verde",LocalDate.of(2024, 3, 10), "600123123"));
        repositorio.alta(new Mascota("Luna","Periquito","Periquito azul muy sociable",LocalDate.of(2024, 3, 18), "699888777"));
        repositorio.alta(new Mascota("Kira","Gato","Gata atigrada muy asustadiza",LocalDate.of(2024, 3, 12), "611222333"));
        repositorio.alta(new Mascota("Toby","Conejo","Conejo marrón con orejas largas",LocalDate.of(2024, 3, 18), "622333444"));
        repositorio.alta(new Perro("Rocky","Perro","Perro grande y fuerte",LocalDate.of(2024, 3, 10), "633444555","Pastor Alemán", true));
        repositorio.alta(new Perro("Max","Perro","Muy sociable y juguetón",LocalDate.of(2024, 3, 12), "644555666","Labrador", false));
        repositorio.alta(new Perro("Rocky","Perro","Perro grande y fuerte",LocalDate.of(2024, 3, 20), "633444555","Pastor Alemán", true));
        
        // Mostrar todas las mascotas
        System.out.println("=== TODAS LAS MASCOTAS ===");
        System.out.println(repositorio);

        // Consultar por nombre "Luna"
        System.out.println("=== BUSQUEDA POR NOMBRE: Luna ===");
        System.out.println(repositorio.consultaByNombre("Luna"));

        // Ordenar por nombre (orden natural)
        System.out.println("=== ORDENADAS POR NOMBRE ===");
        repositorio.ordenar();
        System.out.println(repositorio);

        // Ordenar por fecha
        System.out.println("=== ORDENADAS POR FECHA ===");
        repositorio.ordenarByFecha();
        System.out.println(repositorio);

        // Eliminar duplicados
        System.out.println("=== TRAS ELIMINAR DUPLICADOS ===");
        repositorio.eliminarDuplicados();
        System.out.println(repositorio);

        // Contar por especie
        System.out.println("=== MASCOTAS POR ESPECIE ===");
        HashMap<String, Integer> map = repositorio.contarPorEspecie();

        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        // Agrupar por fecha
        System.out.println("=== AGRUPADAS POR FECHA ===");
        TreeMap<LocalDate, List<Mascota>> map2 = repositorio.agruparPorFecha();

        for (Map.Entry<LocalDate, List<Mascota>> entry : map2.entrySet())
        {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        // Baja por teléfono
        System.out.println("=== TRAS BAJA POR TELEFONO 633444555 ===");
        repositorio.bajaBytTelefono("633444555");
        System.out.println(repositorio);




    }
}

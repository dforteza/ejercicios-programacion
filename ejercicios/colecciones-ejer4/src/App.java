import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class App
{
    public static void main(String[] args)
    {
        LinkedList<String> paises = new LinkedList<>(List.of(
            "Japón", "Sudáfrica", "Canadá", "Brasil",
            "Australia", "Argentina", "India", "Dinamarca"
        ));

        LinkedList<String> capitales = new LinkedList<>(List.of(
            "Tokio", "Pretoria", "Ottawa", "Brasilia",
            "Camberra", "Buenos Aires", "Nueva Delhi", "Copenhague"
        ));

        // Intercalar: país, capital, país, capital...
        for (int i = 0; i < capitales.size(); i++)
        {
            paises.add((i * 2) + 1, capitales.get(i));
        }

        System.out.println("Lista intercalada:");
        System.out.println(paises);

        char c = 'c';

        // --- Versión 1: con Iterator ---
        LinkedList<String> conIterator = new LinkedList<>(paises);

        Iterator<String> it = conIterator.iterator();

        while (it.hasNext())
        {
            String e = it.next();

            if (e.toLowerCase().startsWith(String.valueOf(c)))
            {
                System.out.println("[Iterator] Eliminado: " + e);
                it.remove();
            }
        }

        System.out.println("Resultado con Iterator:");
        System.out.println(conIterator);

        // --- Versión 2: con Stream ---
        List<String> conStream = paises.stream()
            .filter(e -> !e.toLowerCase().startsWith(String.valueOf(c)))
            .collect(Collectors.toList());

        System.out.println("Resultado con Stream:");
        System.out.println(conStream);
    }
}

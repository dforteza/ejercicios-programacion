import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App
{
    public static void main(String[] args)
    {
        List<Persona> personas = new ArrayList<>(List.of(
            new Persona("Diego", 20),
            new Persona("Ana", 35),
            new Persona("Carlos", 28),
            new Persona("Beatriz", 22),
            new Persona("Marcos", 41),
            new Persona("Laura", 19),
            new Persona("Sofía", 33),
            new Persona("Pablo", 25)));

        Collections.sort(personas);
        System.out.println("Por edad:");
        personas.forEach(p -> System.out.println(p.getNombre() + " - " + p.getEdad()));

        Collections.sort(personas, new ComparadorNombre());
        System.out.println("\nPor nombre:");
        personas.forEach(p -> System.out.println(p.getNombre() + " - " + p.getEdad()));
    }
}

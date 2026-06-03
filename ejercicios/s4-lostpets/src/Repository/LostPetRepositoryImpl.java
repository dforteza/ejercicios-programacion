package Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import Model.Mascota;

public class LostPetRepositoryImpl implements LostPetRepository
{
    private List<Mascota> mascotas;

    public LostPetRepositoryImpl() {
        this.mascotas = new ArrayList<>();
    }

    @Override
    public boolean alta(Mascota m)
    {
        if (!mascotas.add(m))
            return (false);      
        return (true);
    }

    @Override
    public boolean baja(Mascota m) 
    {
        if (!mascotas.remove(m))
            return (false);
        return (true);
    }

    @Override
    public boolean bajaBytTelefono(String tlf)
    {
        boolean flag = false;
        Iterator<Mascota> it = mascotas.iterator();

        while (it.hasNext())
        {
            Mascota m = it.next();
            if (tlf.equals(m.getTlf()))
            {
                it.remove();
                flag = true;
            }
        }
        return (flag);
    }

    @Override
    public List<Mascota> consultaByNombre(String nombre)
    {
        List<Mascota> lista = new ArrayList<>();

        for (Mascota m : mascotas)
        {
            if (nombre.equals(m.getNombre()))
                lista.add(m);

        }
        return (lista);
    }

    @Override
    public void ordenar()
    {
        Collections.sort(mascotas);        
    }

    @Override
    public void ordenarByFecha()
    {
        Collections.sort(mascotas, Comparator.comparing(Mascota::getFechaDesaparicion));        
    }

    
    @Override
    public void eliminarDuplicados()
    {
        Set<Mascota> mascotasSinDup = mascotas.stream()
        .collect(Collectors.toSet());
        
        mascotas = new ArrayList<>(mascotasSinDup);
    }

    

    @Override
    public HashMap<String, Integer> contarPorEspecie()
    {
        HashMap<String, Integer> map = new HashMap<>();

        for (Mascota m : mascotas)
        {
            map.put(m.getEspecie(), map.getOrDefault(m.getEspecie(), 0) + 1);
        }

        return (map);
    }

    

    @Override
    public TreeMap<LocalDate, List<Mascota>> agruparPorFecha()
    {
        TreeMap<LocalDate, List<Mascota>> map = new TreeMap<>();
        for (Mascota m : mascotas)
        {
            if (!map.containsKey(m.getFechaDesaparicion()))
            {
                List<Mascota> lista = new ArrayList<>();
                lista.add(m);
                map.put(m.getFechaDesaparicion(), lista); 
            }
            else
            {
                map.get(m.getFechaDesaparicion()).add(m);
            }

        }

        return (map);
    }

    @Override
    public String toString()
    {
        if (mascotas.isEmpty())
            return "No hay mascotas registradas.";

        StringBuilder sb = new StringBuilder();
        sb.append("--- LISTA DE MASCOTAS ---\n");
        for (Mascota m : mascotas)
            sb.append(m).append("\n");
        return sb.toString();
    }
}

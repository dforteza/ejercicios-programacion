package Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import Model.Equipo;
import Model.EquipoFilial;

public class EquiposImpl implements Equipos
{
    private List<Equipo> equipos;

    public EquiposImpl() {
        this.equipos = new ArrayList<>();
    }

    @Override
    public boolean save(Scanner sc)
    {
        System.out.print("¿Tipo? (1-Equipo, 2-Filial): ");
        int tipo = sc.nextInt();
        sc.nextLine();

        if (tipo == 1)
            return equipos.add(new Equipo(sc));
        else
            return equipos.add(new EquipoFilial(sc));
    }

    @Override
    public void delete(int codigo) 
    {
      Optional<Equipo> equipo = findById(codigo);

      equipo.ifPresent(e -> equipos.remove(e));

    }
    
    @Override
    public Optional<Equipo> findById(int id) 
    {
        for (Equipo e : equipos)
            if (e.getCodigo() == id)
                return (Optional.of(e)); 
        return (Optional.empty());
    }

    @Override
    public List<Equipo> findAllByName(String name) 
    {
        List<Equipo> listaFiltrada = new ArrayList<>();

        for (Equipo e : equipos)
            if (e.contiene(name))
                listaFiltrada.add(e);
        return (listaFiltrada);
    }

    // listar es solo ordenar y devolver?
    @Override
    public void show() 
    {
        equipos.stream()
            .sorted()
            .forEach(e -> System.out.println(e));        
    }

    @Override
    public void ordenarAlph() 
    {
        equipos.stream()
            .sorted(Comparator.comparing(Equipo::getNombre))
            .forEach(System.out::println);    
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readBinary(File f) 
    {
        try 
        {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            try
            {
                equipos = (List<Equipo>) ois.readObject();
                int maxCodigo = equipos
                                .stream()
                                .mapToInt(Equipo::getCodigo)
                                .max()
                                .orElse(0);
                Equipo.setSecuencia(maxCodigo);
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }

            ois.close();
            fis.close();
        } catch (IOException e) 
        {
            e.printStackTrace();
        }        
    
    
    }

    @Override
    public void writeBinary(File f) 
    {
        try 
        {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(equipos);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void leerArchivoTxt(File f) 
    {
        try 
        {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            String linea;
            while ((linea = br.readLine()) != null)
            {
                String[] datos = linea.split(";");

                if (datos[0].equals("EQUIPO"))
                    equipos.add(new Equipo(datos[2], Integer.parseInt(datos[3])));
                else if (datos[0].equals("FILIAL"))
                    equipos.add(new EquipoFilial(datos[2], Integer.parseInt(datos[3]), datos[4]));
                else
                    System.out.println("Error de lecutra en linea =>      " + linea);
            }
            

            br.close();
            fr.close();

        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<EquipoFilial> listarFiliales() 
    {
        List<EquipoFilial> listaFiliales = equipos.stream()
            .filter(e -> e instanceof EquipoFilial)
            .map(e -> (EquipoFilial) e)
            .collect(Collectors.toList());

        return (listaFiliales);
    }

    @Override
    public void eliminarDuplicados() 
    {
        Set<Equipo> listaSinDup = new HashSet<>(equipos);
        
        equipos = new ArrayList<>(listaSinDup);
    }

    @Override
    public void statsEquipos()
    {
        double media = equipos.stream()
            .mapToInt(Equipo::getPuntuacion)
            .average()
            .orElse(0);

        Equipo maximo = equipos.stream()
            .max(Comparator.comparingInt(Equipo::getPuntuacion))
            .orElse(null);

        Equipo minimo = equipos.stream()
            .min(Comparator.comparingInt(Equipo::getPuntuacion))
            .orElse(null);

        System.out.println("Media: " + media);
        System.out.println("Maximo: " + maximo);
        System.out.println("Minimo: " + minimo);
    }

    

    

    

    
}

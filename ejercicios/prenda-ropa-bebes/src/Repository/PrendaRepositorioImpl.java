package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Model.Prenda;
import Model.PrendaDeportiva;

public class PrendaRepositorioImpl implements PrendaRepositorio
{
    ArrayList<Prenda> prendas;

    public PrendaRepositorioImpl() {
        this.prendas = new ArrayList<>();
    }

    @Override
    public boolean alta(Prenda p) 
    {
        if (prendas.add(p))
            return (true);
        return false;
    }

    @Override
    public void deleteByNombre(String nombre) {

        Iterator<Prenda> it = prendas.iterator();
        
        while (it.hasNext())
        {
            Prenda p = it.next();

            if (nombre.equals(p.getNombre()))
                it.remove();
        }
    }

    @Override
    public List<Prenda> find(String literal) 
    {
        List<Prenda> prendasFiltradas = prendas.stream()
            .filter(e -> e.contieneTexto(literal))
            .collect(Collectors.toList());

        return (prendasFiltradas);
    }

    @Override
    public void findAll() 
    {
        if (prendas.isEmpty() )
            System.out.println("No hay prendas existentes");
        else
        {
            for (Prenda p : prendas)
            {
                System.out.println(p);
            }
        }       
    }

    @Override
    public void escribirDatos(File f) 
    {
        try 
        {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (Prenda p : prendas)
            {
                bw.write(p.toStringPersist());
                bw.newLine();
            }

            System.out.println("Fichero escrito correctamente");
            bw.close();
            fw.close();
        } catch (IOException e) 
        {
            System.out.println("Error al escribir el fichero");
            e.printStackTrace();
        }
    }

    @Override
    public void leerDatos(File f) 
    {
        try 
        {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null)
            {
                String[] datos = linea.split(";");

                if (datos[0].equals("PRENDA"))
                    prendas.add(new Prenda(datos[1], datos[2], datos[3], datos[4], datos[5]));
                else
                    prendas.add(new PrendaDeportiva(datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], datos[7], Boolean.parseBoolean(datos[8])));
            }
            

            br.close();
            fr.close();
        } catch (IOException e) 
        {
            System.out.println("Error de lectura de fichero");
            e.printStackTrace();
        }        
    }

    
    public Map<String, Integer> frecuenciaPrenda()
    {
        Map<String, Integer> map = new HashMap<>();

        List<PrendaDeportiva> prendasDeportivas = prendas.stream()
        .filter(e -> e instanceof PrendaDeportiva)
        .map(e -> (PrendaDeportiva) e)
        .collect(Collectors.toList());

        for (PrendaDeportiva pd : prendasDeportivas)
            map.put(pd.getDeporte(), map.getOrDefault(pd.getDeporte(), 0) + 1);

        return (map);
    }

    

    
    
    
}

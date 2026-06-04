package Repository;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;

import Model.Producto;

public class ProductoRepositoryImpl implements ProductoRepository
{
    private ArrayList<Producto> lista;

    public ProductoRepositoryImpl()
    {
        this.lista = new ArrayList<>();
    }


    @Override
    public void delete(int id)
    {
        for (Producto p : lista)
        {
            if (p.getId() == id)
            {
                lista.remove(p);
                break;
            }
        }
    }


    @Override
    public void findAll()
    {
        System.out.println("===== LISTA DE PRODUCTOS =====");
        for (Producto p : lista)
            System.out.println(p);
    }


    @Override
    public Optional<Producto> findById(int id)
    {
        for (Producto p : lista)
        {
            if (p.getId() == id)
                return Optional.of(p);
        }
        return Optional.empty();
    }


    @Override
    public boolean save(Producto p)
    {
        return lista.add(p);
    }


    @Override
    public boolean update(int id, int cantidad)
    {
        for (Producto p : lista)
        {
            if (p.getId() == id)
            {
                p.setCantidad(cantidad);
                return true;
            }
        }
        return false;
    }


    // ===== OPCIÓN A — serializar la lista entera =====

    @Override
    public void guardarEnFichero(File f)
    {
        try
        {
            FileOutputStream     fos = new FileOutputStream(f);
            ObjectOutputStream   oos = new ObjectOutputStream(fos);

            oos.writeObject(lista);

            oos.close();
            fos.close();
        }
        catch (IOException e)
        {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }


    @Override
    @SuppressWarnings("unchecked")
    public void cargarDesdeFichero(File f)
    {
        try
        {
            FileInputStream     fis = new FileInputStream(f);
            ObjectInputStream   ois = new ObjectInputStream(fis);

            lista = (ArrayList<Producto>) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }


    // ===== OPCIÓN B — serializar objeto a objeto =====

    public void guardarEnFicheroB(File f)
    {
        try
        {
            FileOutputStream     fos = new FileOutputStream(f);
            ObjectOutputStream   oos = new ObjectOutputStream(fos);

            for (Producto p : lista)
                oos.writeObject(p);

            oos.close();
            fos.close();
        }
        catch (IOException e)
        {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }


    public void cargarDesdeFicheroB(File f)
    {

        try
        {
            FileInputStream     fis = new FileInputStream(f);
            ObjectInputStream   ois = new ObjectInputStream(fis);

            while (true)
                lista.add((Producto) ois.readObject());
        }
        catch (EOFException e)
        {
            // fin del fichero, normal
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }
}

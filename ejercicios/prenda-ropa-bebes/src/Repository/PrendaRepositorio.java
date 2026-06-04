package Repository;

import java.io.File;
import java.util.List;

import Model.Prenda;

public interface PrendaRepositorio 
{
    boolean     alta(Prenda p);
    void        deleteByNombre(String nombre);
    void        findAll();
    List<Prenda>  find(String literal);

    void        leerDatos(File f);
    void        escribirDatos(File f);
}

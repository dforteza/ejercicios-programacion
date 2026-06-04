package Repository;

import java.io.File;
import java.util.Optional;

import Model.Producto;

public interface ProductoRepository
{
    boolean             save(Producto p);
    void                findAll();
    Optional<Producto>  findById(int id);
    boolean             update(int id, int cantidad);
    void                delete(int id);

    void cargarDesdeFichero(File f);
    void guardarEnFichero(File f);
}

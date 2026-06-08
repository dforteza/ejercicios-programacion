package Repository;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import Model.Equipo;
import Model.EquipoFilial;

public interface Equipos {

    public boolean  save(Scanner sc);
    public void     delete(int codigo);

    public Optional<Equipo> findById(int id);
    public List<Equipo>     findAllByName(String name);

    public void show();
    public void ordenarAlph();

    public void readBinary(File f);
    public void writeBinary(File f);

    public void leerArchivoTxt(File f);

    public List<EquipoFilial> listarFiliales();
    public void eliminarDuplicados();

    public void statsEquipos();
}

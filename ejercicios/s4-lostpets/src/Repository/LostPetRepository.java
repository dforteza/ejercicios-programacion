package Repository;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import Model.Mascota;

public interface LostPetRepository 
{
    public boolean     alta(Mascota m);
    
    public boolean     baja(Mascota m);
    public boolean  bajaBytTelefono(String tlf);
    
    public List<Mascota>  consultaByNombre(String nombre);
   
    public void     ordenar();
    public void     ordenarByFecha();

    public void     eliminarDuplicados();
    public HashMap<String, Integer> contarPorEspecie();
    public TreeMap<LocalDate, List<Mascota>> agruparPorFecha();

    void    leerDatosTexto(File f);
    void    escribirDatosTexto(File f);

    void    leerDatosBinario(File f);
    void    escribirDatosBinario(File f);
}
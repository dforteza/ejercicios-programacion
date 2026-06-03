package Repository;

import java.util.Scanner;

import Model.Paseo;

public interface RepositorioPaseos 
{
    boolean    altaPaseo(Scanner sc);
    void       altaPaseo(Paseo p);
    boolean    bajaPaseo(int id);
    Paseo   buscarPaseo(String nombrePerro);
    void    mostrarPaseos();

    
}

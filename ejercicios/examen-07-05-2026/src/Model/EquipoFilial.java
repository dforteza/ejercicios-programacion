package Model;

import java.util.Scanner;

public class EquipoFilial extends Equipo 
{
    private String equipoPrincipal;

    public EquipoFilial(Scanner sc) {
        super(sc);

        System.out.print("> Equipo principal:");
        this.equipoPrincipal = sc.nextLine();
    }

    public EquipoFilial(String nombre, int puntuacion, String equipoPrincipal) 
    {
        super(nombre, puntuacion);
        this.equipoPrincipal = equipoPrincipal;
    }

    public String getEquipoPrincipal() {
        return equipoPrincipal;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + equipoPrincipal;
    }
    
    
    
}

package repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Reserva 
{
    private LocalDate   fecha;
    private LocalTime   hora;
    private String      nombre;
    private String      telefono;
    private int         nComensales;
    private Float       coste;

    private final DateTimeFormatter fmt_fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter fmt_hora = DateTimeFormatter.ofPattern("HH:mm");



    public Reserva(LocalDate fecha, LocalTime hora, String nombre, String telefono, int nComensales) {
        this.fecha = fecha;
        this.hora = hora;
        this.nombre = nombre;
        this.telefono = telefono;
        this.nComensales = nComensales;
    }


    public Reserva(Scanner sc) 
    {
        System.out.print("Introduzca fecha:");
        String fecha = sc.nextLine();
        this.fecha = LocalDate.parse(fecha);

        System.out.print("Introduzca hora: ");
        String hora = sc.nextLine();
        this.hora = LocalTime.parse(hora);

        System.out.print("Introduzca nombre: ");
        String nombre = sc.nextLine();
        this.nombre = nombre;

        System.out.print("Introduzca telefono: ");
        String telefono = sc.nextLine();
        this.telefono = telefono;

        System.out.print("Introduzca numero de comensales: ");
        int numero = sc.nextInt();
        sc.nextLine();

        this.nComensales = numero;

    }


    public void setCoste(Float coste) {
        this.coste = coste;
    }


    @Override
    public String toString() 
    {
        String res = "";

        // Fecha: dd/mm/aaaa – Hora: hh:mm – Nombre: nombre (teléfono) – n comensales.
        // Coste: No ha sido calculado aún ó 9999.99 euros.

        res += "Fecha: " + fecha.format(fmt_fecha) + " ";
        res += "Hora: " + hora.format(fmt_hora) +" ";
        res += "Nombre: "+ nombre + " ";
        res += "("+ telefono + ")";
        res += " - "+ nComensales;
        res += "\nCoste: ";
        if (coste == null)
            res += "No ha sido calculado aun";
        else
            res += coste;

        return (res);
    }

    

    

    

    
}

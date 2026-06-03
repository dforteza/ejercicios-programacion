package Model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Paseo 
{
    private int  id = 0;
    private String      nombrePerro;
    private LocalTime   horaInicio;
    private Float       minutosPaseo;
    private boolean     pagado = false;

    private static int secuencia;

    private final DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("HH:mm");

    public Paseo(Scanner sc)
    {
        this.id = ++secuencia;

        System.out.print("Introduzca nombre perro: ");
        this.nombrePerro = sc.nextLine();

        System.out.print("Introduzca hora inicio: ");
        this.horaInicio = LocalTime.parse(sc.nextLine());

        System.out.print("Introduzca minutos paseo: ");
        this.minutosPaseo = sc.nextFloat();
        sc.nextLine();
    }


    public Paseo(String nombrePerro, LocalTime horaInicio, Float minutosPaseo) 
    {
        this.id = ++secuencia;
        this.nombrePerro = nombrePerro;
        this.horaInicio = horaInicio;
        this.minutosPaseo = minutosPaseo;
    }

    
    public void setPagado()
    {
        this.pagado = true;
    }

        public int getId() {
        return id;
    }


    public String getNombrePerro() {
        return nombrePerro;
    }


    public LocalTime getHoraInicio() {
        return horaInicio;
    }


    public Float getMinutosPaseo() {
        return minutosPaseo;
    }

    @Override
    public String toString() 
    {
        String res = "";

        res += "ID: " + id;
        res += " - " + nombrePerro;
        res += " - " + horaInicio.format(fmt1);
        res += " - " + minutosPaseo;
        res += " - " + (pagado ? "Pagado" : "Pendiente de pago");

        return (res);
    }

    public String toString2() 
    {
        String res = "";

        
        for (String s : nombrePerro.split(", "))
        {
            res += "ID: " + id;
            res += " - " + s;
            res += " - " + horaInicio.format(fmt1);
            res += " - " + minutosPaseo;
            res += (pagado == true) ? " Pagado" : " Pendiente de pago"; 

        }

        return (res);
    }
    
    
}

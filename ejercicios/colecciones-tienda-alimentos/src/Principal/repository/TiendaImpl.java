package Principal.repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TiendaImpl implements Tienda
{
    TreeMap<String, Double>     catalogo;
    LinkedHashMap<String, Integer> pedido;


    public TiendaImpl(TreeMap<String, Double> catalogo)
    {
        this.catalogo = catalogo;
        this.pedido   = new LinkedHashMap<>();
    }


    @Override
    public Double calcularTotal()
    {
        Double sum = 0.0;

        for (Map.Entry<String, Integer> entry : pedido.entrySet())
        {
            sum += entry.getValue() * catalogo.get(entry.getKey());
        }

        return (sum);
    }


    @Override
    public void hacerPedido(Scanner sc)
    {
        String  item;
        int     cantidad;

        while (true)
        {
            System.out.print("Producto (o 'fin'): ");
            item = sc.nextLine();

            if (item.equals("fin"))
                break;

            System.out.print("Cantidad: ");
            cantidad = sc.nextInt();
            sc.nextLine();

            if (pedido.containsKey(item))
                pedido.put(item, pedido.get(item) + cantidad);
            else
                pedido.put(item, cantidad);
        }

        System.out.print("Código de descuento (Enter para omitir): ");
        String codigo = sc.nextLine();

        if (codigo.equals("ECODTO"))
            System.out.println("Descuento del 10% aplicado.");
    }


    @Override
    public Double calcularTotalConDescuento(String codigo)
    {
        Double total = calcularTotal();

        if (codigo.equals("ECODTO"))
            total *= 0.90;

        return (total);
    }


    @Override
    public void mostrarCatalogo()
    {
        System.out.println("======= CATALOGO ========");
        for (Map.Entry<String, Double> entry : catalogo.entrySet())
        {
            System.out.println(entry.getKey() + " - " + entry.getValue() + "$");
        }
    }
}

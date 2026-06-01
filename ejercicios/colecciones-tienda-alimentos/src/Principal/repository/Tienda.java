package Principal.repository;

import java.util.Scanner;

public interface Tienda 
{
    void    mostrarCatalogo();
    void    hacerPedido(Scanner sc);
    Double  calcularTotal();
    Double  calcularTotalConDescuento(String codigo);
}

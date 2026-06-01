public class Zona 
{
    private int     entradasPorVender;

    public Zona(int n)
    {
        this.entradasPorVender = n;
    }

    public int getEntradasPorVender() {
        return entradasPorVender;
    }

    public Boolean comprar(int n)
    {
        if (entradasPorVender <= n)
            return (false);
        
        this.entradasPorVender -= n;
        return (true);
    }

}
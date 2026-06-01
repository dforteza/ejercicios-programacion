public class Numeros 
{
    public static void main(String[] args) throws Exception 
    {
        // System.out.println(potencia(2, 3));
        // System.out.println(potencia(2, -1));
        // System.out.println(potencia(2, 0));
        // revertir(12345);
        // rectangulo(3, 4);
        // triangulo(5);
    }

    // === Potencia ===
    public static int potencia(int n, int power)
    {
        if (power < 0)
            return (-1);
        if (power == 0)
            return (1);
        return (n * potencia(n, power - 1));
    }
    
    // === Revertir número ===
    public static void revertir(int n)
    {
        if (n < 0)
            return ;
        if (n > 9)
        {
            System.out.print(n % 10);
            revertir(n / 10);
        }
        else
            System.out.println(n);
    }

    // === Rectángulo ===
	public static void rectangulo (int h,int b)
	{
		if (h == 1)
			linea(b);
		else
		{
			linea(b);
			System.out.println();
			rectangulo (h-1,b);
		}
	}

    // === Triángulo ===
    public static void triangulo(int h)
    {
        if (h == 1)
        {
            linea(h);
            System.out.println();
            return ;
        }
        triangulo(h - 1);
        linea(h);
        System.out.println();

    }

	public static void linea (int n)
	{
		if (n == 1)
			System.out.print("*");
		else
		{	
			System.out.print("*");
			linea(n-1);
		}
	}
}

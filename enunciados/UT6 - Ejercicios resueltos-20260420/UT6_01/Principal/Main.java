package Principal;

public class Main 
{
	public static void main(String[] args) 
	{
		int numeros[]; // Definición del Vector de números 
		numeros = new int[10]; // Reserva de memoria
		
		// Rellenando el vector con números aleatorios
		for (int i=0;i<numeros.length;i++)
			numeros[i] = (int)(Math.random()*100);
		
	    // Calculando e imprimiendo el cuadrado y el cubo de cada uno
		for (int i=0;i<numeros.length;i++)
		{
			System.out.printf("%03d ",numeros[i]);
			System.out.printf("%6.0f ",Math.pow(numeros[i], 2));
			System.out.printf("%6.0f \n",Math.pow(numeros[i], 3));
		}
	}
}

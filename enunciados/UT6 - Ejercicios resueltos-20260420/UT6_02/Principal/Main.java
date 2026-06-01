package Principal;

import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int suma = 0;
		float media;
		int mayor,menor;
		int posMayor,posMenor;
		int numeros[];
		numeros = new int [5];
		
		System.out.println("Introduzca 10 números enteros:");
		for (int i=0; i<numeros.length;i++)
			numeros[i] = sc.nextInt();
		
		// Calcular la suma de los números
		for (int i=0;i<numeros.length;i++)
			suma = suma +numeros[i];
		media = (float) suma / numeros.length;
		
		// Buscar el mayor y el menor
		mayor = menor = numeros[0];
		posMayor = posMenor = 0;
		
		for (int i=1;i<numeros.length;i++)
			if (numeros[i] > mayor)
			{
				mayor = numeros[i];
				posMayor = i;
			}
			else
				if (numeros[i] < menor)
				{
					menor = numeros[i];
					posMenor = i;
				}
		System.out.println("El mayor es "+mayor+" y ocupa la pos "+posMayor);
		System.out.println("El menor es "+menor+" y ocupa la pos "+posMenor);
		
		System.out.printf("Media %.2f \n",media);
		
		sc.close();
	}

}

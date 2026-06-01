import java.util.Scanner;

public class App 
{
    
	public static void main(String[] args) 
	{
		int numeros[] = {1, 5, 9, 3, 45, 23, 23,45, 12, 87, 9, 6, 5};
		Scanner sc = new Scanner(System.in);
		int numero;
		int nElementos;
		
		nElementos = numeros.length;
		
		for (int i=0;i<nElementos;i++)
			System.out.print(numeros[i]+" ");
		
		System.out.println("\nIndique el elemento a eliminar");
		numero = sc.nextInt();
		
		int j = 0;
		while (j < nElementos)
		{
			if (numeros[j] == numero)
			{
				int k = j;
				while (k < nElementos - 1)
				{
					numeros[k] = numeros[k + 1];
					k++;
				}
				nElementos--;
				continue ;
			}
			j++;

		}
		
		for (int i=0;i<nElementos;i++)
			System.out.print(numeros[i]+" ");
		
		sc.close();
	}

}

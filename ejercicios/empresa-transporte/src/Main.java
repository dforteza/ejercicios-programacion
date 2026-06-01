import java.util.Scanner;

public class Main 
{
	// 1- DIMENSIONES
	static final Integer n = 4;
	static final Integer m = 5;
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		Integer[][] M = {{1,2,3,4,5}, {1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5},};
		Integer[]	v = new Integer[n];

		// 2- MATRIZ INICIALIZADA
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				System.out.println("Posicion: "+i +":"+j);

				M[i][j] = sc.nextInt();
				sc.nextLine();
			}
		}

		// 3- RECORRER MATRIZ Y CALCULAR SUMA
		for (int i = 0; i < n; i++)
		{
			int suma = 0;
			v[i] = 0;
			for (int j = 0; j < m; j++)
			{
				suma += M[i][j];
			}
			v[i] = suma;
		}


		// 4 - IMPRESION
		for (int i = 0; i < n; i++)
			System.out.println("Vector: "+i+": "+v[i]);

		sc.close();
	}
}

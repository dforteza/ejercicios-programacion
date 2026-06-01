// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.Scanner;

// public class Main 
// {
// 	public static void main(String[] args) throws IOException 
// 	{
// 		// Ejemplo de escritura de un fichero de texto
// 		/*
// 		FileWriter salida = new FileWriter("fichero.txt");
// 		salida.write("Hola 123 5.6");
// 		salida.close();
// 		*/
		
// 		// =======================================
		
// 		// Ejemplo de lectura de un fichero de texto
		
// 		FileReader entrada = new FileReader("fichero.txt");
// 		int c;
// 		char letra;
// 		while ((c=entrada.read())!=-1)
// 		{
// 			letra = (char) c;
// 			System.out.println("Acabo de leer "+letra+" código:"+c);
// 		}
// 		entrada.close();
		
		
// 		// =======================================
		
// 		// Ejemplo de lectura línea a línea
// 		/*
// 		FileReader fr = new FileReader("fichero.txt");
// 		BufferedReader entrada = new BufferedReader(fr);
// 		String linea;
// 		while ((linea=entrada.readLine())!=null)
// 		{
// 			System.out.println(linea);
// 		}
		
// 		entrada.close();
// 		fr.close();
// 		*/
		
// 		// =======================================
		
// 		// Ejemplo de crear fichero de texto línea por línea
		
// 		FileWriter fw = new FileWriter("fichero2.txt");
// 		BufferedWriter salida = new BufferedWriter(fw);
		
// 		salida.write("Hola\n");
// 		salida.write("esto es otra línea");
		
// 		salida.close();
// 		fw.close();
		
		
// 		// =======================================
		
// 		// Lectura de un fichero de texto singularizando los tipos simples
// 		/*
// 		FileReader fr = new FileReader("fichero.txt");
// 		Scanner entrada = new Scanner(fr);
// 		int entero;
// 		double doble;
// 		String palabra;
// 		while (entrada.hasNextLine())
// 		{
// 			if (entrada.hasNextInt())
// 			{
// 				entero = entrada.nextInt();
// 				System.out.println("Leo un entero: "+entero);
// 			}
// 			else
// 				if (entrada.hasNextDouble())
// 				{
// 					doble = entrada.nextDouble();
// 					System.out.println("Leo un doble: "+doble);
// 				}
// 				else
// 					if (entrada.hasNext())
// 					{
// 						palabra = entrada.next();
// 						System.out.println("Leo una palabra: "+palabra);
// 					}
// 					else
// 						entrada.nextLine();
// 		}
		
// 		entrada.close();
// 		fr.close();
// 		*/
		
// 		// =======================================
		
// 		// Escritura de un fichero de texto singularizando tipos básicos
// 		/*
// 		int edad = 38;
// 		float pi = 3.14159F;
// 		String nombre ="Jaime Pérez";
		
// 		FileWriter fw = new FileWriter("fichero3.txt");
// 		PrintWriter salida = new PrintWriter(fw);
		
// 		salida.println("La edad es "+edad);
// 		salida.printf ("Pi vale: %.2f \n",pi);
// 		salida.println(nombre);
		
// 		salida.close();
// 		fw.close();
// 		*/
		
// 		// =======================================
		
// 		// Ejemplo para añadir información a un fichero pre-existente
		
// 		FileWriter fw = new FileWriter("fichero3.txt",true); // El true es para añadir
// 		PrintWriter salida = new PrintWriter(fw);
		
// 		salida.println("Toda esta información se ha añadido posteriomente");
// 		salida.close();
// 		fw.close();
// 	}
// }

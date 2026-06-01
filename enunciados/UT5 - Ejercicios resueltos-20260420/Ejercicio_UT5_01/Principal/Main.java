package Principal;

public class Main 
{
	public static void main(String[] args) 
	{
		Empleado e1 = new Empleado("Ana",15,1500);
				

		e1.setPermanencia(30);
		System.out.println(e1);
		
		System.out.println(e1.getNombreCompleto());
		
		e1.aumentarSalario(10);
		
		System.out.println(e1);

	}

}

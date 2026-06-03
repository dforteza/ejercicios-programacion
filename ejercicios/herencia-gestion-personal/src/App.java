import Model.Empleado;
import Model.EmpleadoAsalariado;
import Model.EmpleadoComision;
import java.time.LocalDate;

public class App
{
    public static void main(String[] args) throws Exception
    {
        Empleado[] empresa = new Empleado[4];

        // Empleados 1 y 2 — constructor con parámetros
        empresa[0] = new EmpleadoAsalariado("6546546Z", "Antonio", "López", LocalDate.of(2014, 1, 1), 1125.0);
        empresa[1] = new EmpleadoComision("7879879S", "Sandra", "Nieto", LocalDate.of(2011, 1, 1), 169, 7.10);

        // Empleados 3 y 4 — constructor por defecto + setters
        EmpleadoComision emp3 = new EmpleadoComision();
        emp3.setDni("4654654D");
        emp3.setNombre("Manuel");
        emp3.setApellidos("Ruíz");
        emp3.setFechaAlta(LocalDate.of(2010, 1, 1));
        emp3.setN_clientes(35);
        emp3.setComision(6.90);
        empresa[2] = emp3;

        EmpleadoAsalariado emp4 = new EmpleadoAsalariado();
        emp4.setDni("77879878F");
        emp4.setNombre("María");
        emp4.setApellidos("Ramos");
        emp4.setFechaAlta(LocalDate.of(2011, 1, 1));
        emp4.setSueldo_base(1055.0);
        empresa[3] = emp4;

        mostrarTodos(empresa);
        sueldoMayor(empresa);
    }

    static void mostrarTodos(Empleado[] empresa)
    {
        for (Empleado e : empresa)
            System.out.println(e);
    }

    static void sueldoMayor(Empleado[] empresa)
    {
        Empleado mayor = empresa[0];

        for (Empleado e : empresa)
        {
            if (e.calcularSalario() > mayor.calcularSalario())
                mayor = e;
        }

        System.out.println("Mayor salario: " + mayor.getNombre() + " " + mayor.getApellidos() + " — " + mayor.calcularSalario());
    }
}

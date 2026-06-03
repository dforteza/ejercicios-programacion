import java.time.LocalDate;

import Model.Camion;
import Model.Conductor;
import Model.Furgoneta;
import Model.Motocicleta;
import Repository.EmpresaImpl;

public class App
{
    public static void main(String[] args) throws Exception
    {
        EmpresaImpl empresa = new EmpresaImpl("A-123456", "Entregados S.L.", 20);

        Conductor c1 = new Conductor("Juan Pérez");
        Conductor c2 = new Conductor("María López");
        Conductor c3 = new Conductor("Carlos Ruiz");

        empresa.altaVehiculo(new Camion("1234-ABC", "Volvo FH16", LocalDate.of(2020, 5, 10), c1, 12000.0f, 8000.0f));
        empresa.altaVehiculo(new Furgoneta("5678-DEF", "Ford Transit", LocalDate.of(2019, 3, 22), c2, 9, true));
        empresa.altaVehiculo(new Motocicleta("9012-GHI", "Yamaha MT-07", LocalDate.of(2021, 7, 15), c3, 689));

        System.out.println(empresa);

        
    }
}

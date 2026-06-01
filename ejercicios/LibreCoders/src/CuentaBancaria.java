import java.util.Arrays;

public class CuentaBancaria 
{
    private String  iban;
    private String  titular;
    private Double  saldo;
    private String[] movimientos;

    private int c = 0;

    public CuentaBancaria(String iban, String titular) 
    {
        this.iban = iban;
        this.titular = titular;
        this.saldo = 0.0;
        this.movimientos = new String[5];
    }

    public Boolean ingresar(Double n)
    {
        if (n <= 0)
        {
            System.out.println("Error: Cantidad Negativa");
            return (false);
        }
        if (c >= this.movimientos.length)
        {
            System.out.println("Error - No hay capacidad");
            return (false);
        }
        this.saldo += n;
        this.movimientos[c++] = "INGRESO: "+ n;

        if (this.saldo >= 3000)
            System.out.println("AVISO - Notificar hacienda");

        return (true);
    }

    public Boolean retirar(Double n)
    {
        if (n <= 0 || this.saldo - n < -50) // 3001 - 4001 = -1000 < -50 -> TRUE
        {
            System.out.println("Error: Cantidad Negativa o Inferior a -50");
            return (false);
        }
        if (c >= this.movimientos.length)
        {
            System.out.println("Error - No hay capacidad");
            return (false);
        }
        this.saldo -= n;
        this.movimientos[c++] = "RETIRAR: "+ n;

        if (this.saldo < 0)
            System.out.println("AVISO - Saldo negativo");

        return (true);
    }


    public String getIban() {
        return iban;
    }

    public String getTitular() {
        return titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public String[] getMovimientos() {
        return movimientos;
    }

    public int getNumeroMovimientos()
    {
        return (c);
    }
    
    @Override
    public String toString() {
        return "CuentaBancaria [iban=" + iban + ", titular=" + titular + ", saldo=" + saldo + ", movimientos="
                + Arrays.toString(movimientos) + "]";
    }

    
    
}

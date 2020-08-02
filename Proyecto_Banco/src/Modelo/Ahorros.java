package Modelo;

public class Ahorros extends Cuenta {

    private double Interes;

    public Ahorros(double Interes, double cuentabalance) {
        super(cuentabalance);
        this.Interes = Interes;
    }

    public double getInteres() {
        return Interes;
    }

    public void setInteres(int Interes) {
        this.Interes = Interes;
    }

    public void setBalance(double balance) {

        super.setCuentabalance(balance);
    }
    
    public double getBalance() {

        return super.getCuentabalance();
    }
}

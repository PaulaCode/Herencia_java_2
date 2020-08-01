
package Modelo;


public class Ahorros extends Cuenta{
    
    private double Interes;

    public Ahorros(double Interes, int cuentabalance) {
        super(cuentabalance);
        this.Interes = Interes;
    }

    public double getInteres() {
        return Interes;
    }

    public void setInteres(int Interes) {
        this.Interes = Interes;
    }
    
    
    
}

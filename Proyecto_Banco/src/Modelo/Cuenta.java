
package Modelo;


public class Cuenta  {

    private double cuentabalance=0; 
   
    public Cuenta(double cuentabalance) {
        this.cuentabalance = cuentabalance;
    }

    public double getCuentabalance() {
        return cuentabalance;
    }

    public void setCuentabalance(double cuentabalance) {
        this.cuentabalance += cuentabalance;
    }
    
 
}

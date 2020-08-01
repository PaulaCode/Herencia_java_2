
package Modelo;

public class Cuenta extends Cliente {
    private int cuentabalance; 

    public Cuenta(int cuentabalance) {
        this.cuentabalance = cuentabalance;
    }

    public int getCuentabalance() {
        return cuentabalance;
    }

    public void setCuentabalance(int cuentabalance) {
        this.cuentabalance = cuentabalance;
    }
    
    
}

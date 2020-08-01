
package Modelo;


public class TarjetaCredito extends Cuenta{
    private int numero_tarjeta;
    private double monto;

    public TarjetaCredito(int numero_tarjeta, double monto) {
        this.numero_tarjeta = numero_tarjeta;
        this.monto = monto;
    }

    public int getNumero_tarjeta() {
        return numero_tarjeta;
    }

    public void setNumero_tarjeta(int numero_tarjeta) {
        this.numero_tarjeta = numero_tarjeta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Número: " + numero_tarjeta + " Monto: $" + monto+"\n";
    }
    
}

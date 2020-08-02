package Modelo;

import java.util.ArrayList;

public class Credito extends Cuenta{

    private ArrayList<TarjetaCredito> tarjetas = new ArrayList<>();
    static final double MONTO_MAX = 10000000;
    public Credito(double balance)
    {
        super(balance);
    }
    public void setListaTarjetas(ArrayList<TarjetaCredito> tarjetas)
    {
        this.tarjetas=tarjetas;
    }
    public void setTarjeta(TarjetaCredito tarjeta)
    {
        this.tarjetas.add(tarjeta);
    }
    public double getTotal_monto()
    {
        double monto=0;
        if(!this.tarjetas.isEmpty())
        {
            for(int i=0;i<tarjetas.size();i++)
            {
                monto+= tarjetas.get(i).getMonto();
            }
        }
        return monto;
    }
    public double getBalance(){
        return super.getCuentabalance();
    }
    public void setBalance(double balance){
        
        super.setCuentabalance(balance);
        
    }
    @Override
    public String toString() {
        String mensaje="";
        for(int i =0;i<tarjetas.size();i++)
        {
            mensaje+= tarjetas.toString();
        }
        return mensaje;
    }
    
}

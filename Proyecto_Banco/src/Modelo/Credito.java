package Modelo;

import java.util.ArrayList;

public class Credito extends Cuenta{

    private ArrayList<TarjetaCredito> tarjetas = new ArrayList<>();
    static final double MONTO_MAX = 10000000;
   
    public Credito(ArrayList<TarjetaCredito> tarjetas,double balance)
    {
        super(balance);
        this.tarjetas=tarjetas;
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
    @Override
    public String toString() {
        String mensaje="";
        for(int i =0;i<this.tarjetas.size();i++)
        {
            mensaje+="Número: "+this.tarjetas.toString();
        }
        return mensaje;
    }
    
}

package Modelo;

import java.util.ArrayList;

public class Credito {

    private ArrayList<TarjetaCredito> tarjetas = new ArrayList<>();
    static final double MONTO_MAX = 10000000;
   
    public Credito(ArrayList<TarjetaCredito> tarjetas)
    {
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

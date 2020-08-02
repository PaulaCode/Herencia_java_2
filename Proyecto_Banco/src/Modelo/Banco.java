
package Modelo;

import Vista.InOut;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Banco {
    
    ArrayList<Cliente> clientes = new ArrayList();

    public final double interes = 0.5;

    int numero_tarjeta=0;

    InOut inOut = new InOut(); 
    
    public void crearCliente(){
        
        int identificacion=0;
        boolean verificar;
        
        String nombre = inOut.solicitarNombre("Digite su nombre: ");
        do{
         identificacion = inOut.solicitarEntero("Digite su identificación: ");
         verificar =   Verificarcc(identificacion);
        }while(verificar != false);
        
        String direccion = inOut.solicitarNombre("Digite su dirección: ");

        Cliente obj_cliente = new Cliente(nombre,identificacion,direccion);
        clientes.add(obj_cliente);
        
        crearCuentas(obj_cliente);

    }
  
    public void crearCuentas(Cliente obj_cliente){

       int opcion = inOut.solicitarEntero("1. Cuenta de crédito"
                + "\n2. Cuenta de ahorros ");
        
        switch(opcion){
            
            case 1: {
                 double balance = inOut.solicitarDoubles("Digite el balance de su cuenta de crédito");
                 while(balance<=0 || balance>Credito.MONTO_MAX){
                 balance = inOut.solicitarDoubles("El balance no es correcto \nIngrese el balance nuevamente");
                 }
                 Credito creditos = new Credito(balance);
                 obj_cliente.credito = creditos;
                 crearTarjeta(obj_cliente,balance);
                break;
            }
            case 2:{
                Ahorros ahorro = crearAhorros();
                obj_cliente.setAhorros(ahorro);
                break;
            }

        }
    }
    

    public void crearTarjeta(Cliente obj_cliente,double balance)
    {
        numero_tarjeta++;  //aumentamos el número tarjeta 
        TarjetaCredito obj_tarjeta = new TarjetaCredito();
        
        obj_tarjeta.setNumero_tarjeta(numero_tarjeta);          //asignamos número de tarjeta
        double monto = obj_cliente.credito.getTotal_monto();            //traemos el monto(suma de todos los montos de las tarjetas disponibles)
        obj_tarjeta.setMonto(inOut.solicitarDoubles("Digite el monto que tendrá la tarjeta"));  //solicitamos el monto de esa tarjeta
        
        while(obj_tarjeta.getMonto()+monto>obj_cliente.getCredito().getCuentabalance())//validamos que no sea mayor al balance de la cuenta
        {
          obj_tarjeta.setMonto(inOut.solicitarDoubles("ADVERTENCIA: BALANCE EXCEDIDO\n"+"Su balance en crédito: $"+obj_cliente.credito.getCuentabalance()+"Digite el monto que tendrá la tarjeta"));  
        }
        obj_cliente.getCredito().setTarjeta(obj_tarjeta);
        String mensaje = "Señor/a "+obj_cliente.getNombre()+"\n"+"Las tarjetas a su disposición son: \n"+
                obj_cliente.credito.toString(); //muestra las tarjetas
                ;
        inOut.mostrarResultado(mensaje);
    }

    
    public Ahorros crearAhorros(){
        
        double balance = inOut.solicitarDoubles("Digite el balance de su cuenta de crédito");
        double inter = (balance * interes) / 100; 
        
        Ahorros ahorro = new Ahorros(inter,balance); 
        
        return ahorro;
    }
    
    public void buscarCliente(){
        
        int cedula = inOut.solicitarEntero("Digite el número de identificación del cliente que quiere buscar: ");
        boolean flag = false;
        
        for(int i=0; i<clientes.size(); i++){
            
            if(cedula == clientes.get(i).getIdentificacion()){
                flag = true;
                inOut.mostrarResultado("El cliente es "+clientes.get(i).getNombre()+"con"
                        +   "\nDirección: "+clientes.get(i).getDireccion() +  ", su"
                        +   "\nCédula es: "+clientes.get(i).getIdentificacion()
                        +   "\nEl balance de la cuenta de ahorros es "+clientes.get(i).getAhorros().getBalance()
                        +   "\nEl balance de la cuenta de la cuenta de crédito es " +clientes.get(i).getCredito().getTotal_monto()
                        +   "\nY sus tarjetas de cŕedito son: "+ clientes.get(i).getCredito().toString()
                );
               
            } 
                
        }
        if(flag== false)
            inOut.mostrarResultado("No hay un cliente con esa identificación.");
       
    }
    
    public boolean Verificarcc(int c) {
        boolean flag = false;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdentificacion()== c) {
                flag = true;
            }
        }
        return flag;
    }
    
    public void editarInfo(){
        
        int cedula = inOut.solicitarEntero("Digite su cédula");
        boolean verificar;
        
        
    }
    
}

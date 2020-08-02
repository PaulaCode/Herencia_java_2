
package Modelo;

import Vista.InOut;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Banco {
    
    ArrayList<Cliente> clientes = new ArrayList();
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

    }
    
    public void menuPrincipal(){
        
         int opcion = inOut.solicitarEntero("1. Registrarse"
                + "\n2. Banco.  ");
         
         switch(opcion){
             
             case 1:
             {
                 break;  
             }
             case 2:{
                  break;
             }  
               
             
         }
        
    }
    
    public void crearCuentas(Cliente obj_cliente){
        
       int opcion = inOut.solicitarEntero("1. Cuenta de crédito"
                + "\n2. Cuenta de ahorros ");
        
        switch(opcion){
            
            case 1: 
            {
                 double balance = inOut.solicitarDoubles("Digite el balance de su cuenta de crédito");
                 while(balance<=0 || balance>Credito.MONTO_MAX){
                 balance = inOut.solicitarDoubles("El balance no es correcto \nIngrese el balance nuevamente");
                 }
                 crearTarjeta(obj_cliente,balance);
                break;
            }
            case 2:{
                break;
            }

        }
    }
    

    public void crearTarjeta(Cliente obj_cliente,double balance)
    {
        numero_tarjeta++;  //aumentamos el número tarjeta
        Credito obj_credito = new Credito(balance);
        TarjetaCredito obj_tarjeta = new TarjetaCredito();
        
        obj_tarjeta.setNumero_tarjeta(numero_tarjeta);          //asignamos número de tarjeta
        double monto = obj_credito.getTotal_monto();            //traemos el monto(suma de todos los montos de las tarjetas disponibles)
        obj_tarjeta.setMonto(inOut.solicitarDoubles("Digite el monto que tendrá la tarjeta"));  //solicitamos el monto de esa tarjeta
        
        while(obj_tarjeta.getMonto()+monto>obj_credito.getCuentabalance())//validamos que no sea mayor al balance de la cuenta
        {
          obj_tarjeta.setMonto(inOut.solicitarDoubles("ADVERTENCIA: BALANCE EXCEDIDO\n"+"Su balance en crédito: $"+obj_cliente.credito.getCuentabalance()+"Digite el monto que tendrá la tarjeta"));  
        }
        obj_credito.setTarjeta(obj_tarjeta);
        obj_cliente.setCredito(obj_credito);
        String mensaje = "Señor/a "+obj_cliente.getNombre()+"\n"+"Las tarjetas a su disposición son: \n"+
                obj_cliente.credito.toString(); //muestra las tarjetas
                ;
        inOut.mostrarResultado(mensaje);
    }

    
    public void buscarCliente(){
        
        int cedula = inOut.solicitarEntero("Digite el número de identificación del cliente que quiere buscar: ");
        
        for(int i=0; i<clientes.size(); i++){
            
            if(cedula == clientes.get(i).getIdentificacion()){
                
                inOut.mostrarResultado("El cliente es "+clientes.get(i).getNombre()+"con"
                        +   "\nDirección: "+clientes.get(i).getDireccion() +  ", su"
                        +   "cédula es: "+clientes.get(i).getIdentificacion()
                );
               
            }
                
        }
       
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
    
}

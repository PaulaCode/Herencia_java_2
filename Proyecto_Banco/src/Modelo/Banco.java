
package Modelo;

import Vista.InOut;
import java.util.ArrayList;


public class Banco {
    
    ArrayList<Cliente> clientes = new ArrayList();
    
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
        clientes.add(new Cliente(nombre,identificacion,direccion));
        
        crearCuentas();
        
    }
   
    public void menuCliente(){
        
         int opcion = inOut.solicitarEntero("1. Editar información"
                + "\n2. Ver mis cuentas  ");
         
         switch(opcion){
             
             case 1: break;
             
             case 2: 
                 
                 
                 break;
                 
             
         }
        
    }
    
    public void mostrarCuentas(){
        
        
        
        
    }
    
    public void crearCuentas(){
        
       int opcion = inOut.solicitarEntero("1. Cuenta de crédito"
                + "\n2. Cuenta de ahorros ");
        
        switch(opcion){
            
            case 1: 
            
            
            
        }
    }
    
    public void crearCredito(){
        
        double balance = inOut.solicitarDoubles("Digite el balance de su cuenta de crédito");
        while(balance<=0 || balance>Credito.MONTO_MAX){
            balance = inOut.solicitarDoubles("El balance no es correcto \nIngrese el balance nuevamente");
        }
        
        
        
    }
    
    public void crearTarjeta(double monto){
        
        
        
        
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

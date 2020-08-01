/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.InOut;
import java.util.ArrayList;


public class Banco {
    
    ArrayList<Cliente> clientes = new ArrayList();
    
    InOut inOut = new InOut(); 
    
    public void crearCliente(){
        
        String nombre = inOut.solicitarNombre("Digite su nombre: ");
        int identificacion = inOut.solicitarEntero("Digite su identificación: ");
        String direccion = inOut.solicitarNombre("Digite su dirección: ");
        
        clientes.add(new Cliente(nombre,identificacion,direccion));
        
    }
    
    public void buscarCliente(){
        
        int cedula = inOut.solicitarEntero("Digite el número de identificación del cliente que quiere buscar: ");
        
        for(int i=0; i<clientes.size(); i++){
            
            if(cedula == clientes.get(i).getIdentificacion()){
                
                inOut.mostrarResultado("El cliente es "+clientes.get(i).getNombre()+"con"
                        +   "\nDirección: "+clientes.get(i).getDireccion()
                        
                );
               
            }
                
        }
        
        
    }
    
}


package Control;

import Modelo.Banco;
import Vista.InOut;

public class Proyecto_Banco {

   private static final String clave ="hola123";
    
    public static void main(String[] args) {
        
        
        InOut inOut = new InOut();
        int opc=0;

       Banco banco = new Banco();
  do {
      String mensaje=" ";
      mensaje+="1. Registrarse"
                + "\n 2. Modo banco "
                + "\n 3. Editar información "
                + "\n 4. Salir";
     opc= inOut.solicitarEntero(mensaje);
      
    switch (opc){
        case 1:{
                   banco.crearCliente();
               
            break;
                }
        case 2:{
        String clavead= inOut.solicitarNombre("Ingrese la clave de banco: ");
            if(clavead.equals(clave)){
               banco.buscarCliente();
                 } else
                inOut.mostrarResultado("Clave incorrecta.");
            break;
        }
        case 3:{
            
            banco.editarInfo();
        }
        break;
        case 4:{
            System.exit(0);
            break;
        }
         default: {
                    inOut.mostrarResultado("Ninguna opción válida fue elegida");
                }
    } 
      
      
  }while(opc!=3);
      
    }

    }
    


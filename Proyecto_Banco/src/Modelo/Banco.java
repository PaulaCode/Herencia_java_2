package Modelo;

import Vista.InOut;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Banco {

    ArrayList<Cliente> clientes = new ArrayList();

    public final double interes = 0.5;

    int numero_tarjeta = 0;

    InOut inOut = new InOut();

    public void crearCliente() {

        int identificacion = 0;
        boolean verificar;

        String nombre = inOut.solicitarNombre("Digite su nombre: ");
        do {
            identificacion = inOut.solicitarEntero("Digite su identificación: ");
            verificar = Verificarcc(identificacion);
        } while (verificar != false);

        String contrasena = inOut.solicitarNombre("Digite la contraseña que desee");
        String direccion = inOut.solicitarNombre("Digite su dirección: ");

        Cliente obj_cliente = new Cliente(nombre, identificacion, direccion, contrasena);
        clientes.add(obj_cliente);

        crearCuentas(obj_cliente);

    }

    public void crearCuentas(Cliente obj_cliente) {
        int opcion;
        do {
            opcion = inOut.solicitarEntero("1. Cuenta de crédito"
                    + "\n2. Cuenta de ahorros "
                    + "\n3. Salir "
            );

            switch (opcion) {

                case 1: {
                    if (verificarCuentas(obj_cliente, opcion) == true) {

                        double balance = inOut.solicitarDoubles("Digite el balance de su cuenta de crédito");
                        while (balance <= 0 || balance > Credito.MONTO_MAX) {
                            balance = inOut.solicitarDoubles("El balance no es correcto \nIngrese el balance nuevamente");
                        }
                        Credito creditos = new Credito(balance);
                        obj_cliente.credito = creditos;
                        crearTarjeta(obj_cliente, balance);

                    } else {
                        inOut.mostrarResultado("Ya tiene registrada una cuenta de crédito.");
                    }
                    break;
                }
                case 2: {
                    if (verificarCuentas(obj_cliente, opcion) == true) {
                        Ahorros ahorro = crearAhorros();
                        obj_cliente.setAhorros(ahorro);
                    } else {
                        inOut.mostrarResultado("Ya tiene registrada una cuenta de ahorros.");
                    }
                    break;
                }
                case 3:
                    break;
                default: {
                    inOut.mostrarResultado("Ninguna opción válida fue elegida");
                }
            }
        } while (opcion != 3);
    }

    public void crearTarjeta(Cliente obj_cliente, double balance) {
        numero_tarjeta++;  //aumentamos el número tarjeta 
        TarjetaCredito obj_tarjeta = new TarjetaCredito();

        obj_tarjeta.setNumero_tarjeta(numero_tarjeta);          //asignamos número de tarjeta
        double monto = obj_cliente.credito.getTotal_monto();            //traemos el monto(suma de todos los montos de las tarjetas disponibles)       
        obj_tarjeta.setMonto(inOut.solicitarDoubles("Digite el monto que tendrá la tarjeta"));  //solicitamos el monto de esa tarjeta

        while (obj_tarjeta.getMonto() + monto > obj_cliente.getCredito().getCuentabalance() || obj_tarjeta.getMonto() <= 0)//validamos que no sea mayor al balance de la cuenta
        {
            if (obj_tarjeta.getMonto() + monto > obj_cliente.getCredito().getCuentabalance()) {
                obj_tarjeta.setMonto(inOut.solicitarDoubles("ADVERTENCIA: BALANCE EXCEDIDO\n" + "Su balance en crédito: $" + obj_cliente.credito.getCuentabalance() + " \nDigite el monto que tendrá la tarjeta"));
            } else {
                obj_tarjeta.setMonto(inOut.solicitarDoubles("No pueden ser números negativos. \nDigite nuevamente el monto: "));
            }
        }
        obj_cliente.getCredito().setTarjeta(obj_tarjeta);
        String mensaje = "Señor/a " + obj_cliente.getNombre() + "\n" + "Las tarjetas a su disposición son: \n"
                + obj_cliente.credito.toString(); //muestra las tarjetas
        ;
        inOut.mostrarResultado(mensaje);
    }

    public Ahorros crearAhorros() {

        double balance = inOut.solicitarDoubles("Digite el balance de su cuenta de ahorros");
        while (balance < 0) {
            balance = inOut.solicitarDoubles("Balance erróneo. \nDigite nuevamente el balance.");
        }
        double inter = (balance * interes) / 100;

        Ahorros ahorro = new Ahorros(inter, balance);

        return ahorro;
    }

    public void buscarCliente() {

        String mensaje = " ";

        int cedula = inOut.solicitarEntero("Digite el número de identificación del cliente que quiere buscar: ");
        boolean flag = false;

        for (int i = 0; i < clientes.size(); i++) {

            if (cedula == clientes.get(i).getIdentificacion()) {
                flag = true;
                mensaje += ("El cliente es " + clientes.get(i).getNombre()
                        + "\nDirección: " + clientes.get(i).getDireccion()
                        + "\nCédula es: " + clientes.get(i).getIdentificacion());
                if (clientes.get(i).ahorros != null) {
                    mensaje += ("\nEl balance de la cuenta de ahorros es " + clientes.get(i).getAhorros().getBalance());
                }
                if (clientes.get(i).credito != null) {
                    mensaje += ("\nEl balance de la cuenta de la cuenta de crédito es " + clientes.get(i).credito.getBalance()
                            + "\nY sus tarjetas de cŕedito son: " + clientes.get(i).getCredito().toString());

                }
            }

        }
        if (flag == true) {
            inOut.mostrarResultado(mensaje);
        } else {
            inOut.mostrarResultado("No hay un cliente con esa identificación.");
        }

    }

    public boolean Verificarcc(int c) {
        boolean flag = false;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdentificacion() == c) {
                flag = true;
            }
        }
        return flag;
    }

    public void editarInfo() {
        int opcion = 0;
        int cedula = inOut.solicitarEntero("Digite su cédula");
        if (cedula > 0 && Verificarcc(cedula)) {
            int posicion_cliente = returnCliente(cedula);
            String contrasena = inOut.solicitarNombre("Digite su contraseña");
            if (contrasena.equals(clientes.get(posicion_cliente).getContrasena())) {
                do {
                    String mensaje = "1.Crear Cuenta\n2.Crear una nueva tarjeta\n3.Cambiar contraseña \n4. Cambiar dirección \n5. Salir\n\nDigite una opción:";
                    opcion = inOut.solicitarEntero(mensaje);

                    switch (opcion) {
                        case 1: {
                            crearCuentas(clientes.get(posicion_cliente));
                            break;
                        }
                        case 2: {
                            if (clientes.get(posicion_cliente).credito != null) {
                                crearTarjeta(clientes.get(posicion_cliente), clientes.get(posicion_cliente).getCredito().getCuentabalance());
                            } else {
                                inOut.mostrarResultado("Usted no cuenta con una cuenta de crédito");
                            }

                            break;
                        }
                        case 3: {

                            String contrasena1 = inOut.solicitarNombre("Digite su contraseña actual: ");
                            if (contrasena1.equals(clientes.get(posicion_cliente).getContrasena())) {

                                String nuevacontrasena = inOut.solicitarNombre("Digite su nueva contraseña");
                                clientes.get(posicion_cliente).setContrasena(nuevacontrasena);

                                inOut.mostrarResultado("Contraseña cambiada con éxito");

                            } else {
                                inOut.mostrarResultado("Contraseña incorrecta");
                            }
                            break;
                        }

                        case 4: {

                            String direccion = inOut.solicitarNombre("Digite su nueva direccion");
                            clientes.get(posicion_cliente).setDireccion(direccion);
                            inOut.mostrarResultado("Dirección cambiada con éxito");
                        }

                        case 5:

                            break;

                        default:
                            inOut.mostrarResultado("Ninguna opción fue elegida con éxito.");

                    }
                } while (opcion != 5);
            } else {
                inOut.mostrarResultado("Contraseña incorrecta.");
            }
        } else {
            inOut.mostrarResultado("Datos erroneos");
        }
       
    }

    public int returnCliente(int identificacion) {

        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdentificacion() == identificacion) {
                return i;
            }
        }
        return -1;
    }

    public boolean verificarCuentas(Cliente obj_cliente, int opcion) {

        if (opcion == 1) {
            if (obj_cliente.getCredito() == null) {
                return true;
            }
        } else {
            if (obj_cliente.getAhorros() == null) {
                return true;
            }
        }
        return false;
    }

    public void transacciones() {

        int opcion = 0;
        int cedula = inOut.solicitarEntero("Digite su cédula");
        if (cedula > 0 && Verificarcc(cedula)) {
            int posicion_cliente = returnCliente(cedula);
            if (clientes.get(posicion_cliente).ahorros != null) {
                String contrasena = inOut.solicitarNombre("Digite su contraseña");
                if (contrasena.equals(clientes.get(posicion_cliente).getContrasena())) {
                    do {
                        String mensaje = "1.Consignar\n2.Retirar\n3.Saldo\n4.Salir\n\nDigite una opción:";
                        opcion = inOut.solicitarEntero(mensaje);

                        switch (opcion) {
                            case 1: {
                                double consignar = inOut.solicitarDoubles("Digite la cantidad que quiere consignar");
                                clientes.get(posicion_cliente).ahorros.setBalance(consignar);
                                break;
                            }
                            case 2: {
                                int opcion1;
                                do {
                                    String mensaje1 = "1. 50.000        2. 100.000 \n3. 150.000       4. 200.000 \n        5. Otro valor. \n6. Salir \n\nDigite una opción:";
                                    opcion1 = inOut.solicitarEntero(mensaje1);
                                    switch (opcion1) {

                                        case 1: {
                                            while (50000 > clientes.get(posicion_cliente).ahorros.getBalance()) {
                                                opcion1 = inOut.solicitarEntero("No tiene saldo suficiente para retirar \nDigite otra opción");
                                            }

                                            clientes.get(posicion_cliente).ahorros.setBalanceQuitar(50000);
                                            inOut.mostrarResultado("Ha retirado 50.000 con éxito");

                                            break;
                                        }
                                        case 2: {

                                            while (100000 > clientes.get(posicion_cliente).ahorros.getBalance()) {
                                                opcion1 = inOut.solicitarEntero("No tiene saldo suficiente para retirar \nDigite otra opción");
                                            }

                                            clientes.get(posicion_cliente).ahorros.setBalanceQuitar(100000);

                                            inOut.mostrarResultado("Ha retirado 100.000 con éxito");
                                            break;
                                        }
                                        case 3: {

                                            while (150000 > clientes.get(posicion_cliente).ahorros.getBalance()) {
                                                opcion1 = inOut.solicitarEntero("No tiene saldo suficiente para retirar \nDigite otra opción");
                                            }

                                            clientes.get(posicion_cliente).ahorros.setBalanceQuitar(150000);

                                            inOut.mostrarResultado("Ha retirado 150.000 con éxito");
                                            break;
                                        }

                                        case 4: {

                                            while (200000 > clientes.get(posicion_cliente).ahorros.getBalance()) {
                                                opcion1 = inOut.solicitarEntero("No tiene saldo suficiente para retirar \nDigite otra opción");
                                            }

                                            clientes.get(posicion_cliente).ahorros.setBalanceQuitar(200000);

                                            inOut.mostrarResultado("Ha retirado 200.000 con éxito");
                                            break;
                                        }
                                        case 5: {

                                            double retirar = inOut.solicitarDoubles("Digite la cantidad de dinero que desea retirar");

                                            while (retirar > clientes.get(posicion_cliente).ahorros.getBalance()) {
                                                opcion1 = inOut.solicitarEntero("No tiene saldo suficiente para retirar \nDigite otra opción");
                                            }

                                            clientes.get(posicion_cliente).ahorros.setBalanceQuitar(retirar);

                                            inOut.mostrarResultado("Ha retirado " + retirar + " con éxito");
                                            break;
                                        }
                                        case 6:
                                            break;

                                        default:
                                            inOut.mostrarResultado("Ninguna opción válida fue elegida.");
                                    }

                                } while (opcion1 != 6);
                            }
                            case 3: {

                                inOut.mostrarResultado("Su saldo es de: " + clientes.get(posicion_cliente).ahorros.getBalance());
                                break;
                            }

                            case 4:
                                break;

                            default:
                                inOut.mostrarResultado("Ninguna opción válida fue elegida.");

                        }
                    } while (opcion != 4);

                } else {
                    inOut.mostrarResultado("Contraseña incorrecta");
                }
            } else {
                inOut.mostrarResultado("Usted no posee una cuenta de ahorros.");
            }

        } else {
            inOut.mostrarResultado("Datos erróneos");
        }
    }

}

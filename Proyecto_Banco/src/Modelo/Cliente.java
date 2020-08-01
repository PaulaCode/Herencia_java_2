/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


public class Cliente {
    
    final String nombre;
    final int identificacion;
    String direccion;
    Credito credito;
    Ahorros ahorros;

    public Cliente(String nombre, int identificacion, String direcccion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public Credito getCredito() {
        return credito;
    }

    public Ahorros getAhorros() {
        return ahorros;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public void setAhorros(Ahorros ahorros) {
        this.ahorros = ahorros;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
   
}

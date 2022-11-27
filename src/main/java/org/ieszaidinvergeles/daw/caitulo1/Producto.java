/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ieszaidinvergeles.daw.caitulo1;

/**
 *
 * @author alejandro
 */
public class Producto {
    String SKU;
    String nombre;
    String proveedor;
    Double precio;
    int stock;
    boolean suspendido;

    public Producto(String SKU, String nombre, String proveedor, Double precio, int stock, boolean suspendido) {
        this.SKU = SKU;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.precio = precio;
        this.stock = stock;
        this.suspendido = suspendido;
    }
}

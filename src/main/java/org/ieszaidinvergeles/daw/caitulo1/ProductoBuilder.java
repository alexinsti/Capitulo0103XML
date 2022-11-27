/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ieszaidinvergeles.daw.caitulo1;


public class ProductoBuilder {

    private String SKU;
    private String nombre;
    private String proveedor;
    private Double precio;
    private int stock;
    private boolean suspendido;

    public ProductoBuilder() {
    }

    public ProductoBuilder setSKU(String SKU) {
        this.SKU = SKU;
        return this;
    }

    public ProductoBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ProductoBuilder setProveedor(String proveedor) {
        this.proveedor = proveedor;
        return this;
    }

    public ProductoBuilder setPrecio(Double precio) {
        this.precio = precio;
        return this;
    }

    public ProductoBuilder setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public ProductoBuilder setSuspendido(boolean suspendido) {
        this.suspendido = suspendido;
        return this;
    }

    public Producto createProducto() {
        return new Producto(SKU, nombre, proveedor, precio, stock, suspendido);
    }
    
}

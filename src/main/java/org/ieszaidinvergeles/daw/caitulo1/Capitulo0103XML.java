/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package org.ieszaidinvergeles.daw.caitulo1;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.xml.sax.SAXException;

/**
 *
 * @author alejandro
 */
public class Capitulo0103XML {

    static  Path fichero=Paths.get("datos/productos.xml");
    
    public static void main(String[] args) throws SAXException {
        System.out.println("Hello World!");
        System.out.println("hola");
        ProductosXMLDOM p = new ProductosXMLDOM();
        p.abrirXMLDOM(fichero);
        System.out.println(p.recorrerDOMyAlmacenar(p.doc));
        p.mostrarArray();
        System.out.println(p.catalogo.size());
        
        p.addDOM(p.doc, "SKU", "estuche", "LaCasaDelLibro", "15.65", "5", "true");
        p.mostrarArray();
        System.out.println(p.catalogo.size());
        
        p.guardarDOMcomoFile(Paths.get("datos/productos2.xml"));
        
        
    }
}

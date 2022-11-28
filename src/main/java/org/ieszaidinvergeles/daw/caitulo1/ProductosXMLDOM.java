/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ieszaidinvergeles.daw.caitulo1;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author alejandro
 */
public class ProductosXMLDOM {
    List<Producto> catalogo= new ArrayList();
    
    public Document doc;
    
    public int abrirXMLDOM(Path fichero) throws SAXException {
        doc = null; //doc es de tipo Document y representa al árbol DOM
        try {
            //Se crea un objeto DocumentBuiderFactory. Patrón Singleton
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //Indica que el modelo DOM no debe contemplar los comentarios que tenga el XML
            factory.setIgnoringComments(true);
            //Ignora los espacios en blanco que tenga el documento
            factory.setIgnoringElementContentWhitespace(true);
            //Se crea un objeto DocumentBuilder para cargar en él la estructura de
            //árbol DOM a partir del XML seleccionado
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Interpreta (parsea) el documento XML (file) y genera el DOM equivalente
            doc = builder.parse(fichero.toFile());
            //Ahora doc apunta al árbol DOM listo para ser recorrido
            return 0;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            return -1;
        }
    }
    
    public String recorrerDOMyAlmacenar(Document doc) {
        String[] datosNodo;
        String salida = "";
        Node node;
        //Obtiene el primero nodo del DOM (primer hijo)
        Node raiz = doc.getFirstChild();
        //Obtiene una lista de nodos con todos los nodos hijo del raíz
        NodeList nodelist = raiz.getChildNodes();
        //Procesa los nodos hijo
        for (int i = 0; i < nodelist.getLength(); i++) {
            node = nodelist.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                //Es un nodo libro
                datosNodo = cargarProducto(node);
                catalogo.add(new ProductoBuilder().setSKU(datosNodo[0])
                                .setNombre(datosNodo[1])
                                .setProveedor(datosNodo[2])
                                .setPrecio(Double.valueOf(datosNodo[3]))
                                .setStock(Integer.valueOf(datosNodo[4]))
                                .setSuspendido(Boolean.valueOf(datosNodo[5]))
                                .createProducto());
                salida = salida + " \n " + "Publicado en: " + datosNodo[0];
                salida = salida + " \n " + "El autor es: " + datosNodo[2];
                salida = salida + " \n " + "El título es: " + datosNodo[1];
                salida = salida + " \n -------------------";
            }
        }
        return salida;
    }
    
    

    protected String[] cargarProducto(Node n) {
        String datos[] = new String[6];

        Node ntemp;
        int contador = 1;
        //Obtiene el valor del primer atributo del nodo (solo uno en este ejemplo)
        datos[0] = n.getAttributes().item(0).getNodeValue();

        //Obtiene los hijos del Libros (titulo y autor)
        NodeList nodos = n.getChildNodes();
        for (int i = 0; i < nodos.getLength(); i++) {
            ntemp = nodos.item(i);
            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                //Importante: para obtener el texto con el título y autro se accede
                //al nodo TEXT hijo de ntemp y se obtiene su valor
                datos[contador] = ntemp.getChildNodes().item(0).getNodeValue();
                contador++;
            }
        }
        return datos;
    }

    
    public void mostrarArray(){
        System.out.println(catalogo.toString());
    }
    
    //Serializar
    public int addDOM(Document doc,String sku, String nombre, String proveedor, String precio, String stock, String suspendido) {
        try {
            //se crea el objeto en el catalogo
            catalogo.add(new ProductoBuilder().setSKU(sku)
                                .setNombre(nombre)
                                .setProveedor(proveedor)
                                .setPrecio(Double.valueOf(precio))
                                .setStock(Integer.parseInt("5"))
                                .setSuspendido(Boolean.parseBoolean("True"))
                                .createProducto());
            
            //Se crea un nodo tipo Element con nombre ‘nombre’
            Node nodoNombre = doc.createElement("nombre");
            //Se crea un nodo tipo texto con el título del libro
            Node nodoNombreText = doc.createTextNode(nombre);
            //Se añade el nodo de texto con el título como hijo del elemento Titulo
            nodoNombre.appendChild(nodoNombreText);
            //Se hace lo mismo que con nombre a autor 
            Node nodoProveedor = doc.createElement("proveedor");
            Node nodoProveedorText = doc.createTextNode(proveedor);
            nodoProveedor.appendChild(nodoProveedorText);
            //Se hace repite con cada uno 
            Node nodoPrecio = doc.createElement("precio");
            Node nodoPrecioText = doc.createTextNode(precio);
            nodoPrecio.appendChild(nodoPrecioText);
            
            Node nodoStock = doc.createElement("stock");
            Node nodoStockText = doc.createTextNode(stock);
            nodoStock.appendChild(nodoStockText);
            
            Node nodoSuspendido = doc.createElement("suspendido");
            Node nodoSuspendidoText = doc.createTextNode(suspendido);
            nodoSuspendido.appendChild(nodoSuspendidoText);
            //Se crea un nodo de tipo elemento (<producto>)
            Node nodoProducto = doc.createElement("producto");
            //Al nuevo nodo producto se le añade un atributo sku
            ((Element) nodoProducto).setAttribute("SKU", sku);
            //Se añade a libro el nodos creados antes
            nodoProducto.appendChild(nodoNombre);
            nodoProducto.appendChild(nodoProveedor);
            nodoProducto.appendChild(nodoPrecio);
            nodoProducto.appendChild(nodoStock);
            nodoProducto.appendChild(nodoSuspendido);
            //Finalmente, se obtiene el primer nodo del documento y a él se le
            //añade como hijo el nodo libro que ya tiene colgando todos sus
            //hijos y atributos creados antes.
            Node raiz = doc.getChildNodes().item(0);
            raiz.appendChild(nodoProducto);
            return 0;
        } catch (DOMException e) {
            System.err.println("Error " + e.getMessage());
            return -1;
        }
    }
    
    
    public void guardarDOMcomoFile(Path fichero){
        try {
            // clases necesarias finalizar la creación del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fichero.toFile());
            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            System.err.println("Error " + ex.getMessage());
        } catch (TransformerException ex) {
            System.err.println("Error " + ex.getMessage());
        }
    }
}

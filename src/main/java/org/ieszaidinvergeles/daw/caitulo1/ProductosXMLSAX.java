/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ieszaidinvergeles.daw.caitulo1;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Document;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author alejandro
 */
public class ProductosXMLSAX extends DefaultHandler{
    List<Producto> catalogo= new ArrayList();
    private static String sku;
    private static String nombre;
    private static String proveedor;
    private static String precio;
    private static String stock;
    private static String suspendido;
    
    private static ProductoBuilder pBuilder;
    
    private static String qualifiedName;
    
    
    public Document doc;

    public static void leerYCrear(Path file){
        try {
            var readerSAX = SAXParserFactory.newInstance()
                    .newSAXParser()
                    .getXMLReader();
            
            var handler=new SAXHandler();
            readerSAX.getContentHandler(handler);
            readerSAX.parse(file);
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ProductosXMLSAX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ProductosXMLSAX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //super.characters(ch, start, length); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        
        
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
}

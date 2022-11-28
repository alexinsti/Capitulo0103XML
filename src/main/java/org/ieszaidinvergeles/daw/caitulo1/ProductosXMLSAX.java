/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ieszaidinvergeles.daw.caitulo1;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
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

        int ultimoelement;
    String cadena_resultado = "";
    SAXParser parser;

    public ProductosXMLSAX() {
        ultimoelement = 0;

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //Se crea un objeto SAXParser para interpretar el documento XML.
            parser = factory.newSAXParser();
            //Se crea una instancia del manejador que será el que recorra el documento 
            //XML secuencialmente 

        } catch (ParserConfigurationException | SAXException e) {
            System.err.println("Error " + e.getMessage());
        }
    }

//    public int abrirXMLSAX(ManejadorSAX sh, SAXParser parser) {
//        try {
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            //Se crea un objeto SAXParser para interpretar el documento XML.
//            parser = factory.newSAXParser();
//            //Se crea una instancia del manejador que será el que recorra el documento 
//            //XML secuencialmente 
//            sh = new ManejadorSAX();
//            return 0;
//        } catch (ParserConfigurationException | SAXException e) {
//            System.err.println("Error " + e.getMessage());
//            return -1;
//        }
//    }

    public String recorrerSAX(Path fXML) {
        try {
//            SAXParserFactory factory = SAXParserFactory.newInstance();
            //Se crea un objeto SAXParser para interpretar el documento XML.
//            SAXParser parser = factory.newSAXParser();

            //Se crea una instancia del manejador que será el que recorra el documento 
            //XML secuencialmente 
            parser.parse(fXML.toFile(), this);

            return this.cadena_resultado;
        } catch (SAXException e) {
            return "Error al parsear con SAX " + e.getMessage();
        } catch (IOException ex) {
            return "Error " + ex.getMessage();
        }

    }

    //Se sobrecarga (redefine) el método startElement
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts)
            throws SAXException {

        switch (qName) {
            case "libro":
                cadena_resultado += "Publicado en: "
                        + atts.getValue(atts.getQName(0)) + "\n ";
                ultimoelement = 1;
                break;
            case "titulo":
                ultimoelement = 2;
                cadena_resultado += "\n " + "El título es: ";
                break;
            case "autor":
                ultimoelement = 3;
                cadena_resultado += "\n " + "El autor es: ";
                break;
            default:
                break;
        }

    }

    //Cuando en este ejemplo se detecta el final de un elemento <libro>, se pone una línea
    //discontinua en la salida.
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("libro")) {
            cadena_resultado = cadena_resultado + "\n -------------------";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (ultimoelement == 2) {
            for (int i = start; i < length + start; i++) {
                cadena_resultado = cadena_resultado + ch[i];
            }
        } else if (ultimoelement == 3) {
            for (int i = start; i < length + start; i++) {
                cadena_resultado = cadena_resultado + ch[i];
            }
        }
    }    
    
    
}

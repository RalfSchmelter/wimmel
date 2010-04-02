/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.design.lib.impl;

import java.io.IOException;
import java.util.Locale;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;

/**
 * Holds some utility methods for XML.
 * 
 * @author Ralf Schmelter
 */
public class XMLUtils {

    /**
     * The locale tag.
     */
    private static final String TAG_LOCALE = "locale";

    /**
     * The language attribute.
     */
    private static final QName ATTR_LANG = new QName("lang");

    /**
     * The country attribute.
     */
    private static final QName ATTR_COUNTRY = new QName("country");

    /**
     * The variant attribute.
     */
    private static final QName ATTR_VARIANT = new QName("variant");
    
    /**
     * Reads a locale tag. Returns <code>null</code> it is not the next element.
     * 
     * @param reader The reader.
     * @throws IOException if reading failed.
     * @throws XMLStreamException If the XML is invalid.
     */
    public static void readLocale(XMLEventReader reader) throws IOException, XMLStreamException {
        
    }
    
    /**
     * Writes the locale in an XML element.
     * 
     * @param writer The writer to use.
     * @param tag The tag.
     * @param value The value to write.
     * @throws IOException If writing failed.
     * @throws XMLStreamException If the XML is invalid.
     */
    public static void writeLocale(XMLStreamWriter writer, Locale locale) 
            throws IOException, XMLStreamException {
        writer.writeStartElement(TAG_LOCALE);
        writer.writeAttribute(ATTR_LANG.getLocalPart(), locale.getLanguage());
        
        if (locale.getCountry() != null) {
            writer.writeAttribute(ATTR_COUNTRY.getLocalPart(), locale.getCountry());
            
            if (locale.getVariant() != null) {
                writer.writeAttribute(ATTR_VARIANT.getLocalPart(), locale.getVariant());
            }
        }
        
        writer.writeEndElement();
    }
    
    /**
     * Reads the string content of the element with the given tag. Returns <code>null</code> if the tga
     * is not the next tag.
     * 
     * @param reader The reader.
     * @param tag The tag.
     * @return The string.
     * @throws IOException if reading failed.
     * @throws XMLStreamException If the XML is invalid.
     */
    public static String readString(XMLEventReader reader, String tag) 
            throws IOException, XMLStreamException {
        XMLEvent event = reader.nextTag();
        
        if (!event.isStartElement() || !tag.equals(event.asStartElement().getName())) {
            throw new XMLStreamException("Expected <" + tag + ">", event.getLocation());
        }
        
        String result = reader.getElementText();
        
        event = reader.nextTag();

        if (!event.isEndElement()) {
            throw new XMLStreamException("Expected </" + tag + ">", event.getLocation());
        }
        
        return result;
    }
    
    /**
     * Writes the value in an XML element with the given tag.
     * 
     * @param writer The writer to use.
     * @param tag The tag.
     * @param value The value to write.
     * @throws IOException If writing failed.
     * @throws XMLStreamException If the XML is invalid.
     */
    public static void writeString(XMLStreamWriter writer, String tag, String value) 
            throws IOException, XMLStreamException {
        writer.writeStartElement(tag);
        writer.writeCharacters(value);
        writer.writeEndElement();
    }
}

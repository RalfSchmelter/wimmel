/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.design.lib.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.wimmel.design.lib.XMLExportable;

/**
 * A localized string.
 * 
 * @author Ralf Schmelter
 */
public class LocalizedString implements XMLExportable {

    /**
     * The top level tag.
     */
    private static final String TAG1 = "localized-string";

    /**
     * The tag per localization.
     */
    private static final String TAG2 = "localized-string-part";

    /**
     * The string attribute.
     */
    private static final QName ATTR_STRING = new QName("string");
    
    /**
     * The strings.
     */
    private final HashMap<Locale, String> strings;
    
    /**
     * Creates an empty version.
     */
    public LocalizedString() {
        this.strings = new HashMap<Locale, String>();
    }
    
    /**
     * Creates the object.
     * 
     * @param strings The string mapping.
     */
    private LocalizedString(HashMap<Locale, String> strings) {
        this.strings = strings;
    }
    
    /**
     * Adds the localized version of the string.
     * 
     * @param locale The locale.
     * @param string The string.
     */
    public void addString(Locale locale, String string) {
        strings.put(locale, string);
    }
    
    /**
     * Removes the localized version of the string.
     * 
     * @param locale The locale.
     */
    public void removeLocale(Locale locale) {
        strings.remove(locale);
    }
    
    /**
     * Returns the localized version of the string.
     * 
     * @param locale The locale to use.
     * @return The localized version
     */
    public String getString(Locale locale) {
        return LocaleUtils.getFromLocaleMap(locale, strings);
    }
    
    /**
     * Reads the strings from XML.
     * 
     * @param reader The reader to use.
     * @return The localized string.
     * @throws IOException If reading failed.
     * @throws XMLStreamException If the XML is invalid.
     */
    public static LocalizedString readFromXML(XMLEventReader reader) throws IOException, XMLStreamException {
        XMLEvent event = reader.nextTag();
        
        if (!event.isStartElement() || !TAG1.equals(event.asStartElement().getName())) {
            throw new XMLStreamException("Expected <" + TAG1 + ">", event.getLocation());
        }
        
        HashMap<Locale, String> strings = new HashMap<Locale, String>();
        
        while (true) {
            event = reader.nextTag();
            
            if (event.isEndElement()) {
                return new LocalizedString(strings);
            }
            
            StartElement element = event.asStartElement();
            
            if (!TAG2.equals(element.getName())) {
                throw new XMLStreamException("Expected <" + TAG2 + ">", event.getLocation());
            }
           
            Attribute lang = element.getAttributeByName(ATTR_LANG);
            Attribute country = element.getAttributeByName(ATTR_COUNTRY);
            Attribute variant = element.getAttributeByName(ATTR_VARIANT);
            Attribute string = element.getAttributeByName(ATTR_STRING);
            
            Locale locale = null;
            
            if (lang == null) {
                throw new XMLStreamException("Expected " + ATTR_LANG.getLocalPart() + " attribute",
                        event.getLocation());                
            }
            
            if (country == null) {
                locale = new Locale(lang.getValue());
            }
            else if (variant == null) {
                locale = new Locale(lang.getValue(), country.getValue());
            }
            else {
                locale = new Locale(lang.getValue(), country.getValue(), variant.getValue());
            }

            if (string == null) {
                throw new XMLStreamException("Expected " + ATTR_STRING.getLocalPart() + " attribute",
                        event.getLocation());                
            }
            
            strings.put(locale, string.getValue());

            event = reader.nextTag();
            
            if (!event.isEndElement() || !TAG2.equals(event.asEndElement().getName())) {
                throw new XMLStreamException("Expected </" + TAG2 + ">", event.getLocation());                
            }
        }
    }

    /**
     * @see org.wimmel.design.lib.XMLExportable#writeAsXML(javax.xml.stream.XMLStreamWriter)
     */
    @Override
    public void writeAsXML(XMLStreamWriter writer) throws IOException, XMLStreamException {
        writer.writeStartElement(TAG1);
        
        for (Map.Entry<Locale, String> entry: strings.entrySet()) {
            Locale locale = entry.getKey();
            
            writer.writeStartElement(TAG2);
            writer.writeAttribute(ATTR_LANG.getLocalPart(), locale.getLanguage());
            
            if (locale.getCountry() != null) {
                writer.writeAttribute(ATTR_COUNTRY.getLocalPart(), locale.getCountry());
                
                if (locale.getVariant() != null) {
                    writer.writeAttribute(ATTR_VARIANT.getLocalPart(), locale.getVariant());
                }
            }
            
            writer.writeAttribute(ATTR_STRING.getLocalPart(), entry.getValue());
            writer.writeEndElement();
        }
        
        writer.writeEndElement();
    }
}

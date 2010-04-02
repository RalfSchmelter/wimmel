/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.design.lib.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.wimmel.design.lib.DesignItem;

/**
 * Implementation of the {@link DesignItem} interface.
 * 
 * @author Ralf Schmelter
 */
public class DesignItemImpl implements DesignItem {

    /**
     * The top level tag.
     */
    private static final String TAG = "design-item";

    /**
     * The id attribute.
     */
    private static final QName ATTR_ID = new QName("id");
    
    /**
     * The names of the item.
     */
    private final Map<Locale, String[]> names;
    
    /**
     * The image data.
     */
    private byte[] data;
    
    /**
     * The id.
     */
    private String id;
    
    /**
     * Creates the object.
     * 
     * @param id The id.
     * @param data The image data.
     */
    public DesignItemImpl(String id, byte[] data) {
        this.names = new HashMap<Locale, String[]>();
        this.id = id;
        this.data = data;
    }
    
    /**
     * @see org.wimmel.design.lib.DesignItem#getNames(java.util.Locale)
     */
    @Override
    public String[] getNames(Locale locale) {
        return LocaleUtils.getFromLocaleMap(locale, names);
    }

    /**
     * @see org.wimmel.design.lib.DesignItem#setNames(java.util.Locale, java.lang.String[])
     */
    @Override
    public void setNames(Locale locale, String[] names) {
       this.names.put(locale, names);
    }

    /**
     * @see org.wimmel.lib.Item#getImageData()
     */
    @Override
    public byte[] getImageData() {
        return data;
    }

    /**
     * @see org.wimmel.design.lib.LibraryItem#getByteData()
     */
    @Override
    public byte[] getByteData() {
        return data;
    }

    /**
     * @see org.wimmel.design.lib.LibraryItem#getId()
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * @see org.wimmel.design.lib.DesignItem#getSupportedLocales()
     */
    @Override
    public Locale[] getSupportedLocales() {
        return names.keySet().toArray(new Locale[0]);
    }

    /**
     * @see org.wimmel.design.lib.LibraryItem#merge(org.wimmel.design.lib.LibraryItem, boolean)
     */
    @Override
    public void merge(DesignItem other, boolean keepYours) {
        if (!keepYours) {
            id = other.getId();
            data = other.getImageData();
            
            for (Locale locale: other.getSupportedLocales()) {
                names.put(locale, other.getNames(locale));
            }
        }
        else {
            for (Locale locale: other.getSupportedLocales()) {
                if (!names.containsKey(locale)) {
                    names.put(locale, other.getNames(locale));
                }
            }
        }
    }

    /**
     * @see org.wimmel.design.lib.LibraryItem#setByteData(byte[])
     */
    @Override
    public void setByteData(byte[] data) {
        this.data = data;
    }

    /**
     * @see org.wimmel.design.lib.LibraryItem#setId(java.lang.String)
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @see org.wimmel.design.lib.DesignItem#setImageData(byte[])
     */
    @Override
    public void setImageData(byte[] data) {
        this.data = data;
    }

    /**
     * @see org.wimmel.design.lib.XMLExportable#writeAsXML(javax.xml.stream.XMLStreamWriter)
     */
    @Override
    public void writeAsXML(XMLStreamWriter writer) throws IOException,
            XMLStreamException {
        writer.writeStartElement(TAG);
        writer.writeAttribute(ATTR_ID.getLocalPart(), id);
        writer.writeEndDocument();
    }
}

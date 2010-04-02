/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.design.lib;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * The interface for objects which can be exported to XML.
 * 
 * @author Ralf Schmelter
 */
public interface XMLExportable {

    /**
     * Exports the content as XML. We will start a new entry.
     * 
     * @param writer The writer to use.
     * @throws IOException If writing failed.
     * @throws XMLStreamException If the XML is invalid.
     */
    public void writeAsXML(XMLStreamWriter writer) throws IOException, XMLStreamException;
}

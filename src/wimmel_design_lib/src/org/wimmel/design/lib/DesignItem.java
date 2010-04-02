/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.design.lib;

import java.util.Locale;

import org.wimmel.lib.Item;

/**
 * The design time representation of an item.
 * 
 * @author Ralf Schmelter
 */
public interface DesignItem extends Item, LibraryItem<DesignItem> {

    /**
     * Sets the image data.
     * 
     * @param data The image data.
     */
    public void setImageData(byte[] data);
    
    /**
     * Returns a list of supported locales.
     * 
     * @return a list of supported locales.
     */
    public Locale[] getSupportedLocales();
    
    /**
     * Returns the names of the item for the given locale.
     * 
     * @param locale The locale.
     * @return The names.
     */
    public String[] getNames(Locale locale);
    
    /**
     * Sets the names of the item for the given locale.
     * 
     * @param locale The locale.
     * @param names The names.
     */
    public void setNames(Locale locale, String[] names);
}

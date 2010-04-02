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
     * Returns the names of the item for the given locale.
     * 
     * @param locale The locale.
     * @return The names.
     */
    public String getNames(Locale locale);
    
    /**
     * Sets the names of the item for the given locale.
     * 
     * @param locale The locale.
     * @param names The names.
     */
    public void setNames(Locale locale, String[] names);
}

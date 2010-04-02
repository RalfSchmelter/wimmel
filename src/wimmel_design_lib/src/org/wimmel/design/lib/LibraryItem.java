/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.design.lib;

/**
 * The interface for all items which can be put into a {@link Library}.
 * 
 * @param <T> The type of the item.
 * @author Ralf Schmelter
 */
public interface LibraryItem<T extends LibraryItem<T>> extends XMLExportable {

    /**
     * Returns the id of the item.
     * 
     * @return The id.
     */
    public String getId();
    
    /**
     * Sets the id of the item.
     * 
     * @param id The new id.
     */
    public void setId(String id);
    
    /**
     * Returns the additional data used by the item.
     * 
     * @return The additional data.
     */
    public byte[] getByteData();
    
    /**
     * Sets the additional byte data of the item.
     * 
     * @param data The data to set.
     */
    public void setByteData(byte[] data);
    
    /**
     * Merges the data from the other item to this item.
     * 
     * @param other The other item.
     * @param keepYours If <code>true</code> we used the original data in cases of conflict, otherwise
     *        we use the data from <code>other</code>.
     */
    public void merge(T other, boolean keepYours);
}

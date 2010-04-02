/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.design.lib;

import java.util.List;

/**
 * The interface for all libraries.
 * 
 * @param <T> The type of objects in the library.
 * @author Ralf Schmelter
 */
public interface Library<T extends LibraryItem<T>> extends XMLExportable {
    
    /**
     * Returns all items in the library.
     * 
     * @return The items in the library.
     */
    public List<T> getItems();
    
    /**
     * Adds the item to the library. If an item with the same id already exists, it is replaced.
     * 
     * @param item The item to add.
     */
    public void addItem(T item);
    
    /**
     * Removed the item from the library.
     * 
     * @param item The item to add.
     */
    public void removeItem(T item);
    
    /**
     * Changes the id of an item. If an item with the same id already exists, it is replaced.
     * 
     * @param item The item to change. Must be in the library.
     * @param newId The new id of that item.
     */
    public void renameItem(T item, String newId);
    
    /**
     * Returns the id of the library.
     * 
     * @return the id.
     */
    public String getId();
    
    /**
     * Sets the id of the library.
     * 
     * @param id The new id.
     */
    public void setId(String id);
    
    /**
     * Merges the items from the other library to this library.
     * 
     * @param other The other library.
     * @param keepYours If <code>true</code> we used the original data in cases of conflict, otherwise
     *        we use the data from <code>other</code>.
     */
    public void merge(Library<T> other, boolean keepYours);
}

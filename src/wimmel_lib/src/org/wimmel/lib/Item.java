/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib;

/**
 * Objects of this type represent an item which can be placed onto a {@link Background} to create a
 * {@link Screen}. This interface only includes the methods needed at runtime in a created game.
 * 
 * @author Ralf Schmelter
 */
public interface Item {
    
    /**
     * Returns the image data as a byte array.
     * 
     * @return the image data.
     */
    public byte[] getImageData();
}

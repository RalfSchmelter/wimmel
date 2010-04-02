/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib;

/**
 * Objects of this type act as the background of a {@link Screen}. This interface only includes the methods
 * needed at runtime in a created game.
 * 
 * @author Ralf Schmelter
 */
public interface Background {
    
    /**
     * Returns the image data as a byte array.
     * 
     * @return the image data.
     */
    public byte[] getImageData();
}

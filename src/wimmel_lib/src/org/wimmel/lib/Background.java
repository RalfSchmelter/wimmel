/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib;

import java.awt.image.BufferedImage;
import java.io.IOException;

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
    
    /**
     * Returns the image.
     * 
     * @param screenWidth The width of the screen.
     * @return The image.
     * @throws IOException If reading the image failed.
     */
    public BufferedImage getImage(int screenWidth) throws IOException;
}

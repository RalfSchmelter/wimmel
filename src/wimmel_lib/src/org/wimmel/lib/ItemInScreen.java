/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib;

import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This represents an {@link Item} in a {@link Screen}. The coordinates are relatively to the width of
 * the screen. This interface only includes the methods needed at runtime in a created game.
 * 
 * @author Ralf Schmelter
 */
public interface ItemInScreen {
    
    /**
     * Returns the image to use for the given screen width.
     * 
     * @param screenWidth The width of the screen.
     * @return The image to use.
     * @throws IOException If the image could not be loaded.
     */
    public BufferedImage getImage(int screenWidth) throws IOException;
    
    /**
     * Returns the position of the top left corner of the image returned by {@link #getImage()}.
     * 
     * @param screenWidth The width of the screen.
     * @return The position of the image in the screen.
     * @throws IOException If the image could not be loaded.
     */
    public Point getScreenPos(int screenWidth) throws IOException;

    /**
     * Returns the bounding box of the image.
     * 
     * @param screenWidth The width of the screen.
     * @return The bounding box as an array of 4 points.
     * @throws IOException If the image could not be loaded.
     */
    public Point2D[] getBoundingBox(int screenWidth) throws IOException;
    
    /**
     * Returns the bounding shape of the image. This should be used for hit testing.
     * 
     * @param screenWidth The width of the screen.
     * @return The bounding shape.
     * @throws IOException If the image could not be loaded.
     */
    public Path2D getBounds(int screenWidth) throws IOException;
    
    /**
     * Returns the represented item.
     * 
     * @return the represented item.
     */
    public Item getItem();
    
    /**
     * Returns the item effect to be applied to the item.
     * 
     * @return The item effect.
     */
    public ItemEffect getItemEffect();
}

/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib;

import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

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
     */
    public BufferedImage getImage(int screenWidth);
    
    /**
     * Returns the position of the top left corner of the image returned by {@link #getImage(int)}.
     * 
     * @param screenWidth The width of the screen.
     * @return The position of the image in the screen.
     */
    public Point getScreenPos(int screenWidth);

    /**
     * Returns the bounding box of the image.
     * 
     * @param screenWidth The width of the screen.
     * @return The bounding box.
     */
    public Path2D getBoundingBox(int screenWidth);
    
    /**
     * Returns the bounding shape of the image. This should be used for hit testing.
     * 
     * @param screenWidth The width of the screen.
     * @return The bounding shape.
     */
    public Path2D getBounds(int screenWidth);
    
    /**
     * Returns the represented item.
     * 
     * @return the represented item.
     */
    public Item getItem();
    
    /**
     * Returns the x-coordinate of the center of the item.
     * 
     * @return the x-coordinate.
     */
    public double getX();
    
    /**
     * Returns the y-coordinate of the center of the item.
     * 
     * @return the y-coordinate.
     */
    public double getY();
    
    /**
     * Returns the scale factor in x-direction.
     * 
     * @return the scale factor in x-direction.
     */
    public double getScaleX();
    
    /**
     * Returns the scale factor in y-direction.
     * 
     * @return the scale factor in y-direction.
     */
    public double getScaleY();

    /**
     * Returns the rotation in degrees.
     * 
     * @return the rotation in degrees.
     */
    public double getRotation();
    
    /**
     * Returns the alpha value between 0.0 and 1.0.
     * 
     * @return the alpha value.
     */
    public double getAlpha();
    
    /**
     * Returns the saturation value between 0.0 and 1.0.
     * 
     * @return the saturation value between 0.0 and 1.0.
     */
    public double getSaturation();
    
    /**
     * Returns the hue factor as a value between 0.0 and 1.0.
     * 
     * @return the hue factor.
     */
    public double getHueFactor();
    
    /**
     * Returns the RGB values of the tint to use.
     * 
     * @return the tint.
     */
    public int[] getTint();
    
    /**
     * Returns the factor of tinting to use (0.0 means no tinting, 1.0 means full tinting).
     * 
     * @return the factor of tinting to use
     */
    public double getTintFactor();
}

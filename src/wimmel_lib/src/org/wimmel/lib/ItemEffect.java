/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib;

import java.io.Serializable;

/**
 * Represents the effect we can apply to an item.
 * 
 * @author Ralf Schmelter
 */
public class ItemEffect implements Serializable {

    /**
     * The UID.
     */
    private static final long serialVersionUID = -6644359883631647033L;

    /**
     * The x-coordinate of the center as a fraction of the screen size.
     */
    public double x;

    /**
     * The y-coordinate of the center as a fraction of the screen size.
     */
    public double y;
    
    /**
     * The width as a fraction of the screen width.
     */
    public double width;
    
    /**
     * The height as a fraction of the screen width.
     */
    public double height;

    /**
     * The rotation in degrees.
     */
    public double rotation;
    
    /**
     * The alpha value between 0.0 and 1.0.
     */
    public double alpha;
    
    /**
     * The saturation value between 0.0 and 1.0.
     */
    public double saturation;
    
    /**
     * The hue factor as a value between 0.0 and 1.0.
     */
    public double hueFactor;
    
    /**
     * The RGB values of the tint to use.
     */
    public int[] tint;
    
    /**
     * The factor of tinting to use (0.0 means no tinting, 1.0 means full tinting).
     */
    public double tintFactor;
}

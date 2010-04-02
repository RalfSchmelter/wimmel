/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib;

/**
 * This represents a screen with a {@link Background} and a set of {@link Item} objects. This interface 
 * only includes the methods needed at runtime in a created game.
 * 
 * @author Ralf Schmelter
 */
public interface Screen {
    
    /**
     * Returns the background to use.
     * 
     * @return The background.
     */
    public Background getBackground();
    
    /**
     * Returns the items in the screen.
     * 
     * @return the items.
     */
    public ItemInScreen[] getItems();

}

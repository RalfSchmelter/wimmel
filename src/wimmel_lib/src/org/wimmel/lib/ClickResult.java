/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib;

/**
 * Represents the result of a click.
 * 
 * @author Ralf Schmelter
 */
public class ClickResult {

    /**
     * The items removed as a result of the click.
     */
    public ItemInScreen[] removed;
    
    /**
     * The current item to use (the one which follows the mouse).
     */
    public ItemInScreen current;
    
    /**
     * The points gotten as the result of the click. -1 means to use the default number of points.
     */
    public int points;
    
    /**
     * If <code>true</code> the riddle was finished as a result of the click.
     */
    public boolean finished;
    
    /**
     * If <code>true</code> the riddle was updated as a result of the click.
     */
    public boolean updated;
}

/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib;

import java.util.Locale;

/**
 * Represents a difficulty of a game. This interface only includes the methods needed at runtime in a 
 * created game.
 * 
 * @author Ralf Schmelter
 */
public interface Difficulty {

    /**
     * Returns the value of the difficulty. Different difficulties have to have different values.
     * 
     * @return the value.
     */
    public int getValue();
    
    /**
     * Returns the name of the difficulty.
     * 
     * @param locale The locale to use.
     * @return The name.
     */
    public String getName(Locale locale);
    
    /**
     * Returns the description of the difficulty.
     * 
     * @param locale The locale to use.
     * @return The description.
     */
    public String getDescription(Locale locale);
}

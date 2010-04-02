/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib;

import java.util.Locale;

/**
 * This represents a level in the game. This is the base class for all types of levels. This interface only
 * includes the methods needed at runtime in a created game.
 * 
 * @author Ralf Schmelter
 */
public interface Level {

    /**
     * Returns the associated screen.
     * 
     * @return The associated screen.
     */
    public Screen getScreen();
    
    /**
     * Returns the level settings.
     * 
     * @return the level settings.
     */
    public LevelSettings getSettings();
    
    /**
     * Returns the name of the level.
     * 
     * @param locale The locale to use.
     * @return The name.
     */
    public String getName(Locale locale);
    
}

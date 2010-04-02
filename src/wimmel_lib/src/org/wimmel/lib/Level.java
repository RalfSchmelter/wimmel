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
    
    /**
     * Returns the riddles of this level. The riddle will depend on the locale, since some riddles might
     * depend on the locale (e.g. because 2 different items might have the same name in one language and
     * different names in another, so they would be grouped in the first language but not in the
     * second).
     * 
     * @param locale The locale.
     * @return The riddles.
     */
    public Riddle[] getRiddles(Locale locale);
    
}

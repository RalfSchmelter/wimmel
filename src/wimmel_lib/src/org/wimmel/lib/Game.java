/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib;

import java.util.Locale;

/**
 * Represents a complete game which consists of a set of {@link Level} objects,
 * 
 * @author Ralf Schmelter
 */
public interface Game {

    /**
     * Returns the number of supported difficulty levels.
     * 
     * @return the difficulties.
     */
    public Difficulty[] getDifficulties();
    
    /**
     * Returns the default level settings.
     * 
     * @return the default level settings.
     */
    public LevelSettings getLevelSettings();
    
    /**
     * Returns the levels.
     * 
     * @return The levels.
     */
    public Level[] getLevels();
    
    /**
     * Returns the name of the game.
     * 
     * @param locale The locale.
     * @return The name.
     */
    public String getName(Locale locale);
}

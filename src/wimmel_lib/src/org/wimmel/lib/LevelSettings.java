/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib;

/**
 * Contains the general settings of a level.
 * 
 * @author Ralf Schmelter
 */
public interface LevelSettings {
    
    /**
     * Returns the time in seconds to solve the level. -1 means to use the default time.
     * 
     * @param difficulty The difficulty.
     * @return The time in seconds.
     */
    public int getTime(Difficulty difficulty);
    
    /**
     * Returns the maximum number of hints to get. -1 means to use the default number of hints.
     * 
     * @param difficulty The difficulty.
     * @return the maximum number of hints.
     */
    public int getMaxHints(Difficulty difficulty);
    
    /**
     * Returns the timeout between two hints in seconds. -1 menas to use the default timeout.
     * 
     * @param difficulty The difficulty.
     * @return the hint timeout.
     */
    public int getHintTimeout(Difficulty difficulty);
    
    /**
     * Returns the size of the initial hint area as a percentage of the screen width. 0.0 means to
     * use the default size.
     * 
     * @param difficulty The difficulty.
     * @return The initial hint area size.
     */
    public double getInitialHintSize(Difficulty difficulty);
    
    /**
     * Returns the factor by which the size of the hint for the same {@link Riddle} is reduced by
     * each hint. 0.0 means to use the default factor.
     * 
     * @param difficulty The difficulty.
     * @return The hint size reduction factor.
     */
    public double getHintSizeScaleFactor(Difficulty difficulty);
    
    /**
     * Returns the penalty in points for using a hint. -1 means to use the default penalty.
     * 
     * @param difficulty The difficulty.
     * @return The hint penalty.
     */
    public int getHintPenalty(Difficulty difficulty);
    
    /**
     * Returns the minimum number of points to get a hint. -1 means to use the default value.
     * 
     * @param difficulty The difficulty.
     * @return the minimum number of points for a hint.
     */
    public int minimumPointsForHint(Difficulty difficulty);
    
    /**
     * Returns the default number of points for solving a riddle. -1 means to use the default value.
     * 
     * @param difficulty The difficulty.
     * @return The points for solving a riddle.
     */
    public int getRiddlePoints(Difficulty difficulty);
    
    /**
     * Returns the penalty in points for a wrong click.
     * 
     * @param difficulty The difficulty.
     * @return the penalty in points for a wrong click.
     */
    public int getMisclickPenalty(Difficulty difficulty);
}

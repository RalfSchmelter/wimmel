/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib;

import java.util.Locale;
import java.util.Set;

/**
 * The interface for riddles of a {@link Level}.
 * 
 * @author Ralf Schmelter
 */
public interface Riddle {

    /**
     * Returns the associated items of the riddle.
     * 
     * @return the items.
     */
    public ItemInScreen[] getItems();
    
    /**
     * Returns the result of clicking the given item
     * 
     * @param clicked The item clicked.
     * @param current The item currently active. This can be <code>null</code>.
     * @param itemsLeft The items still left on the screen.
     * @return the result
     */
    public ClickResult getClickResult(ItemInScreen clicked, ItemInScreen current,
            Set<ItemInScreen> itemsLeft);
    
    /**
     * Returns the text of the riddle.
     * 
     * @param locale The locale.
     * @param itemsLeft The items still on the screen.
     * @return The text.
     */
    public String getText(Locale locale, Set<ItemInScreen> itemsLeft);
    
    /**
     * Returns <code>true</code> if this is an invisible riddle which isn't explicitly shown (but
     * can still be solved).
     * 
     * @return <code>true</code> for an invisible riddle.
     */
    public boolean isInvisible();
    
    /**
     * Returns the global id of the riddle. A riddle with a global id can only be solved once during a
     * game. Returns <code>null</code> if this is not a global riddle.
     * 
     * @return the global id.
     */
    public String getGlobalId();
    
    /**
     * Returns the successor riddle. This is the riddle which replaces this riddle when this riddle is
     * solved. Returns <code>null</code> if we have no successor.
     * 
     * @return The successor riddle or <code>null</code>.
     */
    public Riddle getSuccessor();
}

/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib.impl;

import java.io.Serializable;

import org.wimmel.lib.Item;

/**
 * Implementation of the {@link Item} interface to be used a runtime.
 * 
 * @author Ralf Schmelter
 */
public class ItemImpl implements Item, Serializable {
    
    /**
     * The serial version id.
     */
    private static final long serialVersionUID = 3476809329533305912L;
    
    /**
     * The raw image data.
     */
    private final byte[] imageData;
    
    /**
     * Creates the object.
     * 
     * @param imageData The image data to use.
     */
    public ItemImpl(byte[] imageData) {
        this.imageData = imageData;
    }
    
    /**
     * @see org.wimmel.lib.Item#getImageData()
     */
    @Override
    public byte[] getImageData() {
        return imageData;
    }
}

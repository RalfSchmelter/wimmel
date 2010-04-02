/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib.impl;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.SoftReference;

import javax.imageio.ImageIO;

import org.wimmel.lib.Background;

/**
 * Implementation of the {@link Background} interface to be used at runtime.
 * 
 * @author Ralf Schmelter
 */
public class BackgroundImpl implements Background, Serializable {

    /**
     * The UID.
     */
    private static final long serialVersionUID = 2645352328167179176L;

    /**
     * The image data.
     */
    private final byte[] imageData;

    /**
     * The buffered image representing the source item.
     */
    private transient SoftReference<BufferedImage> srcImage;
    
    /**
     * The buffered image representing the destination item.
     */
    private transient SoftReference<BufferedImage> destImage;
    
    /**
     * The screen width for which the soft references were cached.
     */
    private transient int lastScreenWidth = -1;

    /**
     * Creates the object.
     * 
     * @param imageData The image data.
     */
    public BackgroundImpl(byte[] imageData) {
        this.imageData = imageData;
    }

    /**
     * @see org.wimmel.lib.Background#getImageData()
     */
    @Override
    public byte[] getImageData() {
        return imageData;
    }

    /**
     * @see org.wimmel.lib.ItemInScreen#getImage(int)
     */
    @Override
    public BufferedImage getImage(int screenWidth) throws IOException {
        BufferedImage result = null;

        if (screenWidth != lastScreenWidth) {
            destImage = null;
        }
        
        lastScreenWidth = screenWidth;
        
        if (destImage != null) {
            result = destImage.get();
        }
        
        if (result == null) {
            result = getImageImpl(screenWidth);
            destImage = new SoftReference<BufferedImage>(result);
        }
        
        
        return result;
    }
    
    /**
     * Implements getting the buffered image from the item image data.
     * 
     * @param screenWidth The screen width.
     * @return The buffered image.
     */
    private BufferedImage getImageImpl(int screenWidth) throws IOException {
        BufferedImage initialImage = getSourceImage();
        int width = initialImage.getWidth();
        int height = initialImage.getHeight();
        double scale = 1.0 * screenWidth / width;
        int destWidth = (int) (width * scale);
        int destHeight = (int) (height * scale);
        
        BufferedImage result = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphics = result.createGraphics();
        graphics.drawImage(initialImage, AffineTransform.getScaleInstance(scale, scale), null);
        graphics.finalize();
        
        return result;
    }
    
    /**
     * Returns the source image or <code>null</code> if it could not be loaded.
     * 
     * @return The source image or <code>null</code>.
     * @throws IOException If reading the source image failed.
     */
    private BufferedImage getSourceImage() throws IOException {
        BufferedImage result = null;
        
        if (srcImage != null) {
            result = srcImage.get();
        }
        
        if (result == null) {
            BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(imageData));
            result = ImageIO.read(bis);
            srcImage = new SoftReference<BufferedImage>(result);
        }
        
        return result;
    }
}

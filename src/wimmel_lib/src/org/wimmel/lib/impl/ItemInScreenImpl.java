/**
 * (C) 2010 by Ralf Schmelter
 */
package org.wimmel.lib.impl;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.SoftReference;

import javax.imageio.ImageIO;

import org.wimmel.lib.Item;
import org.wimmel.lib.ItemEffect;
import org.wimmel.lib.ItemInScreen;

/**
 * Implementation of the {@link ItemInScreen} interface for the runtime.
 * 
 * @author Ralf Schmelter
 */
public class ItemInScreenImpl implements ItemInScreen, Serializable {

    /**
     * The UID.
     */
    private static final long serialVersionUID = 2645352328167179176L;

    /**
     * The associated item.
     */
    private final Item item;

    /**
     * The buffered image representing the source item.
     */
    private transient SoftReference<BufferedImage> srcImage;
    
    /**
     * The buffered image representing the destination item.
     */
    private transient SoftReference<BufferedImage> destImage;
    
    /**
     * The bounding box.
     */
    private transient SoftReference<Point2D[]> boundingBox;
    
    /**
     * The bounds.
     */
    private transient SoftReference<Path2D> bounds;
    
    /**
     * The screen width for which the soft references were cached.
     */
    private transient int lastScreenWidth = -1;
    
    /**
     * The item effect.
     */
    private final ItemEffect itemEffect;

    /**
     * Creates the object.
     * 
     * @param item The item.
     * @param itemEffect The item effect to apply.
     */
    public ItemInScreenImpl(Item item, ItemEffect itemEffect) {
        this.item = item;
        this.itemEffect = itemEffect;
    }
    
    /**
     * @see org.wimmel.lib.ItemInScreen#getBoundingBox(int)
     */
    @Override
    public Point2D[] getBoundingBox(int screenWidth) throws IOException {
        Point2D[] result = null;
        clearCacheForScreenWidthChange(screenWidth);
        
        if (boundingBox != null) {
            result = boundingBox.get();
        }
        
        if (result == null) {
            result = getBoundingBoxImpl(screenWidth);
            boundingBox = new SoftReference<Point2D[]>(result);
        }
        
        return result;
    }
    
    /**
     * Calculates the bounding box.
     * 
     * @param screenWidth The width of the screen.
     * @return The bounding box.
     * @throws IOException If reading the image failed.
     */
    private Point2D[] getBoundingBoxImpl(int screenWidth) throws IOException {
        AffineTransform transform = getTransformForOrigin(screenWidth);
        transform.concatenate(AffineTransform.getTranslateInstance(itemEffect.x * screenWidth,
                                                                   itemEffect.y * screenWidth));
        BufferedImage image = getSourceImage();
        Point2D[] dest = new Point2D[4];
        Point2D[] src = new Point2D[] {
                new Point2D.Double(0, 0), 
                new Point2D.Double(image.getWidth(), 0),
                new Point2D.Double(image.getWidth(), image.getHeight()), 
                new Point2D.Double(0, image.getHeight())
        };
        
        transform.transform(src, 0, dest, 0, src.length);
        
        return dest;
    }

    /**
     * @see org.wimmel.lib.ItemInScreen#getBounds(int)
     */
    @Override
    public Path2D getBounds(int screenWidth) throws IOException {
        Path2D result = null;
        clearCacheForScreenWidthChange(screenWidth);
        
        if (bounds != null) {
            result = bounds.get();
        }
        
        if (result == null) {
            result = getBoundsImpl(screenWidth);
            bounds = new SoftReference<Path2D>(result);
        }
        
        return result;
    }

    /**
     * Calculates the bounds.
     * 
     * @param screenWidth The screen width.
     * @return The bounds.
     * @throws IOException If reading the image failed.
     */
    private Path2D getBoundsImpl(int screenWidth) throws IOException {
        // TODO: Implement
        return null;
    }

    /**
     * @see org.wimmel.lib.ItemInScreen#getImage(int)
     */
    @Override
    public BufferedImage getImage(int screenWidth) throws IOException {
        BufferedImage result = null;
        clearCacheForScreenWidthChange(screenWidth);
        
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
     * @see org.wimmel.lib.ItemInScreen#getItem()
     */
    @Override
    public Item getItem() {
        return item;
    }

    /**
     * @see org.wimmel.lib.ItemInScreen#getItemEffect()
     */
    @Override
    public ItemEffect getItemEffect() {
        return itemEffect;
    }

    /**
     * @see org.wimmel.lib.ItemInScreen#getScreenPos(int)
     */
    @Override
    public Point getScreenPos(int screenWidth) throws IOException {
        return getBoundingBoxRectangle(screenWidth).getLocation();
    }
    
    /**
     * Implements getting the buffered image from the item image data.
     * 
     * @param screenWidth The screen width.
     * @return The buffered image.
     */
    private BufferedImage getImageImpl(int screenWidth) throws IOException {
        BufferedImage initialImage = getSourceImage();
        Rectangle rect = getBoundingBoxRectangle(screenWidth);
        int width = rect.width;
        int height = rect.height;

        AffineTransform translate = AffineTransform.getTranslateInstance(width / 2.0, height / 2.0);
        AffineTransform scale = AffineTransform.getScaleInstance(itemEffect.width * screenWidth / width,
                                                                 itemEffect.height * screenWidth / height);
        AffineTransform rotate = AffineTransform.getRotateInstance(itemEffect.alpha * Math.PI / 180.0);
        AffineTransform transform = translate;
        transform.concatenate(scale);
        transform.concatenate(rotate);
        
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = result.createGraphics();
        graphics.drawImage(initialImage, transform, null);
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
            BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(item.getImageData()));
            result = ImageIO.read(bis);
            srcImage = new SoftReference<BufferedImage>(result);
        }
        
        return result;
    }
    
    /**
     * Returns the transformation for the image effect, only that the center of the resulting image is
     * located at (0,0).
     * 
     * @param screenWidth The width of the screen.
     * @return The transformation.
     * @throws IOException If the image could not be loaded.
     */
    private AffineTransform getTransformForOrigin(int screenWidth) throws IOException {
        Rectangle rect = getBoundingBoxRectangle(screenWidth);
        int width = rect.width;
        int height = rect.height;

        AffineTransform translate = AffineTransform.getTranslateInstance(-width / 2.0, -height / 2.0);
        AffineTransform scale = AffineTransform.getScaleInstance(itemEffect.width * screenWidth / width,
                                                                 itemEffect.height * screenWidth / height);
        AffineTransform rotate = AffineTransform.getRotateInstance(itemEffect.alpha * Math.PI / 180.0);

        AffineTransform transform = translate;
        transform.concatenate(scale);
        transform.concatenate(rotate);
        
        return transform;
    }
    
    /**
     * Returns a rectangle which covers the bounding box (which might be rotated).
     * 
     * @param screenWidth The width of the screen.
     * @return the rectangle.
     * @throws IOException If reading the image failed.
     */
    private Rectangle getBoundingBoxRectangle(int screenWidth) throws IOException {
        Point2D[] boundingBox = getBoundingBox(screenWidth);
        double maxX = boundingBox[0].getX();
        double minX = maxX;
        double maxY = boundingBox[0].getY();
        double minY = maxY;
        
        for (Point2D point: boundingBox) {
            maxX = Math.max(maxX, point.getX());
            minX = Math.min(minX, point.getX());
            maxY = Math.max(maxY, point.getY());
            minY = Math.min(minY, point.getY());
        }

        int x1 = (int) Math.floor(minX);
        int x2 = (int) Math.ceil(maxX);
        int y1 = (int) Math.floor(minY);
        int y2 = (int) Math.ceil(maxY);
        
        return new Rectangle(x1, y1, x2 - y1, y2 - y1);
    }
    
    /**
     * Clears the caches on a screen width change.
     * 
     * @param screenWidth Thenew screen width.
     */
    private void clearCacheForScreenWidthChange(int screenWidth) {
        if (screenWidth != lastScreenWidth) {
            destImage = null;
            boundingBox = null;
            bounds = null;
        }
        
        lastScreenWidth = screenWidth;
    }
}

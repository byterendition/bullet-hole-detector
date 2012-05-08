package controller;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtil {
	private static final Logger	log	= LoggerFactory.getLogger(ImageUtil.class);
	
	public static BufferedImage loadImage(File imageFile) throws IOException {
		try {
			return ImageIO.read(imageFile);
		} catch (IOException e) {
			log.error("Unable to load '{}'", imageFile.getName());
			throw e;
		}
	}
	
	public static BufferedImage scaleImagePreserveAspect(BufferedImage image, int boundsWidth, int boundsHeight) {
		int rawImageWidth = image.getWidth();
		int rawImageHeight = image.getHeight();
		
		// preserve the original ratio
		float imageRatio = (float) rawImageWidth / (float) rawImageHeight;
		float panelRatio = (float) boundsWidth / (float) boundsHeight;
		int newWidth;
		int newHeight;
		if (imageRatio > panelRatio) {
			newWidth = boundsWidth;
			newHeight = (int) (boundsWidth / imageRatio);
		} else {
			newWidth = (int) (boundsHeight * imageRatio);
			newHeight = boundsHeight;
		}
		
		if (newWidth == rawImageWidth && newHeight == rawImageHeight) {
			return image;
		} else {
			BufferedImage scaledImage = image;
			
			int intermediateWidth = rawImageWidth / 2;
			int intermediateHeight = rawImageHeight / 2;
			while (intermediateWidth > newWidth) {
				BufferedImage newScaledImage = new BufferedImage(intermediateWidth, intermediateHeight, BufferedImage.TYPE_INT_RGB);
				Graphics2D scaledImageGraphics = newScaledImage.createGraphics();
				scaledImageGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				scaledImageGraphics.drawImage(scaledImage, 0, 0, intermediateWidth, intermediateHeight, null);
				scaledImage = newScaledImage;
				intermediateWidth /= 2;
				intermediateHeight /= 2;
			}
			BufferedImage newScaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D scaledImageGraphics = newScaledImage.createGraphics();
			if (rawImageWidth > newWidth) {
				scaledImageGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			} else {
				scaledImageGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			}
			
			scaledImageGraphics.drawImage(scaledImage, 0, 0, newWidth, newHeight, null);
			scaledImage = newScaledImage;
			
			return scaledImage;
		}
	}
	
	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
}

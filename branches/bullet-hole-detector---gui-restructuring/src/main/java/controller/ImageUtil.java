package controller;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
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
	
	public static BufferedImage scaleImagePreserveAspect(BufferedImage image, int newWidth, int newHeight, Component parent) {
		BufferedImage scaledImage = null;
		try {
			int rawImageWidth = image.getWidth();
			int rawImageHeight = image.getHeight();
			
			// preserve the original ratio
			float imageRatio = (float) rawImageWidth / (float) rawImageHeight;
			float panelRatio = (float) newWidth / (float) newHeight;
			int widthFactor = -1;
			int heightFactor = -1;
			if (imageRatio > panelRatio) {
				widthFactor = newWidth;
			} else {
				heightFactor = newHeight;
			}
			if (widthFactor == -1 && heightFactor == -1) {
				scaledImage = image;
			} else {
				scaledImage = (BufferedImage) image.getScaledInstance(widthFactor, heightFactor, Image.SCALE_SMOOTH);
				// load the new image, 'getScaledInstance' loads asynchronously
				MediaTracker tracker = new MediaTracker(parent);
				tracker.addImage(scaledImage, 0);
				tracker.waitForID(0);
			}
		} catch (InterruptedException ie) {
			log.error("load interrupt: {}", ie.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scaledImage;
	}
}

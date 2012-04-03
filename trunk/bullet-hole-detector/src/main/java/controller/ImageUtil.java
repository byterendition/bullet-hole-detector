package controller;

import java.awt.image.BufferedImage;

public class ImageUtil {
	
	public static BufferedImage getNoImage() {
		return new BufferedImage(320, 240, BufferedImage.TYPE_INT_ARGB);
	}
}

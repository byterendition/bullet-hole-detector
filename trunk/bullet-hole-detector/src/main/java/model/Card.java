package model;

import java.awt.image.BufferedImage;
import java.util.Observable;

public class Card extends Observable {
	private final BufferedImage	image;
	
	public Card(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}
}

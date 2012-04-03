package model;

import java.awt.image.BufferedImage;
import java.util.Observable;

public class Card extends Observable {
	private final BufferedImage	mage;
	
	public Card(BufferedImage image) {
		mage = image;
	}
	
	public BufferedImage getImage() {
		return mage;
	}
}

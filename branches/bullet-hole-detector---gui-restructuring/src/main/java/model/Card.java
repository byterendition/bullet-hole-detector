package model;

import java.io.File;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Card extends Observable {
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(Card.class);
	
	private final File			imageFile;
	private String				name;
	
	public Card(File imageFile) {
		this.imageFile = imageFile;
		name = imageFile.getName();
	}
	
	public File getImageFile() {
		return imageFile;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}

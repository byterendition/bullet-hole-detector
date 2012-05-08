package model;

import java.io.File;
import java.io.Serializable;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.FileUtil;

public class Card extends Observable implements Serializable {
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(Card.class);
	
	private final File			imageFile;
	private String				name;
	
	public Card(File imageFile) {
		this.imageFile = imageFile;
		name = FileUtil.getFileRootName(imageFile);
	}
	
	public File getImageFile() {
		return imageFile;
	}
	
	public File getCardFile() {
		return new File(imageFile.getPath() + FileUtil.getFileRootName(imageFile) + ".crd");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}

package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Card;
import model.FileExtension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	private static final Logger	log	= LoggerFactory.getLogger(FileUtil.class);
	
	public static FileExtension getFileExtension(File file) {
		String filename = file.getName();
		int extensionIndex = filename.lastIndexOf('.');
		
		if (extensionIndex > 0 && extensionIndex < filename.length() - 1) {
			String extension = filename.substring(extensionIndex + 1).toUpperCase();
			
			try {
				return FileExtension.valueOf(extension);
			} catch (IllegalArgumentException e) {
				return null;
			}
		}
		return null;
	}
	
	public static String getFileRootName(File file) {
		String filename = file.getName();
		int extensionIndex = filename.lastIndexOf('.');
		
		if (extensionIndex > 0 && extensionIndex < filename.length() - 1) {
			return filename.substring(0, extensionIndex - 1);
		}
		return "";
	}
	
	public static void saveCard(Card card) {
		try {
			FileOutputStream fileOut = new FileOutputStream(card.getName() + ".crd");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(card);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Card loadCard(File file) {
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Card card = (Card) in.readObject();
			in.close();
			fileIn.close();
			return card;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			log.warn("Card class not found.");
			cnfe.printStackTrace();
			return null;
		}
	}
}

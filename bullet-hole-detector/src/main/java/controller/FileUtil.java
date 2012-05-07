package controller;

import java.io.File;

import model.FileExtension;

public class FileUtil {
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
}

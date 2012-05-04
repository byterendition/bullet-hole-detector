package model;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import controller.FileUtil;

public class ImageFileFilter extends FileFilter {
	@Override
	public boolean accept(File file) {
		if (file == null) {
			return false;
		}
		
		if (file.isDirectory()) {
			return true;
		}
		
		FileExtension fileExtension = FileUtil.getFileExtension(file);
		if (fileExtension != null) {
			switch (fileExtension) {
				case TIFF:
				case TIF:
				case JPEG:
				case JPG:
				case PNG:
				case GIF:
				case BMP:
					return true;
			}
		}
		return false;
	}
	
	@Override
	public String getDescription() {
		return "Image files (.tiff, .tif, .jpeg, .jpg, .png, .gif, .bmp)";
	}
}

package controller;

import view.MainWindow;

public class BulletHoleDetector {
	MainWindow	mainWindow;
	
	public BulletHoleDetector() {
		mainWindow = new MainWindow();
	}
	
	public static void main(String[] args) {
		new BulletHoleDetector();
	}
}

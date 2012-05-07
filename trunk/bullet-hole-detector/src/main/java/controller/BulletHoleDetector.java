package controller;

import javax.swing.SwingUtilities;

import model.Model;
import view.MainWindow;

public class BulletHoleDetector implements Runnable {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new BulletHoleDetector());
	}
	
	@Override
	public void run() {
		Model model = new Model();
		
		MainWindow mainWindow = new MainWindow(model);
		
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
	}
}

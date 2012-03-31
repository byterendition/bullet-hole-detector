package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuListener implements ActionListener {
	private static final Logger	log	= LoggerFactory.getLogger(MenuListener.class);
	
	private MainWindow			mainWindow;
	
	public MenuListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			JMenuItem source = (JMenuItem) e.getSource();
			
			if (source.getName() == "menuItemLoadCards") {
				log.info("Load cards");
				// mainWindow.loadCards();
			}
			
			if (source.getName() == "menuItemExit") {
				log.info("Exit");
				System.exit(0);
			}
		}
	}
}

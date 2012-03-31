package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import model.ImageFileFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuListener implements ActionListener {
	private static final Logger	log	= LoggerFactory.getLogger(MenuListener.class);
	
	private MainWindow			mainWindow;
	
	private JFileChooser		fileChooser;
	
	public MenuListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new ImageFileFilter());
		fileChooser.setMultiSelectionEnabled(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			JMenuItem source = (JMenuItem) e.getSource();
			
			if (source.getName() == "menuItemLoadCards") {
				log.info("Load cards");
				
				int returnVal = fileChooser.showOpenDialog(mainWindow);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File[] fileArr = fileChooser.getSelectedFiles();
					
					for (File file : fileArr) {
						log.info("Opening: {}", file.getName());
					}
				} else {
					log.info("Open command cancelled by user.");
				}
				
				// mainWindow.loadCards();
			}
			
			if (source.getName() == "menuItemExit") {
				log.info("Exit");
				System.exit(0);
			}
		}
	}
}

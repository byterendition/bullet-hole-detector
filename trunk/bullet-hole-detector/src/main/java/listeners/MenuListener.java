package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import model.ImageFileFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.BulletHoleDetector;

public class MenuListener implements ActionListener {
	private static final Logger	log	= LoggerFactory.getLogger(MenuListener.class);
	
	private BulletHoleDetector	bulletHoleDetector;
	
	private JFileChooser		fileChooser;
	
	public MenuListener(BulletHoleDetector bulletHoleDetector) {
		this.bulletHoleDetector = bulletHoleDetector;
		
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new ImageFileFilter());
		fileChooser.setMultiSelectionEnabled(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			JMenuItem source = (JMenuItem) e.getSource();
			
			if (source.getName() == "menuItemNew") {
				log.info("New");
				bulletHoleDetector.cardContainer.clear();
			}
			
			if (source.getName() == "menuItemLoadCards") {
				log.info("Load cards");
				
				int returnVal = fileChooser.showOpenDialog(source);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File[] fileArr = fileChooser.getSelectedFiles();
					
					bulletHoleDetector.loadCards(fileArr);
				} else {
					log.info("Open command cancelled by user.");
				}
			}
			
			if (source.getName() == "menuItemExit") {
				log.info("Exit");
				System.exit(0);
			}
		}
	}
}

package listeners.actions.menu.file;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import model.Card;
import model.CardFileFilter;
import model.ImageFileFilter;
import model.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class LoadCardsAction extends AbstractAction {
	private static final Logger	log	= LoggerFactory.getLogger(LoadCardsAction.class);
	
	private Model				model;
	private JFileChooser		fileChooser;
	
	public LoadCardsAction(Model model) {
		super("Load cards");
		putValue(SHORT_DESCRIPTION, "Load cards");
		putValue(MNEMONIC_KEY, KeyEvent.VK_C);
		this.model = model;
		
		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new CardFileFilter());
		fileChooser.addChoosableFileFilter(new ImageFileFilter());
		fileChooser.setMultiSelectionEnabled(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("Load cards");
		
		int returnVal = fileChooser.showOpenDialog((Component) e.getSource());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File[] fileArr = fileChooser.getSelectedFiles();
			
			loadCards(fileArr);
		} else {
			log.info("Open command cancelled by user.");
		}
	}
	
	private void loadCards(File[] fileArr) {
		for (File file : fileArr) {
			Card card = new Card(file);
			model.cardContainer.addCard(card);
		}
	}
}

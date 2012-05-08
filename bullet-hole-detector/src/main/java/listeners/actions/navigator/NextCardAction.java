package listeners.actions.navigator;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import model.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class NextCardAction extends AbstractAction {
	private static final Logger	log	= LoggerFactory.getLogger(NextCardAction.class);
	
	Model						model;
	
	public NextCardAction(Model model) {
		super("Next card");
		putValue(SHORT_DESCRIPTION, "Go to next card");
		putValue(MNEMONIC_KEY, KeyEvent.VK_N);
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int numCards = model.cardContainer.getNumCards();
		
		if (numCards > 1) {
			model.increaseCurrentCardIndex();
		}
		
		log.debug("Next card");
	}
}

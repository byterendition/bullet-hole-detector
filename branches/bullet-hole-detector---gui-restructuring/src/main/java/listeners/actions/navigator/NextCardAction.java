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
		log.info("Next card");
	}
	
	// if (e.getSource() instanceof JButton) {
	// JButton button = (JButton) e.getSource();
	// int numCards = cardPanel.cardContainer.getNumCards();
	//
	// if (button.getName() == "previousButton") {
	// if (numCards > 1) {
	// cardPanel.currentCardIndex--;
	// if (cardPanel.currentCardIndex < 0) {
	// cardPanel.currentCardIndex = numCards - 1;
	// }
	// }
	// cardPanel.repaint();
	// log.debug("previous card");
	// }
	//
	// if (button.getName() == "nextButton") {
	// if (numCards > 1) {
	// cardPanel.currentCardIndex++;
	// if (cardPanel.currentCardIndex >= numCards) {
	// cardPanel.currentCardIndex = 0;
	// }
	// }
	// cardPanel.repaint();
	// log.debug("next card");
	// }
	//
	// if (button.getName() == "removeButton") {
	// if (numCards > 0) {
	// int response = JOptionPane.showOptionDialog(cardPanel, "Are you sure you want to remove this card?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[] { "Yes", "No" }, "No");
	//
	// if (response == 0) {
	// cardPanel.cardContainer.removeCard(cardPanel.getCurrentCard());
	// cardPanel.repaint();
	// log.debug("remove card");
	// }
	// }
	// }
	// }
}

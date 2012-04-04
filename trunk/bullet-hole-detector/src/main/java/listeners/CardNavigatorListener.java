package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import view.CardPanel;

public class CardNavigatorListener implements ActionListener {
	private static final Logger	log	= LoggerFactory.getLogger(CardNavigatorListener.class);
	
	private CardPanel			cardPanel;
	
	public CardNavigatorListener(CardPanel cardPanel) {
		this.cardPanel = cardPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton button = (JButton) e.getSource();
			int numCards = cardPanel.cardContainer.getNumCards();
			
			if (button.getName() == "previousButton") {
				if (numCards > 1) {
					cardPanel.currentCardIndex--;
					if (cardPanel.currentCardIndex < 0) {
						cardPanel.currentCardIndex = numCards - 1;
					}
				}
				cardPanel.repaint();
				log.debug("previous card");
			}
			
			if (button.getName() == "nextButton") {
				if (numCards > 1) {
					cardPanel.currentCardIndex++;
					if (cardPanel.currentCardIndex >= numCards) {
						cardPanel.currentCardIndex = 0;
					}
				}
				cardPanel.repaint();
				log.debug("next card");
			}
			
			if (button.getName() == "removeButton") {
				if (numCards > 0) {
					int response = JOptionPane.showOptionDialog(cardPanel, "Are you sure you want to remove this card?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[] { "Yes", "No" }, "No");
					log.debug("option: {}", response);
					
					if (response == 0) {
						cardPanel.cardContainer.removeCard(cardPanel.getCurrentCard());
						cardPanel.repaint();
						log.debug("remove card");
					}
				}
			}
		}
	}
}

package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import view.CardZoomPanel;

public class CardZoomNavigatorListener implements ActionListener {
	private static final Logger	log	= LoggerFactory.getLogger(CardZoomNavigatorListener.class);
	
	private CardZoomPanel		cardZoomPanel;
	
	public CardZoomNavigatorListener(CardZoomPanel cardZoomPanel) {
		this.cardZoomPanel = cardZoomPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton button = (JButton) e.getSource();
			int numCards = cardZoomPanel.cardContainer.getNumCards();
			
			if (button.getName() == "previousButton") {
				if (numCards > 1) {
					cardZoomPanel.currentCardIndex--;
					if (cardZoomPanel.currentCardIndex < 0) {
						cardZoomPanel.currentCardIndex = numCards - 1;
					}
				}
				cardZoomPanel.repaint();
				log.debug("previous card");
			}
			
			if (button.getName() == "nextButton") {
				if (numCards > 1) {
					cardZoomPanel.currentCardIndex++;
					if (cardZoomPanel.currentCardIndex >= numCards) {
						cardZoomPanel.currentCardIndex = 0;
					}
				}
				cardZoomPanel.repaint();
				log.debug("next card");
			}
			
			if (button.getName() == "removeButton") {
				if (numCards > 0) {
					int response = JOptionPane.showOptionDialog(cardZoomPanel, "Are you sure you want to remove this card?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[] { "Yes", "No" }, "No");
					
					if (response == 0) {
						cardZoomPanel.cardContainer.removeCard(cardZoomPanel.getCurrentCard());
						cardZoomPanel.repaint();
						log.debug("remove card");
					}
				}
			}
		}
	}
}

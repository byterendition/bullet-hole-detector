package listeners;

import java.util.Observable;
import java.util.Observer;

import model.CardContainer;
import view.CardPanel;

public class CardContainerListener implements Observer {
	CardPanel	cardPanel;
	
	public CardContainerListener(CardPanel cardPanel) {
		this.cardPanel = cardPanel;
	}
	
	public void update(Observable o, Object arg) {
		if (o instanceof CardContainer) {
			CardContainer cardContainer = (CardContainer) o;
			
			if (cardContainer.isEmpty()) {
				cardPanel.currentCardIndex = -1;
				cardPanel.repaint();
			} else {
				if (cardPanel.currentCardIndex < 0 || cardPanel.currentCardIndex >= cardContainer.getNumCards()) {
					cardPanel.currentCardIndex = cardContainer.getNumCards() - 1;
					cardPanel.repaint();
				}
				
				cardPanel.cardNavigatorPanel.repaint();
			}
		}
	}
}

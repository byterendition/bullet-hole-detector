package listeners;

import java.util.Observable;
import java.util.Observer;

import model.Card;
import view.CardPanel;

public class CardListener implements Observer {
	CardPanel	cardPanel;
	
	public CardListener(CardPanel cardPanel) {
		this.cardPanel = cardPanel;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Card) {
			Card card = (Card) o;
		}
	}
}

package listeners;

import java.util.Observable;
import java.util.Observer;

import model.CardContainer;

public class CardContainerListener implements Observer {
	public CardContainerListener() {}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof CardContainer) {
			CardContainer cardContainer = (CardContainer) o;
			
			if (cardContainer.isEmpty()) {} else {}
		}
	}
}

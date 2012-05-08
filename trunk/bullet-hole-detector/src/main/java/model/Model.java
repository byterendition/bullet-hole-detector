package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import controller.CalcUtil;

public class Model extends Observable implements Serializable {
	private static final long	serialVersionUID	= 2257801653080085930L;
	
	private int					currentCardIndex	= -1;
	public CardContainer		cardContainer;
	public Color				bgColor;
	public int					bgFuzziness;
	
	public Model() {
		cardContainer = new CardContainer();
		cardContainer.addObserver(new CardContainerListener());
	}
	
	public Card getCurrentCard() {
		return cardContainer.getCard(currentCardIndex);
	}
	
	public int getCurrentCardIndex() {
		return currentCardIndex;
	}
	
	public void setCurrentCardIndex(int currentCardIndex) {
		if (currentCardIndex >= 0 && currentCardIndex < cardContainer.getNumCards()) {
			if (currentCardIndex != this.currentCardIndex) {
				this.currentCardIndex = currentCardIndex;
				setChanged();
				notifyObservers(true);
			}
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public void increaseCurrentCardIndex() {
		currentCardIndex = CalcUtil.modulo(currentCardIndex + 1, cardContainer.getNumCards());
		setChanged();
		notifyObservers(true);
	}
	
	public void decreaseCurrentCardIndex() {
		currentCardIndex = CalcUtil.modulo(currentCardIndex - 1, cardContainer.getNumCards());
		setChanged();
		notifyObservers(true);
	}
	
	private class CardContainerListener implements Observer {
		@Override
		public void update(Observable o, Object arg) {
			if (o instanceof CardContainer) {
				CardContainer cardContainer = (CardContainer) o;
				
				if (cardContainer.isEmpty()) {
					currentCardIndex = -1;
				} else if (currentCardIndex == -1) {
					currentCardIndex = 0;
				} else if (currentCardIndex >= cardContainer.getNumCards()) {
					currentCardIndex = cardContainer.getNumCards() - 1;
				}
				
				setChanged();
				notifyObservers();
			}
		}
	}
}

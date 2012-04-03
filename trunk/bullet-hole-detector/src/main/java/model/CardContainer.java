package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class CardContainer extends Observable {
	List<Card>	cardList;
	
	public CardContainer() {
		cardList = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		cardList.add(card);
		setChanged();
		notifyObservers();
	}
	
	public void removeCard(Card card) {
		cardList.remove(card);
		setChanged();
		notifyObservers();
	}
	
	public boolean isEmpty() {
		return cardList.isEmpty();
	}
	
	public Card getCard(int cardIndex) {
		return cardList.get(cardIndex);
	}
	
	public int getNumCards() {
		return cardList.size();
	}
}

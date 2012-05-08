package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.FileUtil;

public class CardContainer extends Observable {
	private static final Logger	log	= LoggerFactory.getLogger(CardContainer.class);
	
	private List<Card>			cardList;
	
	public CardContainer() {
		cardList = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		if (!cardList.contains(card)) {
			cardList.add(card);
			setChanged();
			notifyObservers();
		} else {
			log.info("Already contains '{}', card not loaded", card.getName());
		}
	}
	
	public void removeCard(int cardIndex) {
		Card card = getCard(cardIndex);
		FileUtil.saveCard(card);
		
		cardList.remove(card);
		setChanged();
		notifyObservers();
	}
	
	public void clear() {
		cardList.clear();
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

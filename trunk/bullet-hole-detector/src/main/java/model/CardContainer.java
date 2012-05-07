package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CardContainer extends Observable {
	private static final Logger	log	= LoggerFactory.getLogger(CardContainer.class);
	
	private List<Card>			cardList;
	
	public CardContainer() {
		cardList = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		if (cardList.contains(card)) {
			log.info("Already contains '{}', card not loaded", card.getName());
		} else {
			cardList.add(card);
			setChanged();
			notifyObservers();
		}
	}
	
	public void removeCard(Card card) {
		saveCard(card);
		
		cardList.remove(card);
		setChanged();
		notifyObservers();
	}
	
	private void saveCard(Card card) {
		try {
			FileOutputStream fileOut = new FileOutputStream(card.getName() + ".crd");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(card);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Card loadCard(File file) {
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Card card = (Card) in.readObject();
			in.close();
			fileIn.close();
			return card;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			log.warn("Card class not found.");
			cnfe.printStackTrace();
			return null;
		}
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

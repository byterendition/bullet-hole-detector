package model;

import java.awt.Color;
import java.io.Serializable;

public class Model implements Serializable {
	private static final long	serialVersionUID	= 6590804952251519999L;
	
	public int					currentCardIndex;
	public CardContainer		cardContainer;
	public Color				bgColor;
	
	public Model() {
		cardContainer = new CardContainer();
	}
	
	public Card getCurrentCard() {
		return cardContainer.getCard(currentCardIndex);
	}
}

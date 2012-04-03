package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import model.Card;
import model.CardContainer;
import controller.ViewUtil;

public class CardPanel extends JPanel {
	private static final long	serialVersionUID	= 1L;
	
	public CardContainer		cardContainer;
	public int					currentCardIndex;
	
	public CardImagePanel		cardImagePanel;
	public CardNavigatorPanel	cardNavigatorPanel;
	
	public CardPanel(CardContainer cardContainer) {
		this.cardContainer = cardContainer;
		currentCardIndex = -1;
		
		setLayout(new BorderLayout());
		setBorder(ViewUtil.getSpacedEtchedBorder(4, 8));
		
		cardImagePanel = new CardImagePanel(this);
		this.add(cardImagePanel, BorderLayout.CENTER);
		
		cardNavigatorPanel = new CardNavigatorPanel(this);
		this.add(cardNavigatorPanel, BorderLayout.SOUTH);
	}
	
	public Card getCurrentCard() {
		if (currentCardIndex >= 0 && currentCardIndex < cardContainer.getNumCards()) {
			return cardContainer.getCard(currentCardIndex);
		}
		return null;
	}
}

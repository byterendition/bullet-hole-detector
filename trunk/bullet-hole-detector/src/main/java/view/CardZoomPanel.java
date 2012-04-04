package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import model.Card;
import model.CardContainer;
import controller.ViewUtil;

public class CardZoomPanel extends JPanel {
	private static final long		serialVersionUID	= 1L;
	
	public CardContainer			cardContainer;
	public int						currentCardIndex;
	
	public CardZoomImagePanel		cardZoomImagePanel;
	public CardZoomNavigatorPanel	cardZoomNavigatorPanel;
	
	public CardZoomPanel(CardContainer cardContainer) {
		this.cardContainer = cardContainer;
		currentCardIndex = -1;
		
		setLayout(new BorderLayout());
		setBorder(ViewUtil.getSpacedEtchedBorder(4, 8));
		
		cardZoomImagePanel = new CardZoomImagePanel(this, 64);
		this.add(cardZoomImagePanel, BorderLayout.CENTER);
		
		cardZoomNavigatorPanel = new CardZoomNavigatorPanel(this);
		this.add(cardZoomNavigatorPanel, BorderLayout.SOUTH);
	}
	
	public Card getCurrentCard() {
		if (currentCardIndex >= 0 && currentCardIndex < cardContainer.getNumCards()) {
			return cardContainer.getCard(currentCardIndex);
		}
		return null;
	}
}

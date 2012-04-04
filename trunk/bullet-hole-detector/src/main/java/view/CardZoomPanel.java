package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import controller.ViewUtil;

public class CardZoomPanel extends JPanel {
	private static final long		serialVersionUID	= 1L;
	
	public int						currentCardIndex;
	
	public CardZoomImagePanel		cardZoomImagePanel;
	public CardZoomNavigatorPanel	cardZoomNavigatorPanel;
	
	public CardPanel				cardPanel;
	
	public CardZoomPanel(CardPanel cardPanel) {
		this.cardPanel = cardPanel;
		
		setLayout(new BorderLayout());
		setBorder(ViewUtil.getSpacedEtchedBorder(4, 8));
		
		cardZoomImagePanel = new CardZoomImagePanel(this, 64);
		cardZoomImagePanel.setPreferredSize(new Dimension(64, 64));
		this.add(cardZoomImagePanel, BorderLayout.CENTER);
		
		cardZoomNavigatorPanel = new CardZoomNavigatorPanel(this);
		this.add(cardZoomNavigatorPanel, BorderLayout.SOUTH);
	}
}

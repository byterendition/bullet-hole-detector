package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.CardZoomNavigatorListener;

public class CardZoomNavigatorPanel extends JPanel {
	private static final long	serialVersionUID	= 1L;
	
	private JLabel				cardIndexLabel;
	private CardZoomPanel		cardZoomPanel;
	
	public CardZoomNavigatorPanel(CardZoomPanel cardZoomPanel) {
		this.cardZoomPanel = cardZoomPanel;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
		
		add(Box.createHorizontalGlue());
		
		JButton previousButton = new JButton("<");
		previousButton.addActionListener(new CardZoomNavigatorListener(cardZoomPanel));
		previousButton.setName("previousButton");
		add(previousButton);
		
		add(Box.createRigidArea(new Dimension(16, 0)));
		
		cardIndexLabel = new JLabel("0 / 0");
		add(cardIndexLabel);
		
		add(Box.createRigidArea(new Dimension(16, 0)));
		
		JButton nextButton = new JButton(">");
		nextButton.addActionListener(new CardZoomNavigatorListener(cardZoomPanel));
		nextButton.setName("nextButton");
		add(nextButton);
		
		add(Box.createRigidArea(new Dimension(8, 0)));
		
		JButton removeButton = new JButton("X");
		removeButton.addActionListener(new CardZoomNavigatorListener(cardZoomPanel));
		removeButton.setName("removeButton");
		add(removeButton);
		
		add(Box.createHorizontalGlue());
	}
	
	public void updateLabel(int currentIndex, int total) {
		if (total > 0) {
			cardIndexLabel.setText((currentIndex + 1) + " / " + total);
		} else {
			cardIndexLabel.setText(0 + " / " + 0);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if (cardZoomPanel != null) {
			updateLabel(cardZoomPanel.currentCardIndex, cardZoomPanel.cardContainer.getNumCards());
		}
		
		super.paintComponent(g);
	}
}

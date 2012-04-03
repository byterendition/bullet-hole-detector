package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.CardNavigatorListener;

public class CardNavigatorPanel extends JPanel {
	private static final long	serialVersionUID	= 1L;
	
	private JLabel				cardIndexLabel;
	private CardPanel			cardPanel;
	
	public CardNavigatorPanel(CardPanel cardPanel) {
		this.cardPanel = cardPanel;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
		
		add(Box.createHorizontalGlue());
		
		JButton previousButton = new JButton("<");
		previousButton.addActionListener(new CardNavigatorListener(cardPanel));
		previousButton.setName("previousButton");
		add(previousButton);
		
		add(Box.createRigidArea(new Dimension(16, 0)));
		
		cardIndexLabel = new JLabel("0 / 0");
		add(cardIndexLabel);
		
		add(Box.createRigidArea(new Dimension(16, 0)));
		
		JButton nextButton = new JButton(">");
		nextButton.addActionListener(new CardNavigatorListener(cardPanel));
		nextButton.setName("nextButton");
		add(nextButton);
		
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
		if (cardPanel != null) {
			updateLabel(cardPanel.currentCardIndex, cardPanel.cardContainer.getNumCards());
		}
		
		super.paintComponent(g);
	}
}

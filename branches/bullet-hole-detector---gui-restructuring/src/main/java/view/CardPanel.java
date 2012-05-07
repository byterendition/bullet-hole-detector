package view;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Model;
import controller.ViewUtil;

@SuppressWarnings("serial")
public class CardPanel extends JPanel {
	private JLabel	cardLabel;
	private Model	model;
	
	public CardPanel(Model model) {
		this.model = model;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(ViewUtil.getSpacedEtchedBorder(4, 8));
		
		CardImagePanel cardImagePanel = new CardImagePanel(model);
		cardImagePanel.setPreferredSize(new Dimension(240, 240));
		add(cardImagePanel);
		
		add(Box.createRigidArea(new Dimension(0, 4)));
		
		cardLabel = new JLabel("no images loaded");
		add(cardLabel);
		cardLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		add(Box.createRigidArea(new Dimension(0, 8)));
		
		CardNavigatorPanel cardNavigatorPanel = new CardNavigatorPanel(model);
		add(cardNavigatorPanel);
	}
}

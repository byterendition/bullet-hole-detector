package view;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.actions.navigator.NextCardAction;
import listeners.actions.navigator.PreviousCardAction;
import listeners.actions.navigator.RemoveCardAction;
import model.Model;

@SuppressWarnings("serial")
public class CardNavigatorPanel extends JPanel {
	public JLabel	cardIndexLabel;
	Model			model;
	
	public CardNavigatorPanel(Model model) {
		this.model = model;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(Box.createHorizontalGlue());
		
		JButton previousButton = new JButton(new PreviousCardAction(model));
		previousButton.setText("<");
		previousButton.setName("previousButton");
		add(previousButton);
		
		add(Box.createRigidArea(new Dimension(16, 0)));
		
		cardIndexLabel = new JLabel("0 / 0");
		add(cardIndexLabel);
		
		add(Box.createRigidArea(new Dimension(16, 0)));
		
		JButton nextButton = new JButton(new NextCardAction(model));
		nextButton.setText(">");
		nextButton.setName("nextButton");
		add(nextButton);
		
		add(Box.createRigidArea(new Dimension(8, 0)));
		
		JButton removeButton = new JButton(new RemoveCardAction(model));
		removeButton.setText("X");
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
}

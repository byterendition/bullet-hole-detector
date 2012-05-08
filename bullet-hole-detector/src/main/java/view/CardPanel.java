package view;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Model;
import controller.ViewUtil;

@SuppressWarnings("serial")
public class CardPanel extends JPanel {
	private JLabel	cardLabel;
	
	public CardPanel(Model model, JFrame parent) {
		model.addObserver(new ModelListenerCardPanel());
		
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
		
		CardNavigatorPanel cardNavigatorPanel = new CardNavigatorPanel(model, parent);
		add(cardNavigatorPanel);
	}
	
	public class ModelListenerCardPanel implements Observer {
		
		@Override
		public void update(Observable o, Object arg) {
			if (o instanceof Model) {
				Model model = (Model) o;
				if (model.getCurrentCardIndex() >= 0) {
					cardLabel.setText(model.getCurrentCard().getName());
				} else {
					cardLabel.setText("no images loaded");
				}
				cardLabel.repaint();
			}
		}
	}
}

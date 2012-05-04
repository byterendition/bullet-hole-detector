package view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardZoomNavigatorPanel extends JPanel {
	private static final long	serialVersionUID	= 1L;
	
	private JLabel				cardIndexLabel;
	private CardZoomPanel		cardZoomPanel;
	
	public CardZoomNavigatorPanel(CardZoomPanel cardZoomPanel) {
		this.cardZoomPanel = cardZoomPanel;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
	}
}

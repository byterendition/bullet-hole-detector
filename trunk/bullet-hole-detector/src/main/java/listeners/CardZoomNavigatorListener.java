package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import view.CardZoomPanel;

public class CardZoomNavigatorListener implements ActionListener {
	private static final Logger	log	= LoggerFactory.getLogger(CardZoomNavigatorListener.class);
	
	private CardZoomPanel		cardZoomPanel;
	
	public CardZoomNavigatorListener(CardZoomPanel cardZoomPanel) {
		this.cardZoomPanel = cardZoomPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton button = (JButton) e.getSource();
		}
	}
}

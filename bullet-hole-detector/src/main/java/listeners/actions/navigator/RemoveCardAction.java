package listeners.actions.navigator;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import model.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class RemoveCardAction extends AbstractAction {
	private static final Logger	log	= LoggerFactory.getLogger(RemoveCardAction.class);
	
	Model						model;
	
	public RemoveCardAction(Model model) {
		super("Remove card");
		putValue(SHORT_DESCRIPTION, "Remove card");
		putValue(MNEMONIC_KEY, KeyEvent.VK_R);
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o instanceof Component) {
			Component parent = (Component) o;
			
			int numCards = model.cardContainer.getNumCards();
			if (numCards > 0) {
				int response = JOptionPane.showOptionDialog(parent, "Are you sure you want to remove this card from the project? Any unsaved changes to it will be lost!", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
						null, new String[] { "Yes", "No" }, "No");
				
				if (response == 0) {
					model.cardContainer.removeCard(model.getCurrentCardIndex());
					log.debug("Remove card");
				}
			}
		}
	}
}

package listeners.actions.navigator;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

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
		log.info("Remove card");
	}
}

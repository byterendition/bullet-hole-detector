package listeners.actions.navigator;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import model.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class PreviousCardAction extends AbstractAction {
	private static final Logger	log	= LoggerFactory.getLogger(PreviousCardAction.class);
	
	Model						model;
	
	public PreviousCardAction(Model model) {
		super("Previous card");
		putValue(SHORT_DESCRIPTION, "Go to previous card");
		putValue(MNEMONIC_KEY, KeyEvent.VK_P);
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("Previous card");
	}
}

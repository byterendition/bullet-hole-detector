package listeners.actions.menu.file;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import model.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class NewAction extends AbstractAction {
	private static final Logger	log	= LoggerFactory.getLogger(NewAction.class);
	
	Model						model;
	
	public NewAction(Model model) {
		super("New");
		putValue(SHORT_DESCRIPTION, "New project");
		putValue(MNEMONIC_KEY, KeyEvent.VK_N);
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("New");
		model.cardContainer.clear();
	}
}

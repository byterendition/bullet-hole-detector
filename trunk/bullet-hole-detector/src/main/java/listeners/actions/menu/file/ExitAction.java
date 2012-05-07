package listeners.actions.menu.file;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import model.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class ExitAction extends AbstractAction {
	private static final Logger	log	= LoggerFactory.getLogger(ExitAction.class);
	
	Model						model;
	
	public ExitAction(Model model) {
		super("Exit");
		putValue(SHORT_DESCRIPTION, "Exit program");
		putValue(MNEMONIC_KEY, KeyEvent.VK_E);
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("Exit");
		System.exit(0);
	}
	
}

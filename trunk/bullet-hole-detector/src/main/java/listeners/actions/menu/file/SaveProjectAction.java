package listeners.actions.menu.file;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import model.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class SaveProjectAction extends AbstractAction {
	private static final Logger	log	= LoggerFactory.getLogger(SaveProjectAction.class);
	
	Model						model;
	
	public SaveProjectAction(Model model) {
		super("Save project");
		putValue(SHORT_DESCRIPTION, "Save project");
		putValue(MNEMONIC_KEY, KeyEvent.VK_S);
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("Save project - not yet implemented");
		// TODO implement this
	}
}

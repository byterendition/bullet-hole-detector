package listeners.actions.menu.file;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import model.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class LoadProjectAction extends AbstractAction {
	private static final Logger	log	= LoggerFactory.getLogger(LoadProjectAction.class);
	
	Model						model;
	
	public LoadProjectAction(Model model) {
		super("Load project");
		putValue(SHORT_DESCRIPTION, "Load project");
		putValue(MNEMONIC_KEY, KeyEvent.VK_L);
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("Load project - not yet implemented");
		// TODO implement this
	}
}

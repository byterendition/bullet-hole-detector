package listeners.actions.menu.file;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import model.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class ExitAction extends AbstractAction {
	private static final Logger	log	= LoggerFactory.getLogger(ExitAction.class);
	
	Model						model;
	JFrame						parent;
	
	public ExitAction(Model model, JFrame parent) {
		super("Exit");
		putValue(SHORT_DESCRIPTION, "Exit program");
		putValue(MNEMONIC_KEY, KeyEvent.VK_E);
		this.model = model;
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.dispatchEvent(new WindowEvent(parent, WindowEvent.WINDOW_CLOSING));
	}
	
}

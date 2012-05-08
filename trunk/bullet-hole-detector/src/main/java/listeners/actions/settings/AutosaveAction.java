package listeners.actions.settings;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;

import model.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class AutosaveAction extends AbstractAction {
	private static final Logger	log	= LoggerFactory.getLogger(AutosaveAction.class);
	
	Model						model;
	
	public AutosaveAction(Model model) {
		super("autosave");
		putValue(SHORT_DESCRIPTION, "autosave");
		putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o instanceof JCheckBox) {
			JCheckBox autosaveCheckBox = (JCheckBox) o;
			log.debug("autosave checkbox changed to {}", autosaveCheckBox.isSelected());
			model.autosave = autosaveCheckBox.isSelected();
		}
	}
}

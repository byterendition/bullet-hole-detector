package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import listeners.actions.settings.AutosaveAction;
import model.Model;
import controller.ViewUtil;

@SuppressWarnings("serial")
public class SettingsPanel extends JPanel {
	public SettingsPanel(Model model, JFrame parent) {
		model.addObserver(new ModelListenerSettingsPanel());
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(ViewUtil.getSpacedEtchedBorder(4, 8));
		
		JCheckBox autosaveCheckBox = new JCheckBox("autosave", false);
		autosaveCheckBox.setAction(new AutosaveAction(model));
		add(autosaveCheckBox);
	}
	
	public class ModelListenerSettingsPanel implements Observer {
		
		@Override
		public void update(Observable o, Object arg) {
			if (o instanceof Model) {
				@SuppressWarnings("unused")
				Model model = (Model) o;
			}
		}
	}
}

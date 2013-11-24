/**
 * 
 */
package fr.nantes.univ.alma.human.ui.dialog;

import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import fr.nantes.univ.alma.human.ui.changelistener.DateChangeListener;

/**
 * @author bmael
 *
 */
public class TimeSelectionDialogPanel {
	
	public static JPanel getPanel(Date ringingdate){		
		JPanel res = new JPanel();
		
		JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "dd MMMM yyyy HH:mm");
		
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(ringingdate); // will only show the current time
		timeSpinner.addChangeListener(new DateChangeListener(ringingdate, timeSpinner));
		
		res.add(timeSpinner);
		
		return res;
	}
}

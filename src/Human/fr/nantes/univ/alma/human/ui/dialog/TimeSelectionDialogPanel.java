/**
 * 
 */
package fr.nantes.univ.alma.human.ui.dialog;

import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 * @author bmael
 *
 */
public class TimeSelectionDialog extends JDialog {

	public TimeSelectionDialog(){
		JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date()); // will only show the current time
	}
}

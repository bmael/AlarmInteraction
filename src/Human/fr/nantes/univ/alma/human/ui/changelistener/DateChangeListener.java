/**
 * 
 */
package fr.nantes.univ.alma.human.ui.changelistener;

import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author bmael
 *
 */
public class DateChangeListener implements ChangeListener {

	private Date date;
	private JSpinner spinner;
	
	public DateChangeListener(Date date, JSpinner spinner){
		this.date = date;
		this.spinner = spinner;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent arg0) {
		this.date.setTime(((Date) spinner.getValue()).getTime());
	}

}

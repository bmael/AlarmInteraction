/**
 * 
 */
package fr.nantes.univ.alma.human.ui.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Date;

import fr.nantes.univ.alma.common.remote.IHuman;
import fr.nantes.univ.alma.human.ui.dialog.TimeSelectionDialog;

/**
 * @author bmael
 *
 */
public class WindOnActionListener implements ActionListener {
	private IHuman human;

	public WindOnActionListener(IHuman human){
		this.human = human;
	} 
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		TimeSelectionDialog dialog = new TimeSelectionDialog();
		dialog.setVisible(true);
		
		//TODO: retrieve the date of alarm ringing
		
		Date oneMinuteAfterNow = new Date();
		oneMinuteAfterNow.setTime(oneMinuteAfterNow.getTime() + 60000);
		try {
			this.human.windOn(oneMinuteAfterNow);
		} catch (RemoteException e) {
			System.err.println("Unable to wind on the alarm: Enjoy tomorrow you can sleep!");
			e.printStackTrace();
		}
		
	}

}

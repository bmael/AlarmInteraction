/**
 * 
 */
package fr.nantes.univ.alma.human.ui.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Date;

import javax.swing.JOptionPane;

import fr.nantes.univ.alma.common.remote.IHuman;
import fr.nantes.univ.alma.human.ui.dialog.TimeSelectionDialogPanel;

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

		Date ringingDate = new Date();

		int res = JOptionPane.showConfirmDialog(null,  
				TimeSelectionDialogPanel.getPanel(ringingDate), 
				"Choose your wake up hour: ", 
				JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.PLAIN_MESSAGE);

		if(res == JOptionPane.OK_OPTION){
			System.out.println("The Alarm Will Ring at: " + ringingDate);
			try {
				this.human.windOn(ringingDate);
				this.human.sleep();
			} catch (RemoteException e) {
				System.err.println("Unable to wind on the alarm: Enjoy tomorrow you can sleep!");
				e.printStackTrace();
			}
		}

	}

}

/**
 * 
 */
package fr.nantes.univ.alma.human.ui.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import fr.nantes.univ.alma.common.remote.IHuman;

/**
 * @author bmael
 *
 */
public class WakeUpActionListener implements ActionListener {

	private IHuman human;

	public WakeUpActionListener(IHuman human){
		this.human = human;
	} 
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.human.wakeUp();
			this.human.windOff(false);
		} catch (RemoteException e1) {
			System.err.println("Unable to wake up the human... He makes a beautifull dream!");
			e1.printStackTrace();
		}
	}

}

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
public class SleepActionListener implements ActionListener {

	private IHuman human;
	
	public SleepActionListener(IHuman human){
		this.human = human;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			this.human.sleep();
		} catch (RemoteException e) {
			System.err.println("Unable to say to the human : 'Go to sleep!'...");
			e.printStackTrace();
		}
	}

}

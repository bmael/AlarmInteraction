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
public class WindOffActionListener implements ActionListener {
	private IHuman human;

	public WindOffActionListener(IHuman human){
		this.human = human;
	} 
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.human.windOff();
		} catch (RemoteException e1) {
			System.err.println("Unable to wind off the alarm... Sentenced to rung");
			e1.printStackTrace();
		}
	}

}

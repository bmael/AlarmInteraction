/**
 * 
 */
package fr.nantes.univ.alma.human.ui.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import fr.nantes.univ.alma.common.remote.IHuman;
import fr.nantes.univ.alma.human.ui.dialog.TimeSelectionDialogPanel;
import fr.nantes.univ.alma.tools.ui.InfiniteProgressPanel;

/**
 * @author bmael
 *
 */
public class WindOnActionListener implements ActionListener {
	private IHuman human;
	private InfiniteProgressPanel glassPane;

	public WindOnActionListener(IHuman human, InfiniteProgressPanel glassPane){
		this.human = human;
		this.glassPane = glassPane;
	} 

	public void waitForTransmission(Date ringingDate){
		Random random = new Random();
		int timeToWait = random.nextInt(10)+1 * 1000; // Delay is on [1;10] second(s)

		System.out.println("[INFO] Have to wait: " + timeToWait + "ms before communication...");

		try {
			Thread.sleep(timeToWait);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.glassPane.stop();
		
		try {
			this.human.windOn(ringingDate);
			this.human.sleep();
		} catch (RemoteException e) {
			System.err.println("[ERROR] Unable to wind on the alarm: Enjoy tomorrow you can sleep!");
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		final Date ringingDate = new Date();

		int res = JOptionPane.showConfirmDialog(null,  
				TimeSelectionDialogPanel.getPanel(ringingDate), 
				"Choose your wake up hour: ", 
				JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.PLAIN_MESSAGE);

		if(res == JOptionPane.OK_OPTION){
			System.out.println("[INFO] The alarm will ring at: " + ringingDate);

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					glassPane.start();
					Thread performer = new Thread(new Runnable() {
						public void run() {
							waitForTransmission(ringingDate);
						}
					}, "Performer");
					performer.start();
				}
			});

		}
	}

}

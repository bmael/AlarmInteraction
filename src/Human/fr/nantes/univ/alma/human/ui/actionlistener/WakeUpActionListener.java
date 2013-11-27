/**
 * 
 */
package fr.nantes.univ.alma.human.ui.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Random;

import javax.swing.SwingUtilities;

import fr.nantes.univ.alma.common.remote.IHuman;
import fr.nantes.univ.alma.tools.ui.InfiniteProgressPanel;

/**
 * @author bmael
 *
 */
public class WakeUpActionListener implements ActionListener {

	private IHuman human;
	private InfiniteProgressPanel glassPane;

	public WakeUpActionListener(IHuman human, InfiniteProgressPanel glassPane){
		this.human = human;
		this.glassPane = glassPane;
	} 

	public void waitForTransmission(){
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
			this.human.wakeUp();
			this.human.windOff(false);	
		} catch (RemoteException e1) {
			System.err.println("Unable to wake up the human... He makes a beautifull dream!");
			e1.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				glassPane.start();
				Thread performer = new Thread(new Runnable() {
					public void run() {
						waitForTransmission();
					}
				}, "Performer");
				performer.start();
			}
		});
	}

}

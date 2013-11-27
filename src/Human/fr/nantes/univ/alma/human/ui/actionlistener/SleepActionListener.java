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
public class SleepActionListener implements ActionListener {

	private IHuman human;
	private InfiniteProgressPanel glassPane;
	

	public SleepActionListener(IHuman human, InfiniteProgressPanel glassPane){
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
			this.human.sleep();
			System.out.println("[INFO] Human is sleeping");
		} catch (RemoteException e) {
			System.err.println("Unable to say to the human : 'Go to sleep!'...");
			e.printStackTrace();
		}	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
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

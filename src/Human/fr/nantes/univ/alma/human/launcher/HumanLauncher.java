/**
 * 
 */
package fr.nantes.univ.alma.human.launcher;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import fr.nantes.univ.alma.common.remote.IAlarm;
import fr.nantes.univ.alma.human.impl.Human;
import fr.nantes.univ.alma.human.ui.MainWindow;

/**
 * @author bmael
 *
 */
public class HumanLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Retrieve the alarm				
		String alarmAddress;
		try {
			alarmAddress = "//" + InetAddress.getLocalHost().getHostAddress() + 
					"/ploppy";

			//server lookup
			try{				
				System.out.println("* Looking up for an alarm at: " + alarmAddress + "...");
				try {
					IAlarm alarm = (IAlarm) Naming.lookup(alarmAddress);

					System.out.println("* Instanciating the human with the alarm...");
					Human human = new Human(alarm);
					
					System.out.println("* Initializing the human main window...");
					MainWindow frame = new MainWindow(human);
					
					human.addObserver(frame);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (NotBoundException e){
					e.printStackTrace();
				}

			} catch (RemoteException e) {
				e.printStackTrace();
				System.err.println("Unable to contact the alarm...");
				System.exit(0);
			}

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
			System.err.println("Unable to construct the default server address...");
			System.exit(0);
		}
	}

}

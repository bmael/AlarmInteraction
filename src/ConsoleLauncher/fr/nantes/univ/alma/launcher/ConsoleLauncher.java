/**
 * 
 */
package fr.nantes.univ.alma.launcher;

import java.net.InetAddress;

import fr.nantes.univ.alma.common.remote.IAlarm;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import fr.nantes.univ.alma.common.remote.IAlarm;
import fr.nantes.univ.alma.common.remote.IHuman;
import fr.nantes.univ.alma.human.impl.Human;

/**
 * @author Maël
 *
 */
public class ConsoleLauncher {

	private static void printWelcomeMenu(String alarmAddress){
		System.out.println(" --------------------------------------------------------");
		System.out.println("|                                                        |");
		System.out.println("|         Welcome to AlarmInteraction Launcher           |");
		System.out.println("|                                                        |");
		System.out.println("|                    " + alarmAddress + "               |");
		System.out.println(" --------------------------------------------------------");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Retrieve the alarm				
		String alarmAddress;
		try {
			alarmAddress = "//" + InetAddress.getLocalHost().getHostAddress() + 
					"/ploppy";

			printWelcomeMenu(alarmAddress);

			//server lookup
			try{				
				System.out.println("* Looking up for an alarm at: " + alarmAddress + "...");
				try {
					IAlarm alarm = (IAlarm) Naming.lookup(alarmAddress);

					System.out.println("* Instanciating the human with the alarm...");
					IHuman human = new Human(alarm);

					// TODO: Display all available choices for human in a menu.

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

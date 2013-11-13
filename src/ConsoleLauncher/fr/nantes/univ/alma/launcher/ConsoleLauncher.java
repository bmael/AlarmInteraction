/**
 * 
 */
package fr.nantes.univ.alma.launcher;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import fr.nantes.univ.alma.common.remote.IServer;

/**
 * @author Maël
 *
 */
public class ConsoleLauncher {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Init the human
		// IHuman human = new Human();
		
		// Retrieve the server				
			String serverAddress;
			try {
				serverAddress = "//" + InetAddress.getLocalHost().getHostAddress() + 
						"/NashorServer";
				
				//server lookup
				try{				
					System.out.println("Looking up for Server at: " + serverAddress);
					try {
						IServer server = (IServer) Naming.lookup(serverAddress);
						
						System.out.println("Setting the human to the server...");
//						server.setHuman();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (NotBoundException e){
						e.printStackTrace();
					}

				} catch (RemoteException e) {
					e.printStackTrace();
					System.err.println("Unable to contact server...");
					System.exit(0);
				}
				
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
				System.err.println("Unable to construct the default server address...");
				System.exit(0);
			}
	}
}

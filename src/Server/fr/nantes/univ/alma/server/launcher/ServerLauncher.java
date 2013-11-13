/**
 * 
 */
package fr.nantes.univ.alma.server.launcher;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import fr.nantes.univ.alma.server.impl.Server;
/**
 * This class provide the static void main method to launch the server.
 * @author bmael
 *
 */
public class ServerLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			
			try {
				System.out.println("Trying to registry port 1099");
				LocateRegistry.createRegistry(1099);
			} catch (Exception e) {
				System.out.println("-- Port already in use....");
			}

			Server nashor = new Server();
			
			String url = "//" + InetAddress.getLocalHost().getHostAddress() + "/ploppy";
			System.out.println("Storing the server with url: " + url);
			
			Naming.rebind(url, nashor);
			
			System.out.println("Server is online");
			
		}catch(Exception e){
			System.err.println("Unable to launch server...");
			e.printStackTrace();
			System.exit(0);
		}	
	}

}

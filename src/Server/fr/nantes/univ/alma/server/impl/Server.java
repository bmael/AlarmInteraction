/**
 * 
 */
package fr.nantes.univ.alma.server.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import fr.nantes.univ.alma.common.remote.IAlarm;
import fr.nantes.univ.alma.common.remote.IHuman;
import fr.nantes.univ.alma.common.remote.IServer;

/**
 * @author Maël
 *
 */
public class Server extends UnicastRemoteObject implements IServer, IAlarm {

	private IHuman human;
	
	private boolean windOn;
	
	public Server() throws RemoteException {
		super();
		this.windOn = false;
	}

	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -3738123347606957790L;

	@Override
	public void windOn() throws RemoteException {
		this.windOn = true;
	}

	@Override
	public void windOffAfterWakeUp() throws RemoteException {
		this.windOn = false;
	}

	@Override
	public void windOffAfterRing() throws RemoteException {
		this.windOn = false;
	}

	@Override
	public void ringing() throws RemoteException {
		// TODO Auto-generated method stub
		// Call the method ring from Human.
	}

	@Override
	public boolean isWindOn() {
		return this.windOn;
	}
	
	/**
	 * Set the human who use this alarm.
	 * @param human the human who use this alarm.
	 */
	public void setHuman(IHuman human){
		this.human = human;
	}

}

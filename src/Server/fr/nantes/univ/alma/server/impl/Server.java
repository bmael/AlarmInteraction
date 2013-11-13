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
public class Server extends UnicastRemoteObject implements IServer {

	public Server() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -3738123347606957790L;

	@Override
	public void windOn(IAlarm alarm) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void windOffAfterWakeUp(IAlarm alarm) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void windOffAfterRing(IAlarm alarm) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ringing(IHuman human) throws RemoteException {
		// TODO Auto-generated method stub

	}

}

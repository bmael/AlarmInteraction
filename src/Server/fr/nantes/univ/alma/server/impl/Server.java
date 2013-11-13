/**
 * 
 */
package fr.nantes.univ.alma.server.impl;

import java.rmi.RemoteException;

import fr.nantes.univ.alma.common.remote.IAlarm;
import fr.nantes.univ.alma.common.remote.IHuman;
import fr.nantes.univ.alma.common.remote.IServer;

/**
 * @author Maël
 *
 */
public class Server implements IServer {

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

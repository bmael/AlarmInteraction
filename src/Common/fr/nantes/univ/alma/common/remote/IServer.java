package fr.nantes.univ.alma.common.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is the server interface.
 * @author bmael
 *
 */
public interface IServer extends Remote {
	
	/**
	 * Wind on the given alarm in parameter.
	 * @param alarm the alarm to wind on.
	 * @throws RemoteException
	 */
	public void windOn(IAlarm alarm) throws RemoteException;
	
	/**
	 * Wind off the given alarm in parameter after a human natural wake up.
	 * @param alarm the alarm to wind off.
	 * @throws RemoteException
	 */
	public void windOffAfterWakeUp(IAlarm alarm) throws RemoteException;
	
	/**
	 * Wind off the given alarm in parameter after an alarm ring.
	 * @param alarm the alarm to wind off.
	 * @throws RemoteException
	 */
	public void windOffAfterRing(IAlarm alarm) throws RemoteException;
	
	/**
	 * Advise the given human in parameter that the alarm is ringing.
	 * @param human the human to advise.
	 * @throws RemoteException
	 */
	public void ringing(IHuman human) throws RemoteException;
}

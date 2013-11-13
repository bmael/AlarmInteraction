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
	 * Wind on the alarm.
	 * @param alarm the alarm to wind on.
	 * @throws RemoteException
	 */
	public void windOn() throws RemoteException;
	
	/**
	 * Wind off the alarm after a human natural wake up.
	 * @param alarm the alarm to wind off.
	 * @throws RemoteException
	 */
	public void windOffAfterWakeUp() throws RemoteException;
	
	/**
	 * Wind off the alarm after an alarm ring.
	 * @param alarm the alarm to wind off.
	 * @throws RemoteException
	 */
	public void windOffAfterRing() throws RemoteException;
	
	/**
	 * Advise the human that the alarm is ringing.
	 * @param human the human to advise.
	 * @throws RemoteException
	 */
	public void ringing() throws RemoteException;
	
}

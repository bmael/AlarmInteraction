package fr.nantes.univ.alma.common.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * This is the alarm interface.
 * @author bmael
 *
 */
public interface IAlarm extends Remote{
	
	/**
	 * Return a boolean to indicate if this alarm is wind on.
	 * @return true if this alarm is wind on, false otherwise.
	 */
	public boolean isWindOn() throws RemoteException;
	
	/**
	 * Wind on the alarm.
	 * @param d, the date when the alarm have to ring.
	 * @throws RemoteException
	 */
	public void windOn(Date d) throws RemoteException;
	
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
	 * Return a boolean to indicate if this alarm is ringing.
	 * @return true if this alarm is ringing, false otherwise.
	 */
	public boolean isRinging() throws RemoteException;
	
	/**
	 * Make this alarm very noisy and boring.
	 * @throws RemoteException
	 */
	public void ringing() throws RemoteException;
	
	/**
	 * Stop the the ring from this alarm.
	 * @throws RemoteException
	 */
	public void stopRinging() throws RemoteException;
	
	/**
	 * Returns the ringing date if it set, null otherwise.
	 * @return the ringing date if it set, null otherwise.
	 * @throws RemoteException
	 */
	public Date getRingingDate() throws RemoteException;
	
}

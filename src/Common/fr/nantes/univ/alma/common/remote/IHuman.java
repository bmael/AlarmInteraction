package fr.nantes.univ.alma.common.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
/**
 * The human interface presents all actions that a human can do.
 * @author bmael
 *
 */
public interface IHuman extends Remote {
	// 2 states : - Sleeping/Wakeup with a boolean 
	// can windOn(Date d) the alarm
	// can windOff() the alarm
	// can wakeUp()
	// can sleep()
	// can see what time is it : getTime()

	/**
	 * Ask to wind on the alarm. 
	 * @param d, the alarm will ring at date d.
	 * @throws RemoteException
	 */
	public void windOn(Date d) throws RemoteException;

	/**
	 * Ask to wind off the alarm.
	 * @param withAlarm is a boolean that indicates if the human is wake up with the ring of the alarm clock or just wake up by himself. 
	 * @throws RemoteException
	 */
	public void windOff(boolean withAlarm) throws RemoteException;	

	/**
	 * Wake up the human.
	 * @throws RemoteException
	 */
	public void wakeUp() throws RemoteException;

	/**
	 * Send to sleep the human.
	 * @throws RemoteException
	 */
	public void sleep() throws RemoteException;

	/**
	 * Set the human on state zombie
	 * @throws RemoteException
	 */
	public void zombie() throws RemoteException;

	/**
	 * Display the hour from the alarm.
	 * @throws RemoteException
	 */
	public void getTime() throws RemoteException;

	/**
	 * Return a boolean indicate if the human isSleeping.
	 * @return true if the human is sleeping, false otherwise.
	 * @throws RemoteException
	 */
	public boolean isSleeping() throws RemoteException;

	/**
	 * Return a boolean to indicate if human wind on or wind off the alarm.
	 * @return true if human wind on the alarm, false otherwise.
	 * @throws RemoteException
	 */
	public boolean isAlarmWindOn() throws RemoteException;

	/**
	 * Return a boolean to indicate if the human is on a zombie state.
	 * @return true if the human is on a zombie state, false otherwise.
	 * @throws RemoteException
	 */
	public boolean isZombie() throws RemoteException;
}

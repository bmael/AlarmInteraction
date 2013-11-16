/**
 * 
 */
package fr.nantes.univ.alma.alarm.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import fr.nantes.univ.alma.common.remote.IAlarm;

/**
 * @author Maël
 *
 */
public class Alarm extends UnicastRemoteObject implements IAlarm {
	
	private boolean windOn;
	private Date date;
	
	public Alarm() throws RemoteException {
		super();
		this.windOn = false;
	}

	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -3738123347606957790L;

	@Override
	public void windOn(Date d) throws RemoteException {
		this.windOn = true;
		this.date = d;
	}
	
	private void windOff(){
		this.windOn = false;
		this.date = null;
	}
	
	@Override
	public void windOffAfterWakeUp() throws RemoteException {
		this.windOff();
	}

	@Override
	public void windOffAfterRing() throws RemoteException {
		this.windOff();
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
}

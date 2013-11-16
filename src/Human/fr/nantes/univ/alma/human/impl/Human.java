/**
 * 
 */
package fr.nantes.univ.alma.human.impl;

import java.rmi.RemoteException;
import java.util.Date;

import fr.nantes.univ.alma.common.remote.IAlarm;
import fr.nantes.univ.alma.common.remote.IHuman;

/**
 * @author bmael
 *
 */
public class Human implements IHuman {

	private boolean sleeping = false;
	private IAlarm alarm;
	
	public Human(IAlarm alarm){
		this.alarm = alarm;
	}
	
	/* (non-Javadoc)
	 * @see fr.nantes.univ.alma.common.remote.IHuman#windOn(java.util.Date)
	 */
	@Override
	public void windOn(Date d) throws RemoteException {
		this.alarm.windOn(d);
	}

	/* (non-Javadoc)
	 * @see fr.nantes.univ.alma.common.remote.IHuman#windOff()
	 */
	@Override
	public void windOff() throws RemoteException {
		// TODO: We have to do a test if the human is waking up cause a ring to call the right alarm method.
				
	}

	/* (non-Javadoc)
	 * @see fr.nantes.univ.alma.common.remote.IHuman#wakeUp()
	 */
	@Override
	public void wakeUp() throws RemoteException {
		this.sleeping = false;
	}

	/* (non-Javadoc)
	 * @see fr.nantes.univ.alma.common.remote.IHuman#sleep()
	 */
	@Override
	public void sleep() throws RemoteException {
		this.sleeping = true;
	}

	/* (non-Javadoc)
	 * @see fr.nantes.univ.alma.common.remote.IHuman#getTime()
	 */
	@Override
	public void getTime() throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see fr.nantes.univ.alma.common.remote.IHuman#isSleeping()
	 */
	@Override
	public boolean isSleeping() {
		return this.sleeping;
	}

}

/**
 * 
 */
package fr.nantes.univ.alma.human.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import fr.nantes.univ.alma.common.remote.IAlarm;
import fr.nantes.univ.alma.common.remote.IHuman;
import fr.nantes.univ.alma.human.observer.Observable;
import fr.nantes.univ.alma.human.observer.Observer;

/**
 * @author bmael
 *
 */
public class Human implements IHuman, Observable {

	private boolean sleeping = false;
	private boolean windOn;
	
	private IAlarm alarm;
	private ArrayList<Observer> observers = new ArrayList<>();

	public Human(IAlarm alarm) throws RemoteException{
		this.alarm = alarm;
		this.windOn = this.alarm.isWindOn();
	}

	/* (non-Javadoc)
	 * @see fr.nantes.univ.alma.common.remote.IHuman#windOn(java.util.Date)
	 */
	@Override
	public void windOn(Date d) throws RemoteException {
		this.windOn = true;
		this.updateWindOnObserver();
		
		this.alarm.windOn(d);
	}

	/* (non-Javadoc)
	 * @see fr.nantes.univ.alma.common.remote.IHuman#windOff()
	 */
	@Override
	public void windOff() throws RemoteException {
		// TODO: We have to do a test if the human is waking up cause a ring to call the right alarm method.
		this.windOn = false;
		this.updateWindOnObserver();
		
		this.alarm.windOffAfterWakeUp();
	}

	/* (non-Javadoc)
	 * @see fr.nantes.univ.alma.common.remote.IHuman#wakeUp()
	 */
	@Override
	public void wakeUp() throws RemoteException {
		this.sleeping = false;
		this.updateSleepObserver();
	}

	/* (non-Javadoc)
	 * @see fr.nantes.univ.alma.common.remote.IHuman#sleep()
	 */
	@Override
	public void sleep() throws RemoteException {
		this.sleeping = true;
		this.updateSleepObserver();
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
	

	@Override
	public boolean isAlarmWindOn() {
		return this.windOn;
	}

	@Override
	public void addObserver(Observer obs) {
		this.observers.add(obs);
	}

	@Override
	public void updateSleepObserver() {
		for(Observer obs : this.observers){
			obs.updateSleep(this.sleeping);
		}
	}

	@Override
	public void delObserver() {
		this.observers = new ArrayList<>();
	}

	@Override
	public void updateWindOnObserver() {
		for(Observer obs : this.observers){
			obs.updateWindOn(this.windOn);
		}
	}
}

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
	private boolean zombie = false;
	private boolean windOn;

	private IAlarm alarm;
	private Listen4RingThread ringListener;
	private ArrayList<Observer> observers = new ArrayList<>();

	public Human(IAlarm alarm) throws RemoteException{
		this.alarm = alarm;
		this.windOn = this.alarm.isWindOn();
	}

	@Override
	public void windOn(Date d) throws RemoteException {
		this.windOn = true;
		this.alarm.windOn(d);

		this.updateWindOnObserver();
	}

	@Override
	public void windOff(boolean withAlarm) throws RemoteException {
		this.windOn = false;
		this.updateWindOnObserver();

		if(withAlarm){
			this.alarm.windOffAfterRing();
		}
		else{
			this.alarm.windOffAfterWakeUp();
		}

		this.zombie = false;
		this.updateZombieObserver();
	}

	@Override
	public void wakeUp() throws RemoteException {
		this.sleeping = false;
		this.updateSleepObserver();

		if(this.ringListener != null){
			this.ringListener.stopListen();
		}

		this.zombie = false;
		this.updateZombieObserver();
	}

	@Override
	public void sleep() throws RemoteException {
		this.sleeping = true;
		this.updateSleepObserver();

		this.ringListener = new Listen4RingThread(this.alarm, this);
		this.ringListener.start();
	}

	@Override
	public void zombie() throws RemoteException {
		this.zombie = true;
		this.updateZombieObserver();
	}

	@Override
	public void getTime() throws RemoteException {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isSleeping() {
		return this.sleeping;
	}


	@Override
	public boolean isAlarmWindOn() {
		return this.windOn;
	}

	@Override
	public boolean isZombie() throws RemoteException {
		return this.zombie;
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
	public void updateWindOnObserver() {
		for(Observer obs : this.observers){
			obs.updateWindOn(this.windOn);
		}
	}

	@Override
	public void updateZombieObserver() {
		for(Observer obs : this.observers){
			obs.updateZombie(this.zombie);
		}
	}

	@Override
	public void delObserver() {
		this.observers = new ArrayList<>();
	}
}

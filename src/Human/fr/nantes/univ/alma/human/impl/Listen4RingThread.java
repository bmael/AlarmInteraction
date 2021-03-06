/**
 * 
 */
package fr.nantes.univ.alma.human.impl;

import java.rmi.RemoteException;

import fr.nantes.univ.alma.common.remote.IAlarm;
import fr.nantes.univ.alma.common.remote.IHuman;

/**
 * @author bmael
 *
 */
public class Listen4RingThread extends Thread {

	private boolean stop = false;
	private IAlarm alarm;
	private IHuman human;

	/**
	 * Initialize a new instance of a thread to listen for the alarm ringing.
	 * @param alarm
	 */
	public Listen4RingThread(IAlarm alarm, IHuman human) {
		super();
		this.alarm = alarm;
		this.human = human;
	}

	public void run(){
		System.out.println("[INFO] Listen4Ring thread start now.");
		while(!stop){
			try {
				if(this.alarm.isRinging()){
					//this.human.waitForTransmission();
					this.human.zombie();
					System.out.println("[INFO] Stopping Listen4Ring thread");
					this.stopListen();
				}
			} catch (RemoteException e) {
				System.err.println("Unable to listen the alarm... May be it is ringing, may be not...");
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Unable to sleep this Listen4RingThread...");
				e.printStackTrace();
			}
		}
		System.out.println("[INFO] Listen4Ring thread is stopped.");
	}

	public void stopListen(){
		this.stop = true;
	}

}

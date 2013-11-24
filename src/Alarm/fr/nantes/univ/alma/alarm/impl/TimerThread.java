/**
 * 
 */
package fr.nantes.univ.alma.alarm.impl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.nantes.univ.alma.common.remote.IAlarm;

/**
 * @author bmael
 *
 */
public class TimerThread extends Thread {
	
	private IAlarm alarm;
	private boolean stop;
	
	public TimerThread(IAlarm alarm){
		this.alarm = alarm;
		this.stop = false;
	}
	
	public void run(){
		while(!stop){
			try {
				Date current = new Date();
				SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy HH:mm");
				if(format.format(this.alarm.getRingingDate()).equals(format.format(current))){
					System.out.println("DRINNNNNNNNNNNNNNGGGGGGGGGGGGGGGGGG!!!!!!!!!!!!!!");
					this.alarm.ringing();
				}
			} catch (RemoteException e) {
				System.err.println("Unable to get the ringing date... Enjoy your alarm never ring!");
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Unable to sleep this thread...");
				e.printStackTrace();
			}
		}
	}
	
	public void stopTimer(){
		this.stop = true;
	}
	
}

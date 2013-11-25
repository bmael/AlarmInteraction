/**
 * 
 */
package fr.nantes.univ.alma.alarm.impl;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import fr.nantes.univ.alma.alarm.observer.Observable;
import fr.nantes.univ.alma.alarm.observer.Observer;
import fr.nantes.univ.alma.common.remote.IAlarm;

/**
 * @author Maël
 *
 */
public class Alarm extends UnicastRemoteObject implements IAlarm, Observable {

	private boolean windOn;
	private boolean ringing;
	private Date date;

	private TimerThread timer;

	private ArrayList<Observer> observers = new ArrayList<>();

	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -3738123347606957790L;

	public Alarm() throws RemoteException {
		super();
		this.windOn = false;
		this.ringing = false;
		this.date = null;
	}
	
	private void windOff(){		
		this.windOn = false;
		this.date = null;

		this.updateWindOnObserver();
	}
	
	private void reLaunch(){
		this.date = new Date();
		this.date.setTime(this.date.getTime() + 60000); //ring 1 minute later
		
		timer = new TimerThread(this);
		timer.start();
		
		this.updateWindOnObserver();
		
	}

	@Override
	public void windOn(Date d) throws RemoteException {
		this.windOn = true;
		this.date = d;

		timer = new TimerThread(this);
		timer.start();
		
		this.updateWindOnObserver();;
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
		this.timer.stopTimer();
		this.ringing = true;
		
		this.updateRingingObserver();	
		
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/bin/UIRessources/ring.wav"));
			clip.open(inputStream);
	        clip.start();
	        
	        while(clip.getMicrosecondLength()!= clip.getMicrosecondPosition()){}
	        this.stopRinging();
	        
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void stopRinging() throws RemoteException {
		this.ringing = false;
		this.updateRingingObserver();
		
		this.reLaunch();
	}

	@Override
	public boolean isWindOn() {
		return this.windOn;
	}

	@Override
	public boolean isRinging() throws RemoteException {
		return this.ringing;
	}

	@Override
	public Date getRingingDate() throws RemoteException {
		return this.date;
	}

	@Override
	public void addObserver(Observer obs) {
		this.observers.add(obs);
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

	@Override
	public void updateRingingObserver() {
		for(Observer obs : this.observers){
			obs.updateRinging(this.ringing);
		}
	}
}

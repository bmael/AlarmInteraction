/**
 * 
 */
package fr.nantes.univ.alma.alarm.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import fr.nantes.univ.alma.alarm.observer.Observer;
import fr.nantes.univ.alma.alarm.ui.digitalClock.DigitalClock;
import fr.nantes.univ.alma.common.remote.IAlarm;

/**
 * @author bmael
 *
 */
public class MainWindow extends JFrame implements Observer {

	private static final long serialVersionUID = 7283810840315180224L;
	private IAlarm alarmClock;
	private DigitalClock digitalClock;
	
	private JPanel infoDisplayer;
	private JLabel alarmLabel;
	private JLabel ringingLabel;
	
	public MainWindow(IAlarm alarm){
		
		this.alarmClock = alarm;
		this.digitalClock = new DigitalClock();
		this.infoDisplayer = new JPanel();
		
		
		this.setMinimumSize(new Dimension(225, 100));
		this.setResizable(false);
		
		this.infoDisplayer.setLayout(new GridLayout(0,5));
		
		this.alarmLabel = new JLabel();
		alarmLabel.setSize(25,25);
		
		this.ringingLabel = new JLabel();
		ringingLabel.setSize(25,25);
		
		try {
			this.updateWindOn(this.alarmClock.isWindOn());
		} catch (RemoteException e) {
			System.err.println("Unable to know if the alarm is wind on...");
			e.printStackTrace();
		}
		try {
			this.updateRinging(this.alarmClock.isRinging());
		} catch (RemoteException e) {
			System.err.println("Unable to know if the alarm is ringing...");
			e.printStackTrace();
		}

		this.infoDisplayer.add(alarmLabel);
		this.infoDisplayer.add(ringingLabel);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		this.add(this.digitalClock, BorderLayout.CENTER);
		this.add(this.infoDisplayer, BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void updateWindOn(boolean isWindOn) {
		if(isWindOn){
			try {
				BufferedImage imgWindOn = ImageIO.read(new File(System.getProperty("user.dir") + "/bin/UIRessources/alarm-clock.png"));
				Image dimgWindOn = imgWindOn.getScaledInstance(alarmLabel.getWidth(), alarmLabel.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon alarmIcon = new ImageIcon(dimgWindOn);
		        
		        this.alarmLabel.setIcon(alarmIcon); 
			} catch (IOException e) {
				System.err.println("Couldn't find icons ressources...");
				e.printStackTrace();
			}
		}
		else {
			ImageIcon alarmIcon = new ImageIcon();
			this.alarmLabel.setIcon(alarmIcon);
		}
	}

	@Override
	public void updateRinging(boolean isRinging) {
		if(isRinging){
			try {
				BufferedImage imgRinging = ImageIO.read(new File(System.getProperty("user.dir") + "/bin/UIRessources/alarm-ringing.png"));
		        Image dimgRinging = imgRinging.getScaledInstance(ringingLabel.getWidth(), ringingLabel.getHeight(), Image.SCALE_SMOOTH);
		        ImageIcon ringingIcon = new ImageIcon(dimgRinging);
		        
		        this.ringingLabel.setIcon(ringingIcon); 
			} catch (IOException e) {
				System.err.println("Couldn't find icons ressources...");
				e.printStackTrace();
			}
		}
		else {
			ImageIcon ringingIcon = new ImageIcon();
			this.alarmLabel.setIcon(ringingIcon);
		}
	}
}

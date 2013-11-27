/**
 * 
 */
package fr.nantes.univ.alma.human.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import fr.nantes.univ.alma.common.remote.IHuman;
import fr.nantes.univ.alma.human.observer.Observer;
import fr.nantes.univ.alma.human.ui.actionlistener.SleepActionListener;
import fr.nantes.univ.alma.human.ui.actionlistener.WakeUpActionListener;
import fr.nantes.univ.alma.human.ui.actionlistener.WindOffActionListener;
import fr.nantes.univ.alma.human.ui.actionlistener.WindOnActionListener;
import fr.nantes.univ.alma.tools.ui.InfiniteProgressPanel;
import fr.nantes.univ.alma.tools.ui.SpringUtilities;

/**
 * @author bmael
 *
 */
public class MainWindow extends JFrame implements Observer {

	private static final long serialVersionUID = -644407311124230791L;

	private IHuman human;

	private JTabbedPane tabbedPane;

	private JPanel statePanel;
	private JLabel isSleepingLabel;
	private JLabel isWindOnLabel;

	private JPanel actionPanel;
	private JButton windOnButton;
	private JButton windOffButton;
	private JButton wakeUpButton;
	private JButton sleepButton;
	private JButton getTimeButton;
	
	private InfiniteProgressPanel glassPane;

	public MainWindow(IHuman human){

		this.human = human;
		
		this.glassPane = new InfiniteProgressPanel();
		this.setGlassPane(this.glassPane);
		
		this.tabbedPane = new JTabbedPane();

		/* State Tab */
		this.statePanel = new JPanel();

		SpringLayout layout = new SpringLayout();
		this.statePanel.setLayout(layout);

		JLabel humanStateLabel = new JLabel("Human State: ");
		this.statePanel.add(humanStateLabel);

		this.isSleepingLabel = new JLabel();
		humanStateLabel.setLabelFor(this.isSleepingLabel);
		this.statePanel.add(this.isSleepingLabel);

		JLabel alarmStateLabel = new JLabel("Alarm State: ");
		this.statePanel.add(alarmStateLabel);

		this.isWindOnLabel = new JLabel();
		alarmStateLabel.setLabelFor(this.isWindOnLabel);
		this.statePanel.add(this.isWindOnLabel);

		SpringUtilities.makeCompactGrid(this.statePanel, 2, 2, 6, 6, 6, 6); 

		this.tabbedPane.addTab("State", null, this.statePanel, "Display the state details of the human.");

		/* Action Tab */
		this.actionPanel = new JPanel();
		this.actionPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		this.windOnButton = new JButton("Wind On");
		this.windOnButton.addActionListener(new WindOnActionListener(this.human, this.glassPane));
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		this.actionPanel.add(windOnButton, gridBagConstraints);

		this.windOffButton = new JButton("Wind Off");
		this.windOffButton.addActionListener(new WindOffActionListener(this.human, this.glassPane));
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		this.actionPanel.add(windOffButton, gridBagConstraints);

		this.wakeUpButton = new JButton("Wake Up");
		this.wakeUpButton.addActionListener(new WakeUpActionListener(this.human, this.glassPane));
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		this.actionPanel.add(wakeUpButton, gridBagConstraints);

		this.sleepButton = new JButton("   Sleep   ");
		this.sleepButton.addActionListener(new SleepActionListener(this.human, this.glassPane));
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		this.actionPanel.add(this.sleepButton, gridBagConstraints);

		this.getTimeButton = new JButton("Get Time");
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		this.actionPanel.add(getTimeButton, gridBagConstraints);

		this.tabbedPane.addTab("Actions", null, this.actionPanel, "Display all available actions for the human.");

		/* Synchronize this frame with the current human state */
		try {
			this.updateSleep(this.human.isSleeping());
			this.updateWindOn(this.human.isAlarmWindOn());
			this.updateZombie(this.human.isZombie());
		} catch (RemoteException e) {
			System.err.println("Unable to synchronize this human with the alarm clock...");
			e.printStackTrace();
		}
		
		this.add(this.tabbedPane, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		Dimension us = this.getSize();
		Dimension them =Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2;
		int newY = (them.height- us.height)/ 2;
		
		this.setLocation(newX + 123, newY - 30);
		
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void updateSleep(boolean isSleeping) {
		if (isSleeping){
			this.isSleepingLabel.setText("Sleep");
			this.sleepButton.setEnabled(false);
			this.wakeUpButton.setEnabled(true);
			this.windOnButton.setEnabled(false);
			this.windOffButton.setEnabled(false);
			this.getTimeButton.setEnabled(false);
		} 
		else {
			this.isSleepingLabel.setText("Wake Up");
			this.windOnButton.setEnabled(true);
			this.windOffButton.setEnabled(false);
			this.wakeUpButton.setEnabled(false);
		}


	}

	@Override
	public void updateWindOn(boolean isWindOn) {
		if(isWindOn){
			this.isWindOnLabel.setText("Wind On");
			this.windOnButton.setEnabled(false);			
		}
		else {
			this.isWindOnLabel.setText("Wind Off");
			this.windOnButton.setEnabled(true);
			this.windOffButton.setEnabled(false);
			this.sleepButton.setEnabled(false);
			this.wakeUpButton.setEnabled(false);
		}
	}

	@Override
	public void updateZombie(boolean zombie) {
		if(zombie){
			this.isSleepingLabel.setText("Zombie");
			this.sleepButton.setEnabled(true);
			this.wakeUpButton.setEnabled(false);
			this.windOffButton.setEnabled(true);
			this.windOnButton.setEnabled(false);
		}
		else{
			this.sleepButton.setEnabled(false);
		}
	}

}

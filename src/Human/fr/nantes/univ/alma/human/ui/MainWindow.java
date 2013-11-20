/**
 * 
 */
package fr.nantes.univ.alma.human.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
	
	public MainWindow(IHuman human){
		
		this.human = human;
		
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
		this.windOnButton.addActionListener(new WindOnActionListener(this.human));
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		this.actionPanel.add(windOnButton, gridBagConstraints);
		
		this.windOffButton = new JButton("Wind Off");
		this.windOffButton.addActionListener(new WindOffActionListener(this.human));
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		this.actionPanel.add(windOffButton, gridBagConstraints);
		
		this.wakeUpButton = new JButton("Wake Up");
		this.wakeUpButton.addActionListener(new WakeUpActionListener(this.human));
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		this.actionPanel.add(wakeUpButton, gridBagConstraints);
		
		this.sleepButton = new JButton("   Sleep   ");
		this.sleepButton.addActionListener(new SleepActionListener(this.human));
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
		this.updateSleep(this.human.isSleeping());
		this.updateWindOn(this.human.isAlarmWindOn());
		
		this.add(this.tabbedPane);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void updateSleep(boolean isSleeping) {
		if (isSleeping){
			this.isSleepingLabel.setText("Sleep");
			this.wakeUpButton.setEnabled(true);
			this.sleepButton.setEnabled(false);
			this.getTimeButton.setEnabled(false);
		} 
		else {
			this.isSleepingLabel.setText("Wake Up");
			this.wakeUpButton.setEnabled(false);
			this.sleepButton.setEnabled(true);
		}
	}

	@Override
	public void updateWindOn(boolean isWindOn) {
		if(isWindOn){
			this.isWindOnLabel.setText("Wind On");
			this.windOnButton.setEnabled(false);
			this.windOffButton.setEnabled(true);
		}
		else {
			this.isWindOnLabel.setText("Wind Off");
			this.windOnButton.setEnabled(true);
			this.windOffButton.setEnabled(false);
		}
	}
	
}

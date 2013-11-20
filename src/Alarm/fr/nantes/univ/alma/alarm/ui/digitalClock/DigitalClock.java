/**
 * 
 */
package fr.nantes.univ.alma.alarm.ui.digitalClock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author bmael
 *
 */
public class DigitalClock extends JPanel {
	
	/**
	 * The generated serial version UID
	 */
	private static final long serialVersionUID = 414250607266898912L;
	String stringTime;

	public DigitalClock() {

		Timer t1 = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				repaint();
			}
		});
		t1.start();
	}
	
	/**
	 * Set the string time to xyz.
	 * @param xyz the new value of string time to set.
	 */
	public void setStringTime(String xyz) {
		this.stringTime = xyz;
	}

	/**
	 * Return the minimum between a and b.
	 * @param a int
	 * @param b int
	 * @return the minimum between a and b.
	 */
	public int findMinimumBetweenTwoNumbers(int a, int b) {
		return (a <= b) ? a : b;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);

		String correctionHour = (hour < 10) ? "0" : "";
		String correctionMinute = (minute < 10) ? "0" : "";
		String correctionSecond = (second < 10) ? "0" : "";
		setStringTime(correctionHour + hour + ":" + correctionMinute+ minute + ":" + correctionSecond + second);
		
		g.setColor(Color.BLACK);
		int length = findMinimumBetweenTwoNumbers(this.getWidth(),this.getHeight());
		Font myFont = new Font("SansSerif", Font.PLAIN, 50);
		
		g.setFont(myFont);
		g.drawString(stringTime, length/6, 50);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 50);
	}
}

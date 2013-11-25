/**
 * 
 */
package fr.nantes.univ.alma.alarm.observer;

/**
 * @author bmael
 *
 */
public interface Observer {
	public void updateWindOn(boolean isWindOn);
	public void updateRinging(boolean isRinging);
}

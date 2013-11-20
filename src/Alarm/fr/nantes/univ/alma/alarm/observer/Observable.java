/**
 * 
 */
package fr.nantes.univ.alma.alarm.observer;

/**
 * @author bmael
 *
 */
public interface Observable {
	public void addObserver(Observer obs);
	public void updateWindOnObserver();
	public void updateRingingObserver();
	public void delObserver();
}

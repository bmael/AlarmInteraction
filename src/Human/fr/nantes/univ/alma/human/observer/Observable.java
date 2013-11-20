/**
 * 
 */
package fr.nantes.univ.alma.human.observer;

/**
 * @author bmael
 *
 */
public interface Observable {
	public void addObserver(Observer obs);
	public void updateSleepObserver();
	public void updateWindOnObserver();
	public void delObserver();
}

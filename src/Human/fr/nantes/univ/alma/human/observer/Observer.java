/**
 * 
 */
package fr.nantes.univ.alma.human.observer;

/**
 * @author bmael
 *
 */
public interface Observer {
	public void updateSleep(boolean isSleeping);
	public void updateWindOn(boolean isWindOn);
	public void updateZombie(boolean zombie);
}

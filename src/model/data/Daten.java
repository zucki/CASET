/**
 * 
 */
package model.data;

/**
 * @author smgug_000
 *
 */
public class Daten {
	private static Daten instance;
	
	private Daten() {
		
	}
	
	public static Daten getInstance() {
		if (instance == null) {
			instance = new Daten();
		}
		return instance;
	}
}
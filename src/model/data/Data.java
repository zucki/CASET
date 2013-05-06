/**
 * 
 */
package model.data;

/**
 * @author smgug_000
 *
 */
public class Data {
	private static Data instance;
	
	private Data() {
		
	}
	
	public static Data getInstance() {
		if (instance == null) {
			instance = new Data();
		}
		return instance;
	}
}
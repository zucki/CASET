/**
 * 
 */
package model.data;

import java.util.List;

/**
 * @author smgug_000
 *
 */
public class Data {
	private static Data instance;
	
	private List<Project> projects;
	
	private Data() {
		
	}
	
	public static Data getInstance() {
		if (instance == null) {
			instance = new Data();
		}
		return instance;
	}

	public List<Project> getProjects() {
		return projects;
	}
}
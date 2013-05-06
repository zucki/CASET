/**
 * 
 */
package view;

import model.data.Project;

/**
 * @author smgug_000
 *
 */
public interface ViewInterface {
	public void show();
	public void createNewProject();
	public Project getSelectedProject();
	public void removeSelectedProject();
}

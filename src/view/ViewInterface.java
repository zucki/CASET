/**
 * 
 */
package view;

import org.eclipse.swt.widgets.Shell;

import model.data.GlossaryEntry;
import model.data.Project;
import model.data.ProjectField;

/**
 * @author smgug_000
 *
 */
public interface ViewInterface {
	public void show();
	public void createNewProject(String projectName);
	public String getSelectedProject();
	public void removeSelectedProject();
	public GlossaryEntry getSelectedGlossaryEntry();
	public void showGlossaryChanges();
	public Shell getShell();
	public void setData(String projectName, ProjectField field, Object value);
	public String getData(String projectName, ProjectField field);
	public void showProjectNameValidity(boolean valid);
	public void changeProjectName(String newName);
}

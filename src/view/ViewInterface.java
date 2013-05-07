/**
 * 
 */
package view;

import org.eclipse.swt.widgets.Shell;

import model.data.GlossaryEntry;
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
	public GlossaryEntry getSelectedGlossaryEntry();
	public void showGlossaryChanges();
	public Shell getShell();
}

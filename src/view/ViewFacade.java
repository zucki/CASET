/**
 * 
 */
package view;

import org.eclipse.swt.widgets.Shell;

import controller.Controller;
import controller.ControllerInterface;
import model.ModelFacade;
import model.data.Data;
import model.data.GlossaryEntry;
import model.data.Project;
import view.gui.MainWindow;

/**
 * @author smgug_000
 *
 */
public class ViewFacade implements ViewInterface {
	private MainWindow mainWindow;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ControllerInterface controller = new Controller(new ModelFacade(new Data()));
			ViewInterface view = new ViewFacade(controller);
			controller.setView(view);
			view.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ViewFacade(ControllerInterface controller) {
		this.mainWindow = new MainWindow(controller);
	}
	
	public void show() {
		this.mainWindow.open();
	}
	
	public void createNewProject(String projectName) {
		this.mainWindow.createProject(projectName);
	}
	
	public String getSelectedProject() {
		return this.mainWindow.getSelectedProjectComposite().getProjectName();
	}

	@Override
	public void removeSelectedProject() {
		this.mainWindow.removeSelectedProject();
		
	}
	
	public GlossaryEntry getSelectedGlossaryEntry() {
		return this.mainWindow.getSelectedGlossaryEntry();
	}

	@Override
	public void showGlossaryChanges() {
		this.mainWindow.showGlossaryChanges();
	}
	
	public Shell getShell() {
		return this.mainWindow.getShell();
	}
}

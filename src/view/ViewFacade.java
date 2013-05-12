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
import model.data.ProjectField;
import model.data.Specification;
import model.data.SpecificationType;
import view.gui.MainWindow;
import view.gui.ProjectComposite;

/**
 * @author smgug_000
 *
 */
public class ViewFacade implements ViewInterface {
	private MainWindow mainWindow;
	
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
	
	public ProjectComposite getSelectedProjectComposite() {
		return this.mainWindow.getSelectedProjectComposite();
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

	@Override
	public void setData(String projectName, ProjectField field, Object value) {
		ProjectComposite pc = mainWindow.getProjectComposite(projectName);
		pc.setData(field, value);
	}

	@Override
	public String getData(String projectName, ProjectField field) {
		ProjectComposite pc = mainWindow.getProjectComposite(projectName);
		return pc.getData(field);
	}
	
	@Override
	public void showProjectNameValidity(boolean valid) {
		this.mainWindow.showProjectNameValidity(valid);
	}

	@Override
	public void changeProjectName(String newName) {
		this.mainWindow.changeProjectName(newName);
	}

	@Override
	public Specification getSpecification() {
		return this.mainWindow.getSpecification();
	}

	@Override
	public Specification getSelectedSpecification() {
		return this.mainWindow.getSelectedSpecification();
	}

	@Override
	public void showSpecificationChanges() {
		this.mainWindow.showSpecificationChanges();
	}

	@Override
	public SpecificationType getSpecificationType() {
		return this.mainWindow.getSpecificationType();
	}
}

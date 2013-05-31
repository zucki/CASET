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
import model.data.InfluencingFactorTypeEnum;
import model.data.Project;
import model.data.ProjectFieldEnum;
import model.data.Specification;
import model.data.SpecificationFieldEnum;
import model.data.SpecificationTypeEnum;
import model.services.calculation.CalculationMethod;
import model.services.calculation.CalculationResults;
import view.gui.MainWindow;
import view.gui.ProjectComposite;

/**
 * Implementation of the ViewInterface
 * @author smgug_000
 *
 */
public class ViewFacade implements ViewInterface {
	private MainWindow _mainWindow;
	private static ViewFacade _instance;
	
	/**
	 * The view has to get listners from the controller
	 * @param controller implementation of ControllerInterface
	 * @return the instance
	 */
	public static ViewFacade makeInstance(ControllerInterface controller) {
		if (_instance == null) {
			_instance = new ViewFacade(controller);
		}
		return _instance;
	}
	
	/**
	 * @return the instance
	 */
	public static ViewFacade getInstance() {
		return _instance;
	}
	
	private  ViewFacade(ControllerInterface controller) {
		this._mainWindow = new MainWindow(controller);
	}
	
	public void show() {
		this._mainWindow.open();
	}
	
	public void createNewProject(String projectName) {
		this._mainWindow.createProject(projectName);
	}
	
	public String getSelectedProject() {
		return this._mainWindow.getSelectedProjectComposite().getProjectName();
	}
	
	public ProjectComposite getSelectedProjectComposite() {
		return this._mainWindow.getSelectedProjectComposite();
	}

	@Override
	public void removeSelectedProject() {
		this._mainWindow.removeSelectedProject();
		
	}
	
	public GlossaryEntry getSelectedGlossaryEntry() {
		return this._mainWindow.getSelectedGlossaryEntry();
	}

	@Override
	public void showGlossaryChanges() {
		this._mainWindow.showGlossaryChanges();
	}
	
	public Shell getShell() {
		return this._mainWindow.getShell();
	}

	@Override
	public void setData(String projectName, ProjectFieldEnum field, Object value) {
		ProjectComposite pc = _mainWindow.getProjectComposite(projectName);
		pc.setData(field, value);
	}

	@Override
	public String getData(String projectName, ProjectFieldEnum field) {
		ProjectComposite pc = _mainWindow.getProjectComposite(projectName);
		return pc.getData(field);
	}
	
	@Override
	public String getData(String projectName, SpecificationFieldEnum field) {
		ProjectComposite pc = _mainWindow.getProjectComposite(projectName);
		return pc.getData(field);
	}
	
	@Override
	public void showProjectNameValidity(boolean valid) {
		this._mainWindow.showProjectNameValidity(valid);
	}

	@Override
	public void changeProjectName(String newName) {
		this._mainWindow.changeProjectName(newName);
	}

	@Override
	public Specification getSelectedSpecification() {
		return this._mainWindow.getSelectedSpecification();
	}

	@Override
	public void showSpecificationChanges() {
		this._mainWindow.showSpecificationChanges();
	}

	@Override
	public SpecificationTypeEnum getSpecificationType() {
		return this._mainWindow.getSpecificationType();
	}

	@Override
	public String getdata(String projectName, InfluencingFactorTypeEnum type) {
		ProjectComposite pc = this._mainWindow.getProjectComposite(projectName);
		return pc.getData(type);
	}

	@Override
	public CalculationMethod getCalculationMethod() {
		return _mainWindow.getCalculationMethod();
	}

	@Override
	public void setCalculationResult(CalculationResults results) {
		_mainWindow.setCalculationResult(results);
	}
}

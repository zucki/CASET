/**
 * 
 */
package view;

import org.eclipse.swt.widgets.Shell;

import model.data.GlossaryEntry;
import model.data.InfluencingFactorTypeEnum;
import model.data.Project;
import model.data.ProjectFieldEnum;
import model.data.Specification;
import model.data.SpecificationFieldEnum;
import model.data.SpecificationTypeEnum;
import model.services.calculation.CalculationMethod;
import model.services.calculation.CalculationResults;

/**
 * Interface of the view in the MVC construct.
 * @author smgug_000
 *
 */
public interface ViewInterface {
	/**
	 * Shows the Application Window of the view
	 */
	public void show();
	/**
	 * Adds a project to the view 
	 * @param projectName the projectname of the project to be added
	 */
	public void createNewProject(String projectName);
	/**
	 * @return projectname of the project, that is currently selected
	 */
	public String getSelectedProject();
	/**
	 * Removs the project that is currently selected
	 */
	public void removeSelectedProject();
	/**
	 * @return the glossaryentry that is currently selected or null, if no 
	 * entry is selected
	 */
	public GlossaryEntry getSelectedGlossaryEntry();
	/**
	 * refreshes the glossary, so changes made in the model are shown
	 */
	public void showGlossaryChanges();
	/**
	 * @return the window
	 */
	public Shell getShell();
	/**
	 * Sets data in the gui
	 * @param projectName name of the project
	 * @param field field to set
	 * @param value value of the field
	 */
	public void setData(String projectName, ProjectFieldEnum field, Object value);
	/**
	 * Gets data from the view
	 * @param projectName project to get data from
	 * @param field field to get
	 * @return value of the field of the project
	 */
	public String getData(String projectName, ProjectFieldEnum field);
	/**
	 * Shows that the chosen projectname is valid/invalid
	 * @param valid
	 */
	public void showProjectNameValidity(boolean valid);
	/**
	 * Changes the name of the selected project in the view
	 * @param newName new name of the project
	 */
	public void changeProjectName(String newName);
	/**
	 * Get the curently selected specification of the selected project
	 * @return the specifiation
	 */
	public Specification getSelectedSpecification();
	/**
	 * refreshes the specification so changes made in the model are shown
	 */
	public void showSpecificationChanges();
	/**
	 * Gets the type of the currently selected specification
	 * @return type of the specification
	 */
	public SpecificationTypeEnum getSpecificationType();
	/**
	 * Gets data from the view
	 * @param projectName project to get data from
	 * @param field field to get
	 * @return value of the field of the project
	 */
	public String getData(String projectName, SpecificationFieldEnum field);
	/**
	 * Gets data from the view
	 * @param projectName project to get data from
	 * @param field field to get
	 * @return value of the field of the project
	 */
	public String getdata(String projectName, InfluencingFactorTypeEnum type);
	/**
	 * Gets the chosen calculationmethod, if there is no open dialog it returns null,
	 * this should never happen 
	 * @return calcualtionmethod/null
	 */
	public CalculationMethod getCalculationMethod();
	
	/**
	 * Shows the results of a calculation in the gui
	 */
	public void setCalculationResult(CalculationResults results);
	
	public String saveFileDialog();
}

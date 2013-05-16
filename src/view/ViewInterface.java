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
	public void setData(String projectName, ProjectFieldEnum field, Object value);
	public String getData(String projectName, ProjectFieldEnum field);
	public void showProjectNameValidity(boolean valid);
	public void changeProjectName(String newName);
	public Specification getSpecification();
	public Specification getSelectedSpecification();
	public void showSpecificationChanges();
	public SpecificationTypeEnum getSpecificationType();
	public String getData(String projectName, SpecificationFieldEnum field);
	public String getdata(String projectName, InfluencingFactorTypeEnum type);
}

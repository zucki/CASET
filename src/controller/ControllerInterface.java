package controller;

import model.data.GlossaryFieldEnum;
import model.data.InfluencingFactorTypeEnum;
import model.data.ProjectFieldEnum;
import model.data.SpecificationFieldEnum;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;

import view.ViewInterface;

/**
 * Interface of the controller in the MVC construct.
 * @author smgug_000
 *
 */
public interface ControllerInterface {
	/**
	 * Sets the view, which will display stuff to the user
	 * @param implementation of the ViewInterface
	 */
	public void setView(ViewInterface view);
	/**
	 * Creates a project, when a button is clicked
	 * @return listener for the button
	 */
	public SelectionAdapter createProject();
	/**
	 * Removes the project, that is selcted in the view, if a button is clicked
	 * @return listener for the button
	 */
	public SelectionAdapter removeSelectedProject();
	/**
	 * Creates a new Glossary Entry, when a button is clicked
	 * @return listener fot the button
	 */
	public SelectionAdapter createGlossaryEntry();
	/**
	 * Removes the selected Glossary Entry in the selected Project, if a button is clicked
	 * @return listener for the button
	 */
	public SelectionAdapter removeGlossaryEntry();
	/**
	 * Creates a specification, of the selected type in the selected project, when a button is clicked
	 * @return listener for the button
	 */
	public SelectionAdapter createSpecification();
	/**
	 * Removes the selected specification in the selected project, when a button is clicked
	 * @return listener for the button
	 */
	public SelectionAdapter removeSpecification();
	/**
	 * Changes the project name of the selected project, if it is modified in it's textbox.
	 * It needs a own listener, because the projectname is the identifier of projects and
	 * has to unique.
	 * @return listener for the textbox
	 */
	public ModifyListener changeProjectName();
	/**
	 * Common listener for all project fields except the name. Given a ProjectField
	 * it changes that field in the Project, once the textbox it is added to is modified.
	 * @param field the field, he textbox belongs to has to be specified here
	 * @return a listener for the textbox
	 */
	public ModifyListener changeProjectField(ProjectFieldEnum field);
	/**
	 * Starts the calculation with the chosen calculationmethod
	 * @return listener for a button
	 */
	public SelectionAdapter startCalculation();
	/**
	 * Common listener for Glossary Entry and Description. Given a GlossaryField
	 * it changes that field in the currently selected glossary entry, 
	 * when the textbox it is added to is modified.
	 * @param field glossary field the textbox belongs to
	 * @return listener for the textbox
	 */
	ModifyListener changeGlossaryEntry(GlossaryFieldEnum field);
	/**
	 * Common listener for all specification fields. Given a SpecificationField
	 * it changes that field in the selected Specification, in the selected project,
	 * when the text/combobox gets modified.
	 * @param field specification field the control belongs to
	 * @return listener for the control
	 */
	public ModifyListener changeSpecification(SpecificationFieldEnum field);
	/**
	 *	Common listener for all influencing factors. Given a InfluencingFactorType
	 * it changes that field in the Project, once the textbox it is added to is modified.
	 * @param field type of the influencing factor the control belongs to
	 * @return listener fo the control
	 */
	public ModifyListener changeInfluencingFactor(InfluencingFactorTypeEnum field);
	
	/**
	 * A listener that exports a project as xml-file
	 * @return a listener for a button
	 */
	public SelectionAdapter exportProjectAsXml();
}

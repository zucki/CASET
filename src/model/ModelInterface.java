package model;

import java.util.ArrayList;

import model.data.GlossaryEntry;
import model.data.ProjectField;

/**
 * Interface of the model in the MVC construct.
 * 
 * @author Aaron
 *
 */
public interface ModelInterface {
	
	public boolean createNewProject(String projectName);
	
	/**
	 * @param projecName: Name of the project.
	 * @param field: Field in this project to change.
	 * @param value: String of new value.
	 * @return: True, if change was successful.
	 */
	public boolean changeProjectField(String projecName, ProjectField field, String value);
	
	/**
	 * @param projecName: name of the project
	 * @param field: ProjectField which should be returned.
	 * @return: String value of the given field.
	 */
	public String getProjectField(String projecName, ProjectField field);
	
	/**
	 * Get the glossary of a given project.
	 * @param projectName: String of the name of the project.
	 * @return: null if the given project doesn't exist or the glossary as an ArrayList.
	 */
	public ArrayList<GlossaryEntry> getGlossary(String projectName);
	
	/**
	 * @param projectName
	 * @return: true if function was successful and project is removed.
	 */
	public boolean removeProject(String projectName);
	
}
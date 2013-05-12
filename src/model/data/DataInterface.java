/**
 * 
 */
package model.data;

import java.util.ArrayList;

/**
 * Interface for the Data structure.
 * @author Aaron
 *
 */
public interface DataInterface {
	
	
	/**
	 * @param projectName
	 * @return
	 */
	public boolean createNewProject(String projectName);
	
	/**
	 * @param projecName: name of the project
	 * @param field: ProjectField which should be returned.
	 * @return: String value of the given field or null if project doesn't exist.
	 */
	public String getProjectField(String projectName, ProjectField field);
	
	/**
	 * Get the glossary of a given project.
	 * @param projectName: String of the name of the project.
	 * @return: null if the given project doesn't exist or the glossary as an ArrayList.
	 */
	public ArrayList<GlossaryEntry> getGlossary(String projectName);
	
	/**
	 * @param projecName: Name of the project.
	 * @param field: Field in this project to change.
	 * @param value: String of new value.
	 * @return: True, if change was successful.
	 */
	public boolean changeProjectField(String projectName, ProjectField field, String value);
	
	/**
	 * @param projectName
	 * @return: true if function was successful and project is removed.
	 */
	public boolean removeProject(String projectName);
	
	/**
	 * @param projectName: name of the project in which the specification should be created.
	 * @param type: SpecificationType Enum of the new Specification.
	 * @return false if project doesn't exist and creating the new specification was not successful,
	 * true if creating the new specification was successful.
	 */
	public int createNewSpecification(String projectName, SpecificationType type);
	
	/**
	 * @param projectName: Name of the project, in which the the Specification should be.
	 * @param specificationIndex: Index of the specification in the given project.
	 * @return: true if deleting the specification was successful, false if the project or the specification doesn't
	 * exist in the data pool
	 */
	public boolean deleteSpecification(String projectName, int specificationIndex);
	
	/**
	 * @param projectName: Name of the project, in which the the Specification should be.
	 * @param specificationIndex: Index of the specification in the given project.
	 * @param field: SpecificationField of the field, that should be changed.
	 * @param value: String of new value.
	 * @return: True, if change was successful.
	 */
	public boolean changeSpecificationField(String projectName, int specificationIndex, SpecificationField field, String value);
	
	/**
	 * @param projectName: Name of the project, in which the the Specification should be.
	 * @param specificationIndex: Index of the specification in the given project.
	 * @param field: SpecificationField of the field, that should be changed.
	 * @return String of the field given in parameters or null if project or specification doesn't exist.
	 */
	public String changeSpecificationField(String projectName, int specificationIndex, SpecificationField field);

	ArrayList<Specification> getSpecifications(String projectName);

}

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
	public String getProjectField(String projectName, ProjectFieldEnum field);
	
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
	public boolean changeProjectField(String projectName, ProjectFieldEnum field, String value);
	
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
	public Specification createNewSpecification(String projectName, SpecificationTypeEnum type);
	
	/**
	 * @param projectName: Name of the project, in which the the Specification should be.
	 * @param specification: Reference of a Specification.
	 * @return: true if deleting the specification was successful, false if the project or the specification doesn't
	 * exist in the data pool
	 */
	public boolean deleteSpecification(String projectName, Specification specification);
	
	/**
	 * @param projectName: Name of the project, in which the the Specification should be.
	 * @param specification: Reference of a Specification.
	 * @param field: SpecificationField of the field, that should be changed. Except of  Enum value Specifications.
	 * @param value: String of new value.
	 * @return: True, if change was successful.
	 */
	public boolean changeSpecificationField(String projectName, Specification specification, 
			SpecificationFieldEnum field, String value);
	
	/**
	 * @param projectName: Name of the project, in which the the Specification should be.
	 * @param specification: Reference of a Specification.
	 * @param field: SpecificationField of the field, that should be changed. Except of  Enum value Specifications.
	 * @return String of the field given in parameters or null if project or specification doesn't exist.
	 */
	public String getSpecificationField(String projectName, Specification specification, SpecificationFieldEnum field);

	/**
	 * @param projectName: Name of a project.
	 * @return ArrayList of the specifications in the given project.
	 */
	ArrayList<Specification> getSpecifications(String projectName);
	
	/**
	 * @param projectName: Name of the project
	 * @return ArrayList of the Specifications, that are particular for the Function Point Caclulation 
	 * of the given project.
	 */
	public ArrayList<CalculatedSpecification> getCalculatedSpecifications(String projectName);
	
	/**
	 * @param projectName: Name of the project.
	 * @param value: New value of the 
	 * @return
	 */
	public boolean changeInflencingFactorField(String projectName, InfluencingFactorTypeEnum type, String value);
	
	/**
	 * @param projectName
	 * @return Array of all InfluencingFactors in the given project.
	 */
	public InfluencingFactor[] getInfluencingFactors(String projectName);

}

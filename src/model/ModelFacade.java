package model;

import java.util.ArrayList;

import model.data.DataInterface;
import model.data.GlossaryEntry;
import model.data.ProjectField;
import model.data.Specification;
import model.data.SpecificationField;
import model.data.SpecificationType;

/**
 * Facade class of the model, which implements the ModelInterface.
 * 
 * @author Aaron
 *
 */
public class ModelFacade implements ModelInterface {
	
	DataInterface data;
	
	
	/**
	 * @param data: DataInterface, that should be used.
	 */
	public ModelFacade(DataInterface data){
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#createNewProject(java.lang.String)
	 */
	@Override
	public boolean createNewProject(String projectName) {
		
		return data.createNewProject(projectName);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#changeProjectField(java.lang.String, model.data.ProjectField, java.lang.String)
	 */
	@Override
	public boolean changeProjectField(String projectName, ProjectField field,
			String value) {
		return data.changeProjectField(projectName, field, value);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#getProjectField(java.lang.String, model.data.ProjectField)
	 */
	@Override
	public String getProjectField(String projectName, ProjectField field) {
		return data.getProjectField(projectName, field);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#getGlossary(java.lang.String)
	 */
	@Override
	public ArrayList<GlossaryEntry> getGlossary(String projectName) {
		return data.getGlossary(projectName);
	}
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#getGlossary(java.lang.String)
	 */
	@Override
	public ArrayList<Specification> getSpecifications(String projectName) {
		return data.getSpecifications(projectName);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#removeProject(java.lang.String)
	 */
	@Override
	public boolean removeProject(String projectName) {
		return data.removeProject(projectName);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#createNewSpecification(java.lang.String)
	 */
	@Override
	public int createNewSpecification(String projectName, SpecificationType type) {
		return data.createNewSpecification(projectName, type);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#deleteSpecification(java.lang.String, int)
	 */
	@Override
	public boolean deleteSpecification(String projectName,
			int specificationIndex) {
		
		return data.deleteSpecification(projectName, specificationIndex);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#changeProjectField(java.lang.String, int, model.data.SpecificationField, java.lang.String)
	 */
	@Override
	public boolean changeSpecificationField(String projectName,
			int specificationIndex, SpecificationField field, String value) {
		
		return data.changeSpecificationField(projectName, specificationIndex, field, value);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#changeSpecificationField(java.lang.String, int, model.data.SpecificationField)
	 */
	@Override
	public String changeSpecificationField(String projectName,
			int specificationIndex, SpecificationField field) {
		
		return data.changeSpecificationField(projectName, specificationIndex, field);
	}
	
	
}
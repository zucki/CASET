package model;

import java.util.ArrayList;

import model.data.DataInterface;
import model.data.GlossaryEntry;
import model.data.InfluencingFactorType;
import model.data.ProjectField;
import model.data.Specification;
import model.data.SpecificationField;
import model.data.SpecificationType;
import model.services.calculation.CalculationInterface;
import model.services.calculation.CalculationMethod;
import model.services.calculation.CalculationResults;

/**
 * Facade class of the model, which implements the ModelInterface.
 * 
 * @author Aaron
 *
 */
public class ModelFacade implements ModelInterface {
	
	DataInterface data;
	CalculationInterface calculation;
	
	
	/**
	 * @param data: DataInterface, that should be used.
	 */
	public ModelFacade(DataInterface data, CalculationInterface calculation){
		this.data = data;
		this.calculation = calculation;
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
	public Specification createNewSpecification(String projectName, SpecificationType type) {
		return data.createNewSpecification(projectName, type);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#deleteSpecification(java.lang.String, int)
	 */
	@Override
	public boolean deleteSpecification(String projectName,
			Specification specification) {
		
		return data.deleteSpecification(projectName, specification);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#changeProjectField(java.lang.String, int, model.data.SpecificationField, java.lang.String)
	 */
	@Override
	public boolean changeSpecificationField(String projectName,
			Specification specification, SpecificationField field, String value) {
		
		return data.changeSpecificationField(projectName, specification, field, value);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#changeSpecificationField(java.lang.String, int, model.data.SpecificationField)
	 */
	@Override
	public String getSpecificationField(String projectName,
			Specification specification, SpecificationField field) {
		
		return data.getSpecificationField(projectName, specification, field);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#changeInflencingFactorField(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean changeInflencingFactorField(String projectName, InfluencingFactorType type, String value) {
		
		return data.changeInflencingFactorField(projectName, type, value);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#calculate(java.lang.String, model.services.calculation.CalculationMethod)
	 */
	@Override
	public CalculationResults calculate(String projectname,
			CalculationMethod method) {
		return calculation.calculate(projectname, method);
	}
	
	
}
package model;

import java.util.ArrayList;

import model.data.DataInterface;
import model.data.GlossaryEntry;
import model.data.InfluencingFactorTypeEnum;
import model.data.ProjectFieldEnum;
import model.data.Specification;
import model.data.SpecificationFieldEnum;
import model.data.SpecificationTypeEnum;
import model.services.calculation.CalculationInterface;
import model.services.calculation.CalculationMethod;
import model.services.calculation.CalculationResults;

/**
 * Facade class of the model, which implements the ModelInterface.
 * Design Pattern Singleton is used for this Class.
 * 
 * @author Aaron
 *
 */
public class ModelFacade implements ModelInterface {
	
	private static ModelFacade _modelFacade = null;
	private DataInterface data;
	private CalculationInterface calculation;
	
	
	/**
	 * @param data: DataInterface, that should be used.
	 */
	private ModelFacade(DataInterface data, CalculationInterface calculation){
		this.data = data;
		this.calculation = calculation;
	}
	
	/**
	 * Singleton-Function to get the instance.
	 * @return the singleton instance
	 */
	public static ModelFacade getInstance(){
		return _modelFacade;
	}
	
	/**
	 * Singleton-Function to creat an Instance. 
	 * @param data: Data interface that should be used.
	 * @param calculation: Calculation interface that should be used.
	 */
	public static void makeInstance(DataInterface data, CalculationInterface calculation){
		if(_modelFacade == null){
			_modelFacade = new ModelFacade(data, calculation);
		}
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
	public boolean changeProjectField(String projectName, ProjectFieldEnum field,
			String value) {
		return data.changeProjectField(projectName, field, value);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#getProjectField(java.lang.String, model.data.ProjectField)
	 */
	@Override
	public String getProjectField(String projectName, ProjectFieldEnum field) {
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
	public Specification createNewSpecification(String projectName, SpecificationTypeEnum type) {
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
			Specification specification, SpecificationFieldEnum field, String value) {
		
		return data.changeSpecificationField(projectName, specification, field, value);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#changeSpecificationField(java.lang.String, int, model.data.SpecificationField)
	 */
	@Override
	public String getSpecificationField(String projectName,
			Specification specification, SpecificationFieldEnum field) {
		
		return data.getSpecificationField(projectName, specification, field);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#changeInflencingFactorField(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean changeInflencingFactorField(String projectName, InfluencingFactorTypeEnum type, String value) {
		
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
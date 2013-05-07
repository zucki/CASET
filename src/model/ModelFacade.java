package model;

import java.util.ArrayList;

import model.data.DataInterface;
import model.data.GlossaryEntry;
import model.data.ProjectField;

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
	public boolean changeProjectField(String projecName, ProjectField field,
			String value) {
		return data.changeProjectField(projecName, field, value);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#getProjectField(java.lang.String, model.data.ProjectField)
	 */
	@Override
	public String getProjectField(String projecName, ProjectField field) {
		return data.getProjectField(projecName, field);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#getGlossary(java.lang.String)
	 */
	@Override
	public ArrayList<GlossaryEntry> getGlossary(String projectName) {
		return data.getGlossary(projectName);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#removeProject(java.lang.String)
	 */
	@Override
	public boolean removeProject(String projectName) {
		return data.removeProject(projectName);
	}
	
	
}
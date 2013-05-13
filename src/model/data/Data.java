/**
 * 
 */
package model.data;

import java.util.ArrayList;

/**
 * Class of the complete Data pool, with a List of opened projects.
 * @author Aaron
 *
 */
public class Data implements DataInterface {
	
	private ArrayList<Project> projects;
	
	/**
	 * Default-constructor.
	 */
	public Data() {
		this.projects = new ArrayList<Project>();
	}
	

	/* (non-Javadoc)
	 * @see model.data.DataInterface#createNewProject(java.lang.String)
	 */
	@Override
	public boolean createNewProject(String projectName){
		if(getProject(projectName) == null){
			return projects.add(new Project(projectName));
		}
		else{
			return false; 
		}
	}
	
	/**
	 * @param projectName
	 * @return: Project with the given project name or null if project doesn't exist.
	 */
	public Project getProject(String projectName){
		Project ret = null;
		for(int i = 0; i < projects.size(); i++){
			if(projects.get(i).getName().equals(projectName)){
				return projects.get(i);
			}
		}
		return ret;
	}


	/* (non-Javadoc)
	 * @see model.data.DataInterface#getProjectField(java.lang.String, model.data.ProjectField)
	 */
	@Override
	public String getProjectField(String projectName, ProjectField field) {
		Project project = getProject(projectName);
		if(project == null){
			return null;
		}
		else{
			switch(field){
			case ProjectUse:
				return project.getProjectUse();
			case Author:
				return project.getAuthor();
			case Cocomomethod:
				return project.getCocomomethod().toString();
			case Company:
				return project.getCompany();
			case LinesOfCode:
				return String.valueOf(project.getLinesOfCode());
			case Name:
				return project.getName();
			case ValueAdjustmentFactor:
				return String.valueOf(project.getValueAdjustmentFactor());
			case TargetSpecification:
				return project.getTargetSpecification();
			default:
				return null;
			}
		}
	}


	/* (non-Javadoc)
	 * @see model.data.DataInterface#getGlossary(java.lang.String)
	 */
	@Override
	public ArrayList<GlossaryEntry> getGlossary(String projectName) {

		Project project = getProject(projectName);
		if(project != null){
			return project.getGlossary();
		}
		else{
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see model.data.DataInterface#getGlossary(java.lang.String)
	 */
	@Override
	public ArrayList<Specification> getSpecifications(String projectName) {

		Project project = getProject(projectName);
		if(project != null){
			return project.getSpecifications();
		}
		else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see model.data.DataInterface#changeProjectField(java.lang.String, model.data.ProjectField, java.lang.String)
	 */
	@Override
	public boolean changeProjectField(String projectName, ProjectField field,
			String value) {
		
		boolean ret = false; 
		Project project = getProject(projectName);
		if(project == null){
			return ret;
		}
		else{
			switch(field){
			case ProjectUse:
				project.setProjectUse(value);
				ret = true;
				break;
			case Author:
				project.setAuthor(value);
				ret = true;
				break;
			case Cocomomethod:
				project.setCocomomethod(CocomoMethod.valueOf(value));
				ret = true;
				break;
			case Company:
				project.setCompany(value);
				ret = true;
				break;
			case LinesOfCode:
				project.setLinesOfCode(Integer.parseInt(value));
				ret = true;
				break;
			case Name:
				ret = changeProjectName(project, value);
				break;
			case ValueAdjustmentFactor:
				project.setValueAdjustmentFactor(Double.parseDouble(value));
				ret = true;
				break;
			case TargetSpecification:
				project.setTargetSpecification(value);
				ret = true;
				break;
			default:
				ret = false;
				break;
			}
			return ret;
		}
	}

	/**
	 * Function to change the name of an project. Function is also checking if the project name already exists.
	 *  
	 * @param project: Project that should be changed.
	 * @param newName: New name of the project.
	 * @return true if name doesn't exist and function was successful. false, if name already exist.
	 */
	private boolean changeProjectName(Project project, String newName) {
		boolean valid = true;
		for (Project p: this.projects) {
			if (p.getName().equals(newName) && (p != project)) {
				valid = false;
			}
		}
		if (valid) {
			project.setName(newName);
		}
		return valid;
	}

	/* (non-Javadoc)
	 * @see model.data.DataInterface#removeProject(java.lang.String)
	 */
	@Override
	public boolean removeProject(String projectName) {
		Project project = getProject(projectName);
		
		if(project == null){
			return false;
		}
		else{
			return projects.remove(project);
		}
	}


	/* (non-Javadoc)
	 * @see model.data.DataInterface#createNewSpecification(java.lang.String)
	 */
	@Override
	public Specification createNewSpecification(String projectName, SpecificationType type) {
		Project project = getProject(projectName);
		if(project != null){
			Specification specification = SpecificationFactory.getInstance().createSpecification(type);
			project.getSpecifications().add(specification);
			return specification;
		}
		else{
			return null;
		}
	}


	/* (non-Javadoc)
	 * @see model.data.DataInterface#deleteSpecification(java.lang.String, int)
	 */
	@Override
	public boolean deleteSpecification(String projectName,
			Specification specification) {
		Project project = getProject(projectName);
		if (project != null){
			return project.getSpecifications().remove(specification);
		}
		else{
			return false;
		}
	}


	/* (non-Javadoc)
	 * @see model.data.DataInterface#changeSpecificationField(java.lang.String, int, model.data.SpecificationField, java.lang.String)
	 */
	@Override
	public boolean changeSpecificationField(String projectName,
			Specification specification, SpecificationField field, String value) {
		
		Project project = getProject(projectName);
		if(project != null){
			
			boolean ret = true;
			
			switch(field){
			case Description:
				specification.setDescription(value);
				break;
			case Name:
				specification.setName(value);
				break;
			case Category:
				if(specification instanceof ProductFunction){
					((ProductFunction)specification).setCategory(FunctionCategory.fromString(value));
				}
				else if(specification instanceof ProductData){
					((ProductData)specification).setCategory(DataCategory.fromString(value));
				}
				else{
					ret = false;
				}
				break;
			case Classification:
				if(specification instanceof ProductFunction){
					((ProductFunction)specification).setClassification(SpecificationClassification.valueOf(value));
				}
				else if(specification instanceof ProductData){
					((ProductData)specification).setClassification(SpecificationClassification.valueOf(value));
				}
				else{
					ret = false;
				}
				break;
			default:
				ret = false;
				break;
			}
			return ret;
		}
		else{
			return false;
		}
	}


	/* (non-Javadoc)
	 * @see model.data.DataInterface#changeSpecificationField(java.lang.String, int, model.data.SpecificationField)
	 */
	@Override
	public String getSpecificationField(String projectName,
			Specification specification, SpecificationField field) {

		Project project = getProject(projectName);
		if(project != null){
			String ret;
			
			switch(field){
			case Description:
				ret = specification.getDescription();
				break;
			case Name:
				ret = specification.getName();
				break;
			case Category:
				if(specification instanceof ProductFunction){
					ret = ((ProductFunction)specification).getCategory().toString();
				}
				else if(specification instanceof ProductData){
					ret = ((ProductData)specification).getCategory().toString();
				}
				else{
					ret = null;
				}
				break;
			case Classification:
				if(specification instanceof ProductFunction){
					ret = ((ProductFunction)specification).getClassification().toString();
				}
				else if(specification instanceof ProductData){
					ret = ((ProductData)specification).getClassification().toString();
				}
				else{
					ret = null;
				}
				break;
			default:
				ret = null;
				break;
			}
			return ret;
		}
		else{
			return null;
		}
	}
	
	
}
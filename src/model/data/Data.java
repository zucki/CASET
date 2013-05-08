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
	public String getProjectField(String projecName, ProjectField field) {
		Project project = getProject(projecName);
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

		for(int i = 0; i < projects.size(); i++){
			if(projects.get(i).getName().equals(projectName)){
				return projects.get(i).getGlossary();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see model.data.DataInterface#changeProjectField(java.lang.String, model.data.ProjectField, java.lang.String)
	 */
	@Override
	public boolean changeProjectField(String projecName, ProjectField field,
			String value) {
		
		boolean ret = false; 
		Project project = getProject(projecName);
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
	public int createNewSpecification(String projectName) {
		Project project = getProject(projectName);
		if(project != null){
			Specification specification = new Specification();
			project.getSpecifications().add(specification);
			return project.getSpecifications().indexOf(specification);
		}
		else{
			return -1;
		}
	}


	/* (non-Javadoc)
	 * @see model.data.DataInterface#deleteSpecification(java.lang.String, int)
	 */
	@Override
	public boolean deleteSpecification(String projectName,
			int specificationIndex) {
		Project project = getProject(projectName);
		if (project != null){
			ArrayList <Specification> specifications = project.getSpecifications();
			return specifications.remove(specifications.get(specificationIndex));
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
			int specificationIndex, SpecificationField field, String value) {
		
		Project project = getProject(projectName);
		if(project != null){
			try{
				Specification specification = project.getSpecifications().get(specificationIndex);
				boolean ret = true;
				
				switch(field){
				case Description:
					specification.setDescription(value);
					break;
				case Name:
					specification.setName(value);
					break;
				case Category:
					specification.setCategory(SpecificationCategory.fromString(value));
					break;
				default:
					ret = false;
					break;
				}
				return ret;
			}catch (IndexOutOfBoundsException e) {
				return false;
			}
		}
		else{
			return false;
		}
	}


	/* (non-Javadoc)
	 * @see model.data.DataInterface#changeSpecificationField(java.lang.String, int, model.data.SpecificationField)
	 */
	@Override
	public String changeSpecificationField(String projectName,
			int specificationIndex, SpecificationField field) {

		Project project = getProject(projectName);
		if(project != null){
			try{
				Specification specification = project.getSpecifications().get(specificationIndex);
				String ret;
				
				switch(field){
				case Description:
					ret = specification.getDescription();
					break;
				case Name:
					ret = specification.getName();
					break;
				case Category:
					ret = specification.getCategory().toString();
					break;
				default:
					ret = null;
					break;
				}
				return ret;
			}catch (IndexOutOfBoundsException e) {
				return null;
			}
		}
		else{
			return null;
		}
	}
	
	
}
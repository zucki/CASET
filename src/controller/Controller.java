/**
 * 
 */
package controller;

import model.ModelInterface;
import model.data.FunctionCategoryEnum;
import model.data.GlossaryEntry;
import model.data.GlossaryFieldEnum;
import model.data.InfluencingFactorTypeEnum;
import model.data.ProjectFieldEnum;
import model.data.Specification;
import model.data.SpecificationClassificationEnum;
import model.data.SpecificationFieldEnum;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import view.ViewInterface;

/**
 * @author smgug_000
 *
 */
public class Controller implements ControllerInterface{
	private ViewInterface view;
	private ModelInterface model;
	
	public Controller(ModelInterface model) {
		this.model = model;
	}
	
	public void setView(ViewInterface view) {
		this.view = view;
	}
	
	public SelectionAdapter createProject() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String projectName = "";
				int projectCount = 0;
				do {
					++projectCount;
					projectName = String.format("New Project %d", projectCount);
				} while(!model.createNewProject(projectName));
				view.createNewProject(projectName);
				view.setData(projectName, ProjectFieldEnum.Glossary, model.getGlossary(projectName));
				view.setData(projectName, ProjectFieldEnum.Name, projectName);
				view.setData(projectName, ProjectFieldEnum.Specifications, model.getSpecifications(projectName));
			}
		};
	}

	@Override
	public ModifyListener changeGlossaryEntry(GlossaryFieldEnum field) {
		return new ModifyGlossaryListener(model, view, field);
	}
	
	public SelectionAdapter createGlossaryEntry() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				model.getGlossary(view.getSelectedProject()).add(new GlossaryEntry("Entry", ""));
				view.showGlossaryChanges();
			}
		};
	}

	@Override
	public SelectionAdapter removeSelectedProject() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				model.removeProject(view.getSelectedProject());
				view.removeSelectedProject();
			}
		};
	}

	@Override
	public SelectionAdapter removeGlossaryEntry() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				model.getGlossary(view.getSelectedProject()).remove(view.getSelectedGlossaryEntry());
				view.showGlossaryChanges();
			}
		};
	}
	
	public ModifyListener changeProjectName() {
		return new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent event) {
				String name = view.getData(view.getSelectedProject(), ProjectFieldEnum.Name);
				boolean valid = model.changeProjectField(view.getSelectedProject(), ProjectFieldEnum.Name, name);
				view.showProjectNameValidity(valid);
				if (valid) {
					view.changeProjectName(name);
				}
			}
		};
	}

	@Override
	public ModifyListener changeProjectField(ProjectFieldEnum field) {
		return new ModifyFieldListener(model, view, field);
	}

	@Override
	public SelectionAdapter createSpecification() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String projectName = view.getSelectedProject();
				Specification spec = model.createNewSpecification(projectName, view.getSpecificationType());
				if (spec != null) {
					model.changeSpecificationField(projectName, spec, SpecificationFieldEnum.Name, "New Specification");
					model.changeSpecificationField(projectName, spec, 
							SpecificationFieldEnum.Classification, SpecificationClassificationEnum.Medium.toString());
					model.changeSpecificationField(projectName, spec, 
							SpecificationFieldEnum.Category, FunctionCategoryEnum.Database.toString());
					view.showSpecificationChanges();
				}
			}
		};
	}

	@Override
	public SelectionAdapter removeSpecification() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				model.deleteSpecification(view.getSelectedProject(), view.getSelectedSpecification());
				view.showSpecificationChanges();
			}
		};
	}

	@Override
	public ModifyListener changeSpecification(SpecificationFieldEnum field) {
		return new ModifySpecificationListener(model, view, field);
	}

	@Override
	public ModifyListener changeInfluencingFactor(InfluencingFactorTypeEnum field) {
		return new ModifyInfluencingFactorsListener(model, view, field);
	}
	
	
}

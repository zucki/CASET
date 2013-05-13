/**
 * 
 */
package controller;

import java.util.ArrayList;

import model.ModelFacade;
import model.ModelInterface;
import model.data.FunctionCategory;
import model.data.GlossaryEntry;
import model.data.GlossaryField;
import model.data.ProductData;
import model.data.ProductFunction;
import model.data.ProjectField;
import model.data.Specification;
import model.data.SpecificationClassification;
import model.data.SpecificationField;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import view.ViewFacade;
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
				view.setData(projectName, ProjectField.Glossary, model.getGlossary(projectName));
				view.setData(projectName, ProjectField.Name, projectName);
				view.setData(projectName, ProjectField.Specifications, model.getSpecifications(projectName));
			}
		};
	}

	@Override
	public ModifyListener changeGlossaryEntry(GlossaryField field) {
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
				String name = view.getData(view.getSelectedProject(), ProjectField.Name);
				boolean valid = model.changeProjectField(view.getSelectedProject(), ProjectField.Name, name);
				view.showProjectNameValidity(valid);
				if (valid) {
					view.changeProjectName(name);
				}
			}
		};
	}

	@Override
	public ModifyListener changeProjectField(ProjectField field) {
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
					model.changeSpecificationField(projectName, spec, SpecificationField.Name, "New Specification");
					model.changeSpecificationField(projectName, spec, 
							SpecificationField.Classification, SpecificationClassification.Medium.toString());
					model.changeSpecificationField(projectName, spec, 
							SpecificationField.Category, FunctionCategory.Database.toString());
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
	public ModifyListener changeSpecification(SpecificationField field) {
		return new ModifySpecificationListener(model, view, field);
	}
}

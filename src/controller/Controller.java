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
import model.services.calculation.CalculationResults;
import model.services.importexport.ExportType;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import view.ViewInterface;

/**
 * Controller class that implements ControllerInterface
 * @author smgug_000
 *
 */
public class Controller implements ControllerInterface{
	private ViewInterface _view;
	private ModelInterface _model;
	private static Controller _instance;
	
	/**
	 * Creates a new instance if there is none yet
	 * @param model implementation of ModelInterface
	 * @return the instance
	 */
	public static Controller makeInstance(ModelInterface model) {
		if (_instance == null) {
			_instance = new Controller(model);
		}
		return _instance;
	}
	
	/**
	 * @return the instance of the singleton
	 */
	public static Controller getInstance() {
		return _instance;
	}
	
	/**
	 * @param model implementation of ModelInterface
	 */
	private Controller(ModelInterface model) {
		this._model = model;
	}
	
	public void setView(ViewInterface view) {
		this._view = view;
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
				} while(!_model.createNewProject(projectName));
				_view.createNewProject(projectName);
				_view.setData(projectName, ProjectFieldEnum.Glossary, _model.getGlossary(projectName));
				_view.setData(projectName, ProjectFieldEnum.Name, projectName);
				_view.setData(projectName, ProjectFieldEnum.Specifications, _model.getSpecifications(projectName));
			}
		};
	}

	@Override
	public ModifyListener changeGlossaryEntry(GlossaryFieldEnum field) {
		return new ModifyGlossaryListener(_model, _view, field);
	}
	
	public SelectionAdapter createGlossaryEntry() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				_model.getGlossary(_view.getSelectedProject()).add(new GlossaryEntry("Entry", ""));
				_view.showGlossaryChanges();
			}
		};
	}

	@Override
	public SelectionAdapter removeSelectedProject() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				_model.removeProject(_view.getSelectedProject());
				_view.removeSelectedProject();
			}
		};
	}

	@Override
	public SelectionAdapter removeGlossaryEntry() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				_model.getGlossary(_view.getSelectedProject()).remove(_view.getSelectedGlossaryEntry());
				_view.showGlossaryChanges();
			}
		};
	}
	
	public ModifyListener changeProjectName() {
		return new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent event) {
				String name = _view.getData(_view.getSelectedProject(), ProjectFieldEnum.Name);
				boolean valid = _model.changeProjectField(_view.getSelectedProject(), ProjectFieldEnum.Name, name);
				_view.showProjectNameValidity(valid);
				if (valid) {
					_view.changeProjectName(name);
				}
			}
		};
	}

	@Override
	public ModifyListener changeProjectField(ProjectFieldEnum field) {
		return new ModifyFieldListener(_model, _view, field);
	}

	@Override
	public SelectionAdapter createSpecification() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String projectName = _view.getSelectedProject();
				Specification spec = _model.createNewSpecification(projectName, _view.getSpecificationType());
				if (spec != null) {
					_model.changeSpecificationField(projectName, spec, SpecificationFieldEnum.Name, "New Specification");
					_model.changeSpecificationField(projectName, spec, 
							SpecificationFieldEnum.Classification, SpecificationClassificationEnum.Medium.toString());
					_model.changeSpecificationField(projectName, spec, 
							SpecificationFieldEnum.Category, FunctionCategoryEnum.Database.toString());
					_view.showSpecificationChanges();
				}
			}
		};
	}

	@Override
	public SelectionAdapter removeSpecification() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				_model.deleteSpecification(_view.getSelectedProject(), _view.getSelectedSpecification());
				_view.showSpecificationChanges();
			}
		};
	}

	@Override
	public ModifyListener changeSpecification(SpecificationFieldEnum field) {
		return new ModifySpecificationListener(_model, _view, field);
	}

	@Override
	public ModifyListener changeInfluencingFactor(InfluencingFactorTypeEnum field) {
		return new ModifyInfluencingFactorsListener(_model, _view, field);
	}

	@Override
	public SelectionAdapter startCalculation() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CalculationResults results = _model.calculate(_view.getSelectedProject(), _view.getCalculationMethod());
				_view.setCalculationResult(results);
			}
		};
	}

	@Override
	public SelectionAdapter exportProjectAsXml() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String project = _view.getSelectedProject();
				String path = _view.saveFileDialog();
				System.out.println(path);
				if (path != "" && (path != null) && (project != null)) {
					_model.exportProject(ExportType.XML, project, path);
				}
			}
		};
	}
	
	
}

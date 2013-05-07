/**
 * 
 */
package controller;

import java.util.ArrayList;

import model.ModelFacade;
import model.ModelInterface;
import model.data.GlossaryEntry;
import model.data.ProjectField;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import view.ViewFacade;
import view.ViewInterface;
import view.gui.GlossaryEntryDialog;

/**
 * @author smgug_000
 *
 */
public class Controller implements ControllerInterface{
	private ViewInterface view;
	private ModelInterface model;
	private int projectCount;
	
	public Controller(ModelInterface model) {
		this.model = model;
		projectCount = 0;
	}
	
	public void setView(ViewInterface view) {
		this.view = view;
	}
	
	public SelectionAdapter createProject() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				++projectCount;
				String projectName = String.format("New Project %d", projectCount);
				model.createNewProject(projectName);
				view.createNewProject(projectName);
				view.setData(projectName, ProjectField.Glossary, model.getGlossary(projectName));
				view.setData(projectName, ProjectField.Name, projectName);
			}
		};
	}

	@Override
	public SelectionAdapter changeGlossaryEntry() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GlossaryEntry entry = view.getSelectedGlossaryEntry();
				GlossaryEntryDialog dialog = new GlossaryEntryDialog(view.getShell(), SWT.NONE, entry.getEntry());
				String result = dialog.open();
				if (result.length() > 0) {
						entry.setEntry(result);
						view.showGlossaryChanges();
				}
			}
		};
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
				view.removeSelectedProject();
				model.removeProject(view.getSelectedProject());
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
}

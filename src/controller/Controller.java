/**
 * 
 */
package controller;

import model.ModelFacade;
import model.ModelInterface;
import model.data.GlossaryEntry;

import org.eclipse.swt.SWT;
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
	
	public SelectionAdapter createProjectListener() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				++projectCount;
				String projectName = String.format("New Project %d", projectCount);
				model.createNewProject(projectName);
				view.createNewProject(projectName);
			}
		};
	}
	
	public SelectionAdapter removeSelectedProjectListener() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				view.removeSelectedProject();
			}
		};
	}

	@Override
	public SelectionAdapter removeSelectedProject() {
		// TODO Auto-generated method stub
		return null;
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
	
	public SelectionAdapter CreateGlossaryEntry() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				model.getGlossary(view.getSelectedProject()).add(new GlossaryEntry("Entry", ""));
				view.showGlossaryChanges();
			}
		};
	}
}

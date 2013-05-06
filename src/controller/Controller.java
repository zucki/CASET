/**
 * 
 */
package controller;

import model.ModelFacade;
import model.ModelInterface;

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
	
	public SelectionAdapter createProjectListener() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				view.createNewProject();
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
}

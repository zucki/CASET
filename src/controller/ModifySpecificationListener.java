package controller;

import model.ModelInterface;
import model.data.GlossaryField;
import model.data.SpecificationField;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import view.ViewInterface;

public class ModifySpecificationListener implements ModifyListener {

	private SpecificationField field;
	private ModelInterface model;
	private ViewInterface view;
	
	public ModifySpecificationListener(ModelInterface model, ViewInterface view, SpecificationField field) {
		this.field = field;
		this.model = model;
		this.view = view;
	}

	@Override
	public void modifyText(ModifyEvent arg0) {
		String projectName = view.getSelectedProject();
		String value = view.getData(projectName, field);
		model.changeSpecificationField(projectName, view.getSelectedSpecification(), 
				field, view.getData(projectName, field));
		view.showSpecificationChanges();
	}
}

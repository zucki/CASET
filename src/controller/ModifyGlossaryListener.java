package controller;

import model.ModelInterface;
import model.data.GlossaryEntry;
import model.data.GlossaryField;
import model.data.ProjectField;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import view.ViewInterface;

public class ModifyGlossaryListener implements ModifyListener {
	private GlossaryField field;
	private ModelInterface model;
	private ViewInterface view;
	
	public ModifyGlossaryListener(ModelInterface model, ViewInterface view, GlossaryField field) {
		this.field = field;
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void modifyText(ModifyEvent arg0) {
		GlossaryEntry entry = view.getSelectedGlossaryEntry();
		if (field == GlossaryField.Entry) {
			entry.setEntry(view.getData(view.getSelectedProject(), ProjectField.GlossaryEntry));
		} else {
			entry.setDefinition(view.getData(view.getSelectedProject(), ProjectField.GlossaryDescription));
		}
		view.showGlossaryChanges();
	}

}
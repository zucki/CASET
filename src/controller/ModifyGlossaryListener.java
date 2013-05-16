package controller;

import model.ModelInterface;
import model.data.GlossaryEntry;
import model.data.GlossaryFieldEnum;
import model.data.ProjectFieldEnum;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import view.ViewInterface;

public class ModifyGlossaryListener implements ModifyListener {
	private GlossaryFieldEnum field;
	private ModelInterface model;
	private ViewInterface view;
	
	public ModifyGlossaryListener(ModelInterface model, ViewInterface view, GlossaryFieldEnum field) {
		this.field = field;
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void modifyText(ModifyEvent arg0) {
		GlossaryEntry entry = view.getSelectedGlossaryEntry();
		if (field == GlossaryFieldEnum.Entry) {
			entry.setEntry(view.getData(view.getSelectedProject(), ProjectFieldEnum.GlossaryEntry));
		} else {
			entry.setDefinition(view.getData(view.getSelectedProject(), ProjectFieldEnum.GlossaryDescription));
		}
		view.showGlossaryChanges();
	}

}
package controller;

import model.ModelInterface;
import model.data.GlossaryEntry;
import model.data.GlossaryFieldEnum;
import model.data.ProjectFieldEnum;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import view.ViewInterface;

public class ModifyGlossaryListener implements ModifyListener {
	private GlossaryFieldEnum _field;
	private ModelInterface _model;
	private ViewInterface _view;
	
	public ModifyGlossaryListener(ModelInterface model, ViewInterface view, GlossaryFieldEnum field) {
		this._field = field;
		this._model = model;
		this._view = view;
	}
	
	@Override
	public void modifyText(ModifyEvent arg0) {
		GlossaryEntry entry = _view.getSelectedGlossaryEntry();
		if (_field == GlossaryFieldEnum.Entry) {
			entry.setEntry(_view.getData(_view.getSelectedProject(), ProjectFieldEnum.GlossaryEntry));
		} else {
			entry.setDefinition(_view.getData(_view.getSelectedProject(), ProjectFieldEnum.GlossaryDescription));
		}
		_view.showGlossaryChanges();
	}

}
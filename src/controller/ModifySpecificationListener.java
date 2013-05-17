package controller;

import model.ModelInterface;
import model.data.GlossaryFieldEnum;
import model.data.SpecificationFieldEnum;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import view.ViewInterface;

public class ModifySpecificationListener implements ModifyListener {

	private SpecificationFieldEnum _field;
	private ModelInterface _model;
	private ViewInterface _view;
	
	public ModifySpecificationListener(ModelInterface model, ViewInterface view, SpecificationFieldEnum field) {
		this._field = field;
		this._model = model;
		this._view = view;
	}

	@Override
	public void modifyText(ModifyEvent arg0) {
		String projectName = _view.getSelectedProject();
		String value = _view.getData(projectName, _field);
		_model.changeSpecificationField(projectName, _view.getSelectedSpecification(), 
				_field, _view.getData(projectName, _field));
		_view.showSpecificationChanges();
	}
}

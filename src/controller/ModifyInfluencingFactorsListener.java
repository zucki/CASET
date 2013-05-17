package controller;

import model.ModelInterface;
import model.data.InfluencingFactorTypeEnum;
import model.data.SpecificationFieldEnum;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import view.ViewInterface;

public class ModifyInfluencingFactorsListener implements ModifyListener {

	private InfluencingFactorTypeEnum _field;
	private ModelInterface _model;
	private ViewInterface _view;
	
	public ModifyInfluencingFactorsListener(ModelInterface model, ViewInterface view, InfluencingFactorTypeEnum field) {
		this._field = field;
		this._model = model;
		this._view = view;
	}
	
	@Override
	public void modifyText(ModifyEvent arg0) {
		String projectName = _view.getSelectedProject();
		String value = _view.getdata(projectName, _field);
		_model.changeInflencingFactorField(projectName, _field, value.length()==0?"0":value);
	}

}

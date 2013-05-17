package controller;

import model.ModelInterface;
import model.data.InfluencingFactorTypeEnum;
import model.data.SpecificationFieldEnum;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import view.ViewInterface;

/**
 * A flexible common listener for all influencing factors, that changes the field,
 * when the content of the control it is given to gets modified.
 * It is a part of the controller.
 * @author smgug_000
 *
 */
public class ModifyInfluencingFactorsListener implements ModifyListener {

	private InfluencingFactorTypeEnum _field;
	private ModelInterface _model;
	private ViewInterface _view;
	
	/**
	 * Constructor that takes a InfluencingFactor, the model and the view. this
	 * listener is a part of the controller
	 * @param model implementation of ModelInterface
	 * @param view implementation of ViewInterface
	 * @param field the field the control, that gets this listener, belongs to
	 */
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
